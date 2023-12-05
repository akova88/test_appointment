package com.cg.service.doctor;

import com.cg.model.Appointment;
import com.cg.model.Doctor;
import com.cg.model.LocationRegion;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IDoctorService extends IGeneralService<Doctor, Long> {
    Doctor create(LocationRegion locationRegion, Doctor doctor);

    List<Doctor> findAllBySpecialityId(Long specialityId);


   Doctor findDoctorByUserId(Long doctorId);


    List<Doctor> findAllByUser_Id(Long userId);

}
