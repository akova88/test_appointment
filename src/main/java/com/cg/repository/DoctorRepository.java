package com.cg.repository;

import com.cg.model.Appointment;
import com.cg.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findAllBySpecialityId(Long specialityId);

    Doctor findDoctorByUserId(Long doctorId);
    List<Doctor> findAllByUser_Id(Long userId);

}
