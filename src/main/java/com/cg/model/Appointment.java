package com.cg.model;

import com.cg.model.dtos.appointment.AppointmentResDTO;
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
@Table(name = "appointments")
@Accessors(chain = true)
@Entity
public class Appointment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Speciality speciality;

    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id", nullable = false)
    private Room room;

    @Column(nullable = false)
    private Date day;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ETime time;

    @Column(precision = 10, scale = 0, nullable = false, updatable = false)
    private BigDecimal price;

    @Column(nullable = false, name = "is_available")
    private boolean isAvailable;

    public AppointmentResDTO toAppointmentResDTO(){
        return new AppointmentResDTO()
                .setId(id)
                .setDoctor(doctor.toDoctorResDTO())
                .setSpecialityName(speciality.getName())
                .setRoom(room.toRoomResDTO())
                .setDay(day)
                .setTimeName(getTime().name())
                .setPrice(price)
                .setAvailable(isAvailable);
    }

}
