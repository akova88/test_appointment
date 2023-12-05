package com.cg.service.appointment;

import com.cg.model.Appointment;
import com.cg.model.Doctor;
import com.cg.model.dtos.appointment.AppointmentResDTO;
import com.cg.model.enums.ETime;
import com.cg.service.IGeneralService;

import java.util.Date;
import java.util.List;

public interface IAppointmentService extends IGeneralService<Appointment, Long> {
    List<Appointment> getAllByRoomIdAndDayAndTimeAndIdNot (Long roomId, Date day, ETime time, Long id);

    List<Appointment> getAllByDoctorIdAndDayAndTimeAndIdNot (Long doctorId, Date day, ETime time, Long id);

    List<AppointmentResDTO> findAllAppointmentResDTO();

    List<Appointment> getAllBySpecialityIdAndDoctorIdAndRoomId(Long specialityId, Long doctorId, Long roomId);

    List<Appointment> getAllByDoctorId(Long doctorId);

    List<Appointment> getAllBySpecialityId(Long specialityId);
    List<Appointment> getAppointmentByDoctorId(Long doctorId);

    void deleteInvalidDateAppointments(List<Appointment> appointments);

}
