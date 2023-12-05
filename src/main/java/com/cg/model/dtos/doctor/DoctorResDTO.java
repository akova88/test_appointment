package com.cg.model.dtos.doctor;

import com.cg.model.dtos.locationRegion.LocationRegionResDTO;
import com.cg.model.dtos.user.UserResDTO;
import com.cg.model.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DoctorResDTO {

    private Long id;

    private String fullName;

    private String email;

    private EGender gender;

    private String phone;

    private Date DOB;

    private String job;

    private String identityNumber;

    private String ethnic;

    private LocationRegionResDTO locationRegion;

    private UserResDTO user;

    private String specName;

    private String levelName;
}
