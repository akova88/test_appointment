package com.cg.model.dtos.billTest;

import com.cg.model.Customer;
import com.cg.model.DM_Goi;
import com.cg.model.LocationRegion;
import com.cg.model.User;
import com.cg.model.dtos.customer.CustomerResDTO;
import com.cg.model.dtos.dm_goi.DM_GoiResDTO;
import com.cg.model.dtos.locationRegion.LocationRegionResDTO;
import com.cg.model.dtos.user.UserResDTO;
import com.cg.model.enums.EBookTime;
import com.cg.model.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class BillTestResDTO {
    private Long id;
    private String maPhieu;
    private DM_GoiResDTO dmGoi;
    private Date takeDate;
    private String timeBookName;
    private String fullName;
    private String email;
    private EGender gender;
    private String phone;
    private Date DOB;
    private LocationRegionResDTO locationRegion;
    private String reasonNote;
    private BigDecimal price;
    private boolean homeService;
    private boolean isConfirm;
    private String staffConfirm;
    private UserResDTO user;

    public BillTestResDTO(Long id, String maPhieu, DM_Goi dmGoi, Date takeDate, EBookTime eBookTime,
                          String fullName, String email, EGender gender, String phone, Date DOB,
                          LocationRegion locationRegion, String reasonNote, BigDecimal price,
                          boolean homeService, boolean isConfirm, String staffConfirm, User user) {
        this.id = id;
        this.maPhieu = maPhieu;
        this.dmGoi = dmGoi.toDmGoiResDTO();
        this.takeDate = takeDate;
        this.timeBookName = eBookTime.name();
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.DOB = DOB;
        this.locationRegion = locationRegion.toLocationRegionResDTO();
        this.reasonNote = reasonNote;
        this.price = price;
        this.homeService = homeService;
        this.isConfirm = isConfirm;
        this.staffConfirm = staffConfirm;
        this.user = user.toUserResDTO();
    }
}
