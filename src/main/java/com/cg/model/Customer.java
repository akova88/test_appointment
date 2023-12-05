package com.cg.model;

import com.cg.model.dtos.customer.CustomerResDTO;
import com.cg.model.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "customers")
@Accessors(chain = true)
@Entity
public class Customer extends BasePerson{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "location_region_id", referencedColumnName = "id", nullable = false)
    private LocationRegion locationRegion;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, updatable = false)
    private User user;

    public Customer(String fullName, String email, EGender gender, String phone, Date DOB, String job, String identityNumber, String ethnic, Long id, LocationRegion locationRegion, User user) {
        super(fullName, email, gender, phone, DOB, job, identityNumber, ethnic);
        this.id = id;
        this.locationRegion = locationRegion;
        this.user = user;
    }

    public CustomerResDTO toCustomerResDTO(){

        return new CustomerResDTO(id, getFullName(), getEmail(), getGender(), getPhone(), getDOB(), getJob(), getIdentityNumber(),
                getEthnic(), locationRegion.toLocationRegionResDTO(), user.toUserResDTO());
    }
}
