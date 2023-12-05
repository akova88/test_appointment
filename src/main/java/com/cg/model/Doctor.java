package com.cg.model;

import com.cg.model.dtos.doctor.DoctorResDTO;
import com.cg.model.enums.EGender;
import com.cg.model.enums.ELevel;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "doctors")
@Accessors(chain = true)
@Entity
public class Doctor extends BasePerson{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "location_region_id", referencedColumnName = "id", nullable = false)
    private LocationRegion locationRegion;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "speciality_id", referencedColumnName = "id", nullable = false)
    private Speciality speciality;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ELevel level;

    public Doctor(String fullName, String email, EGender gender, String phone, Date DOB, String job, String identityNumber, String ethnic, Long id, LocationRegion locationRegion, User user, Speciality speciality, ELevel level) {
        super(fullName, email, gender, phone, DOB, job, identityNumber, ethnic);
        this.id = id;
        this.locationRegion = locationRegion;
        this.user = user;
        this.speciality = speciality;
        this.level = level;
    }

    public DoctorResDTO toDoctorResDTO(){

        return new DoctorResDTO(id, getFullName(), getEmail(), getGender(), getPhone(), getDOB(), getJob(), getIdentityNumber(),
                getEthnic(), locationRegion.toLocationRegionResDTO(), user.toUserResDTO(), speciality.getName(), level.name());
    }
}
