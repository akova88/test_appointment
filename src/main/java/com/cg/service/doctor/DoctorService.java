package com.cg.service.doctor;

import com.cg.model.Appointment;
import com.cg.model.Doctor;
import com.cg.model.LocationRegion;
import com.cg.repository.DoctorRepository;
import com.cg.repository.LocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DoctorService implements IDoctorService{

    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAllBySpecialityId(Long specialityId) {
        return doctorRepository.findAllBySpecialityId(specialityId);
    }

    @Override
    public Doctor findDoctorByUserId(Long doctorId) {
        return doctorRepository.findDoctorByUserId(doctorId);
    }


    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> findAllByUser_Id(Long userId) {
        return doctorRepository.findAllByUser_Id(userId);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void delete(Doctor doctor) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Doctor create(LocationRegion locationRegion, Doctor doctor) {
        LocationRegion locationRegion1 = locationRegionRepository.save(locationRegion);
        doctor.setLocationRegion(locationRegion1);
        return doctorRepository.save(doctor);
    }
}
