package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dtos.customer.CustomerResDTO;
import com.cg.model.dtos.doctor.DoctorCreReqDTO;
import com.cg.model.dtos.doctor.DoctorResDTO;
import com.cg.model.dtos.doctor.DoctorUpReqDTO;
import com.cg.model.dtos.locationRegion.LocationRegionCreReqDTO;
import com.cg.model.dtos.locationRegion.LocationRegionUpReqDTO;
import com.cg.model.enums.EGender;
import com.cg.model.enums.ELevel;
import com.cg.service.doctor.IDoctorService;
import com.cg.service.speciality.ISpecialityService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorAPI {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISpecialityService specialityService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<DoctorResDTO> doctorResDTOS = new ArrayList<>();
        List<Doctor> doctors = doctorService.findAll();

        for (Doctor doctor: doctors){
            if (!doctor.isDeleted()) {
                DoctorResDTO doctorResDTO = doctor.toDoctorResDTO();
                doctorResDTOS.add(doctorResDTO);
            }
        }

        return new ResponseEntity<>(doctorResDTOS,HttpStatus.OK);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<?> getById(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.findById(doctorId).orElseThrow(() -> {
            throw new DataInputException("Mã bác sĩ không tồn tại");
        });
        DoctorResDTO doctorResDTO = doctor.toDoctorResDTO();
        return new ResponseEntity<>(doctorResDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DoctorCreReqDTO doctorCreReqDTO,
                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            appUtils.mapErrorToResponse(bindingResult);
        }

        String eGenderName = doctorCreReqDTO.getNameGender();
        EGender eGender;
        try {
            eGender = EGender.valueOf(eGenderName);
        }catch (IllegalArgumentException e){
            throw new DataInputException("Giới tính không tồn tại");
        }

        LocationRegionCreReqDTO locationRegionCreReqDTO = doctorCreReqDTO.getLocationRegion();
        LocationRegion locationRegion = locationRegionCreReqDTO.toLocationRegion();

        Long userid = Long.parseLong(doctorCreReqDTO.getUserId());
        User user = userService.findById(userid).orElseThrow(() -> new DataInputException("Ngưới dùng không tồn tại"));

        List<Doctor> doctors = doctorService.findAllByUser_Id(userid);
        if (!doctors.isEmpty()) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Tài khoản đã có hồ sơ bác sĩ!");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long specialityId = Long.valueOf(doctorCreReqDTO.getSpecialityId());
        Speciality speciality = specialityService.findById(specialityId).orElseThrow(()->{
            throw new DataInputException("Chuyên khoa không tồn tại");
        });

        String eLevelName = doctorCreReqDTO.getLevelName();
        ELevel eLevel;
        try {
            eLevel = ELevel.valueOf(eLevelName);
        }catch (IllegalArgumentException e){
            throw new DataInputException("Trình độ không tồn tại");
        }

        Doctor doctor = doctorCreReqDTO.toDoctor(eGender,locationRegion,user,speciality,eLevel);
        Doctor newDoctor = doctorService.create(locationRegion, doctor);
        DoctorResDTO doctorResDTO = newDoctor.toDoctorResDTO();

        return new ResponseEntity<>(doctorResDTO,HttpStatus.CREATED);
    }

    @PatchMapping("/{doctorId}")
    public ResponseEntity<?> update(@PathVariable("doctorId") String doctorIdStr,
                                    @RequestBody DoctorUpReqDTO doctorUpReqDTO,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            appUtils.mapErrorToResponse(bindingResult);
        }
        if (!ValidateUtil.isNumberValid(doctorIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã bác sĩ không đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long doctorId = Long.parseLong(doctorIdStr);
        Doctor doctor = doctorService.findById(doctorId).orElseThrow(() -> {
            throw  new DataInputException("Bác sĩ không tồn tại");
        });

        Long locationRegionId = doctor.getLocationRegion().getId();
        User user = doctor.getUser();

        String eGenderName = doctorUpReqDTO.getNameGender();
        EGender eGender;
        try {
            eGender = EGender.valueOf(eGenderName);
        }catch (IllegalArgumentException e){
            throw new DataInputException("Giới tính không tồn tại");
        }

        String eLevelName = doctorUpReqDTO.getLevelName();
        ELevel eLevel;
        try {
            eLevel = ELevel.valueOf(eLevelName);
        }catch (IllegalArgumentException e){
            throw new DataInputException("Level không tồn tại");
        }
        String specialityIdStr = doctorUpReqDTO.getSpecialityId();
        Long specialityId = Long.parseLong(specialityIdStr);

        Speciality speciality = specialityService.findById(specialityId).orElseThrow(() -> {
            throw new DataInputException("Khoa không tồn tại");
        });

        LocationRegionUpReqDTO locationRegionUpReqDTO = doctorUpReqDTO.getLocationRegion();
        LocationRegion locationRegion = locationRegionUpReqDTO.toLocationRegion(locationRegionId);
        Doctor updateDoctor = doctorUpReqDTO.toDoctor(eGender, doctorId, locationRegionId, user, speciality,eLevel);
        Doctor newDoctor = doctorService.create(locationRegion, updateDoctor);

        DoctorResDTO doctorResDTO = newDoctor.toDoctorResDTO();

        return new ResponseEntity<>(doctorResDTO, HttpStatus.OK);
    }

    @GetMapping("/speciality/{specialityId}")
    public ResponseEntity<?> getDoctorsBySpec(@PathVariable("specialityId") String specialityIdStr){
        if (!ValidateUtil.isNumberValid(specialityIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã chuyên khoa không đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long specialityId = Long.parseLong(specialityIdStr);
        Speciality speciality = specialityService.findById(specialityId).orElseThrow(()->{
            throw new DataInputException("Chuyên khoa không tồn tại");
        });

        List<Doctor> doctors = doctorService.findAllBySpecialityId(specialityId);
        List<DoctorResDTO> doctorResDTOS = new ArrayList<>();

        for (Doctor doctor: doctors){
            DoctorResDTO doctorResDTO = doctor.toDoctorResDTO();
            doctorResDTOS.add(doctorResDTO);
        }

        return new ResponseEntity<>(doctorResDTOS,HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<?> suspend(@PathVariable("doctorId") String doctorIdStr) {

        if (!ValidateUtil.isNumberValid(doctorIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã bác sĩ không đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long doctorId = Long.parseLong(doctorIdStr);
        Doctor doctor = doctorService.findById(doctorId).orElseThrow(() -> {
            throw  new DataInputException("Bác sĩ không tồn tại");
        });

        doctor.setDeleted(true);

        Doctor newDoctor = doctorService.save(doctor);
        DoctorResDTO doctorResDTO = newDoctor.toDoctorResDTO();
        return new ResponseEntity<>(doctorResDTO, HttpStatus.OK);
    }
}
