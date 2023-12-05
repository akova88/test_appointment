package com.cg.service.appointment;

import com.cg.model.Appointment;
import com.cg.model.dtos.appointment.AppointmentResDTO;
import com.cg.model.enums.ETime;
import com.cg.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentService implements IAppointmentService{

    @Autowired
    AppointmentRepository appointmentRepository;



    @Override
    public List<AppointmentResDTO> findAllAppointmentResDTO() {
        return appointmentRepository.findAllAppointmentResDTO();
    }

    @Override
    public List<Appointment> getAllByRoomIdAndDayAndTimeAndIdNot(Long roomId, Date day, ETime time, Long id) {
        return appointmentRepository.getAllByRoomIdAndDayAndTimeAndIdNot(roomId, day, time, id);
    }

    @Override
    public List<Appointment> getAllByDoctorIdAndDayAndTimeAndIdNot(Long doctorId, Date day, ETime time, Long id) {
        return appointmentRepository.getAllByDoctorIdAndDayAndTimeAndIdNot(doctorId, day, time, id);
    }

    @Override
    public List<Appointment> getAllByDoctorId(Long doctorId) {
        return appointmentRepository.getAllByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAllBySpecialityId(Long specialityId) {
        return appointmentRepository.getAllBySpecialityId(specialityId);
    }

    @Override
    public List<Appointment> getAppointmentByDoctorId(Long doctorId) {
        return appointmentRepository.getAppointmentByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> getAllBySpecialityIdAndDoctorIdAndRoomId(Long specialityId, Long doctorId, Long roomId) {
        return appointmentRepository.getAllBySpecialityIdAndDoctorIdAndRoomId(specialityId, doctorId, roomId);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void delete(Appointment appointment) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteInvalidDateAppointments(List<Appointment> appointments) {
        for (Appointment appointment: appointments){
            Long appointmentId = appointment.getId();
            appointment.setId(appointmentId);
            appointment.setDeleted(true);
            appointmentRepository.save(appointment);
        }
    }
}
