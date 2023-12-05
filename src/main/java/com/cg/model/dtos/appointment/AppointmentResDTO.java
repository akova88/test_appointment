package com.cg.model.dtos.appointment;

import com.cg.model.Doctor;
import com.cg.model.Room;
import com.cg.model.Speciality;
import com.cg.model.dtos.doctor.DoctorResDTO;
import com.cg.model.dtos.room.RoomResDTO;
import com.cg.model.enums.ETime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class AppointmentResDTO {

    private Long id;

    private DoctorResDTO doctor;

    private String specialityName;

    private RoomResDTO room;

    private Date day;

    private String timeName;

    private BigDecimal price;

    private boolean isAvailable;

    public AppointmentResDTO(Long id, Doctor doctor, Speciality speciality, Room room, Date day, ETime time, BigDecimal price, boolean isAvailable){
        this.id = id;
        this.doctor = doctor.toDoctorResDTO();
        this.specialityName = speciality.getName();
        this.room = room.toRoomResDTO();
        this.day = day;
        this.timeName = time.name();
        this.price = price;
        this.isAvailable = isAvailable;
    }
}
