package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dtos.appointment.AppointmentCreReqDTO;
import com.cg.model.dtos.appointment.AppointmentResDTO;
import com.cg.model.dtos.appointment.AppointmentUpReqDTO;
import com.cg.model.dtos.doctor.DoctorResDTO;
import com.cg.model.dtos.room.RoomResDTO;
import com.cg.model.enums.ETime;
import com.cg.service.appointment.IAppointmentService;
import com.cg.service.doctor.IDoctorService;
import com.cg.service.medicalBill.IMedicalBillService;
import com.cg.service.room.IRoomService;
import com.cg.service.speciality.ISpecialityService;
import com.cg.utils.AppUtils;
import com.cg.utils.DateFormat;
import com.cg.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentAPI {

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private ISpecialityService specialityService;

    @Autowired
    private IMedicalBillService medicalBillService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AppointmentCreReqDTO appointmentCreReqDTO,
                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Long specialityId = Long.parseLong(appointmentCreReqDTO.getSpecialityId());
        Speciality speciality = specialityService.findById(specialityId).orElseThrow(()-> new DataInputException("Chuyên khoa không tồn tại"));

        Long doctorId = Long.parseLong(appointmentCreReqDTO.getDoctorId());
        Doctor doctor = doctorService.findById(doctorId).orElseThrow(() -> new DataInputException("Bác sĩ không tồn tại"));

        Long doctorSpecialityId = doctor.getSpeciality().getId();
        if (!specialityId.equals(doctorSpecialityId)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Bác sĩ không thuộc "+speciality.getName());
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        ETime eTime = ETime.valueOf(appointmentCreReqDTO.getTimeName());
        Date day = DateFormat.parse(appointmentCreReqDTO.getDay());

        LocalDate now = LocalDate.now();
        LocalDate dayComp = DateFormat.convertToLocalDate(appointmentCreReqDTO.getDay());

        if (dayComp.isBefore(now) || dayComp.isEqual(now)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Không thể tạo mới lịch khám trong hoặc trước ngày hôm nay");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        List<Appointment> appointments1 = appointmentService.getAllByDoctorIdAndDayAndTimeAndIdNot(doctorId,day,eTime, -1L);
        if (!appointments1.isEmpty()){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Bác sĩ đã có lịch khám cùng giờ");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long roomId = Long.parseLong(appointmentCreReqDTO.getRoomId());
        Room room = roomService.findById(roomId).orElseThrow(()-> new DataInputException("Phòng khám không tồn tại"));

        Long roomSpecialityId= room.getSpeciality().getId();
        if (!specialityId.equals(roomSpecialityId)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Phòng khám không thuộc "+speciality.getName());
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        List<Appointment> appointments = appointmentService.getAllByRoomIdAndDayAndTimeAndIdNot(roomId,day,eTime, -1L);
        if (!appointments.isEmpty()){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Phòng khám không sẵn sàng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Appointment appointment = appointmentCreReqDTO.toAppointment(doctor,room,eTime, speciality);
        Appointment newAppointment = appointmentService.save(appointment);
        AppointmentResDTO appointmentResDTO = newAppointment.toAppointmentResDTO();

        return new ResponseEntity<>(appointmentResDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<AppointmentResDTO> appointmentResDTOS = new ArrayList<>();

        List<Appointment> appointments = appointmentService.findAll();
        List<Appointment> invalidDayAppointments = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for (Appointment appointment: appointments){
            if (!appointment.isDeleted()){
                String appDayStr = DateFormat.format(appointment.getDay());
                LocalDate appDay = DateFormat.convertToLocalDate(appDayStr);

                if (appDay.isEqual(now) || appDay.isBefore(now)){
                    invalidDayAppointments.add(appointment);
                }else {
                    AppointmentResDTO appointmentResDTO = appointment.toAppointmentResDTO();
                    appointmentResDTOS.add(appointmentResDTO);
                }
            }

        }

        appointmentService.deleteInvalidDateAppointments(invalidDayAppointments);

        return new ResponseEntity<>(appointmentResDTOS,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/{appointmentId}")
    public ResponseEntity<?> update(@PathVariable("appointmentId") String appointmentIdStr,
                                    @Valid @RequestBody AppointmentUpReqDTO appointmentUpReqDTO,
                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        if (!ValidateUtil.isNumberValid(appointmentIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "ID lịch khám không đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long appointmentId = Long.parseLong(appointmentIdStr);
        Appointment appointment = appointmentService.findById(appointmentId).orElseThrow(()-> new DataInputException("Lịch khám không tồn tại"));

        String appDayStr = DateFormat.format(appointment.getDay());
        LocalDate appDay = DateFormat.convertToLocalDate(appDayStr);
        LocalDate now = LocalDate.now();
        if (appDay.isBefore(now) || appDay.isEqual(now)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Không thể cập nhật lịch khám có ngày là trước hoặc bằng hôm nay");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        List<MedicalBill> paidMedicalBillsByApp = medicalBillService.getPaidBillsByApp(appointmentId);
        if (!paidMedicalBillsByApp.isEmpty()){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Không thể cập nhật lịch khám đã được thanh toán");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Speciality speciality = appointment.getSpeciality();
        Long specialityId = speciality.getId();

        Long doctorId = Long.parseLong(appointmentUpReqDTO.getDoctorId());
        Doctor doctor = doctorService.findById(doctorId).orElseThrow(() -> new DataInputException("Bác sĩ không tồn tại"));

        Long doctorSpecialityId = doctor.getSpeciality().getId();
        if (!specialityId.equals(doctorSpecialityId)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Bác sĩ không thuộc "+speciality.getName());
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        ETime eTime = ETime.valueOf(appointmentUpReqDTO.getTimeName());
        Date day = DateFormat.parse(appointmentUpReqDTO.getDay());

        List<Appointment> appointments1 = appointmentService.getAllByDoctorIdAndDayAndTimeAndIdNot(doctorId,day,eTime, appointmentId);
        if (!appointments1.isEmpty()){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Bác sĩ đã có lịch khám cùng giờ");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long roomId = Long.parseLong(appointmentUpReqDTO.getRoomId());
        Room room = roomService.findById(roomId).orElseThrow(()-> new DataInputException("Phòng khám không tồn tại"));

        Long roomSpecialityId= room.getSpeciality().getId();
        if (!specialityId.equals(roomSpecialityId)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Phòng khám không thuộc "+speciality.getName());
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        List<Appointment> appointments = appointmentService.getAllByRoomIdAndDayAndTimeAndIdNot(roomId,day,eTime, appointmentId);
        if (!appointments.isEmpty()){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Phòng khám không sẵn sàng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        int isAvailableCode = Integer.parseInt(appointmentUpReqDTO.getIsAvailable());
        boolean isAvailable = isAvailableCode == 1;

        Appointment editedAppointment = appointmentUpReqDTO.toAppointment(appointmentId,doctor,room,eTime,isAvailable);
        Appointment newAppointment = appointmentService.save(editedAppointment);
        newAppointment.setSpeciality(speciality);
        newAppointment.setPrice(BigDecimal.valueOf(150000));
        AppointmentResDTO appointmentResDTO = newAppointment.toAppointmentResDTO();

        return new ResponseEntity<>(appointmentResDTO,HttpStatus.OK);
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<?> getById(@PathVariable("appointmentId") String appointmentIdStr){
        if (!ValidateUtil.isNumberValid(appointmentIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "ID lịch khám không hợp đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long appointmentId = Long.parseLong(appointmentIdStr);
        Appointment appointment = appointmentService.findById(appointmentId).orElseThrow(()-> new DataInputException("Lịch khám không tồn tại"));
        AppointmentResDTO appointmentResDTO = appointment.toAppointmentResDTO();

        return new ResponseEntity<>(appointmentResDTO,HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<?> remove(@PathVariable("appointmentId") String appointmentIdStr){
        if (!ValidateUtil.isNumberValid(appointmentIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "ID lịch khám không hợp đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long appointmentId = Long.parseLong(appointmentIdStr);
        Appointment appointment = appointmentService.findById(appointmentId).orElseThrow(()-> new DataInputException("Lịch khám không tồn tại"));

        appointment.setDeleted(true);
        appointment.setId(appointmentId);
        Appointment removedAppointment = appointmentService.save(appointment);

        AppointmentResDTO appointmentResDTO = removedAppointment.toAppointmentResDTO();

        return new ResponseEntity<>(appointmentResDTO,HttpStatus.OK);
    }

    @GetMapping("/doctor/{specialityId}")
    public ResponseEntity<?> getAssignedDoctorBySpec(@PathVariable("specialityId") String specialityIdStr){
        if (!ValidateUtil.isNumberValid(specialityIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "ID chuyên khoa không hợp đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long specialityId = Long.parseLong(specialityIdStr);
        Speciality speciality = specialityService.findById(specialityId).orElseThrow(()-> new DataInputException("Chuyên khoa không ồn tại"));

        List<Appointment> appointments = appointmentService.getAllBySpecialityId(specialityId);
        List<DoctorResDTO> doctorResDTOS = new ArrayList<>();
        for (Appointment appointment: appointments){
            Doctor doctor = appointment.getDoctor();
            DoctorResDTO doctorResDTO = doctor.toDoctorResDTO();
            doctorResDTOS.add(doctorResDTO);
        }

        List<DoctorResDTO> uniqueDoctor = new ArrayList<>();
        Set<Long> seenIds = new HashSet<>();

        for (DoctorResDTO doctorResDTO : doctorResDTOS) {
            if (!seenIds.contains(doctorResDTO.getId())) {
                uniqueDoctor.add(doctorResDTO);
                seenIds.add(doctorResDTO.getId());
            }
        }

        return new ResponseEntity<>(uniqueDoctor,HttpStatus.OK);
    }

    @GetMapping("/room/{doctorId}")
    public ResponseEntity<?> getAssignedRoomByDoctorId(@PathVariable("doctorId") String doctorIdStr){
        if (!ValidateUtil.isNumberValid(doctorIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "ID bác sĩ không hợp đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long doctorId = Long.parseLong(doctorIdStr);
        Doctor doctor = doctorService.findById(doctorId).orElseThrow(()-> new DataInputException("Bác sĩ không tồn tại"));

        List<Appointment> appointments = appointmentService.getAllByDoctorId(doctorId);
        List<RoomResDTO> roomResDTOS = new ArrayList<>();

        for (Appointment appointment : appointments){
            Room room = appointment.getRoom();
            RoomResDTO roomResDTO = room.toRoomResDTO();
            roomResDTOS.add(roomResDTO);
        }

        List<RoomResDTO> uniqueRooms = new ArrayList<>();
        Set<Long> seenIds = new HashSet<>();

        for (RoomResDTO roomResDTO : roomResDTOS) {
            if (!seenIds.contains(roomResDTO.getId())) {
                uniqueRooms.add(roomResDTO);
                seenIds.add(roomResDTO.getId());
            }
        }

        return new ResponseEntity<>(uniqueRooms,HttpStatus.OK);
    }

    @GetMapping("/{specialityId}/{doctorId}/{roomId}")
    public ResponseEntity<?> getAllBySpecAndDocAndRoom(@PathVariable("specialityId") String specialityIdStr,
                                                       @PathVariable("doctorId") String doctorIdStr,
                                                       @PathVariable("roomId") String roomIdStr){
        if (!ValidateUtil.isNumberValid(specialityIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "ID chuyên khoa không hợp đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long specialityId = Long.parseLong(specialityIdStr);
        Speciality speciality = specialityService.findById(specialityId).orElseThrow(()-> new DataInputException("Chuyên khoa không ồn tại"));

        if (!ValidateUtil.isNumberValid(doctorIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "ID bác sĩ không hợp đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long doctorId = Long.parseLong(doctorIdStr);
        Doctor doctor = doctorService.findById(doctorId).orElseThrow(()-> new DataInputException("Bác sĩ không tồn tại"));

        if (!ValidateUtil.isNumberValid(roomIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "ID phòng khám không hợp đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long roomId = Long.parseLong(roomIdStr);
        Room room = roomService.findById(roomId).orElseThrow(()-> new DataInputException("Phòng khám không tồn tại"));

        // TODO: 29/7/2023 Bắt lỗi không đồng nhất spec doc room

        List<Appointment> appointments = appointmentService.getAllBySpecialityIdAndDoctorIdAndRoomId(specialityId,doctorId,roomId);
        List<AppointmentResDTO> appointmentResDTOS = new ArrayList<>();
        for (Appointment appointment: appointments){
            if (!appointment.isDeleted()){
                if (appointment.isAvailable()){
                    AppointmentResDTO appointmentResDTO = appointment.toAppointmentResDTO();
                    appointmentResDTOS.add(appointmentResDTO);
                }
            }
        }

        return new ResponseEntity<>(appointmentResDTOS,HttpStatus.OK);
    }

}
