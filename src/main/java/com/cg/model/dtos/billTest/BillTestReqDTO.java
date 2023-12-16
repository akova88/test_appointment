package com.cg.model.dtos.billTest;

import com.cg.model.BillTest;
import com.cg.model.Customer;
import com.cg.model.DM_Goi;
import com.cg.model.User;
import com.cg.model.dtos.locationRegion.LocationRegionCreReqDTO;
import com.cg.model.enums.EBookTime;
import com.cg.model.enums.EGender;
import com.cg.utils.DateFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class BillTestReqDTO {
    private String maGoi;
    private String takeDate;
    private String timeBookName;
    private String fullName;
    private String email;
    private String genderName;
    private String phone;
    private String birthDay;
    private LocationRegionCreReqDTO locationRegion;
    private String reasonNote;
    private boolean homeService;

    @NotNull(message = "ID khách hàng không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ID khách hàng")
    @NotEmpty(message = "ID khách hàng không thể để trống")
    @Pattern(regexp = "\\d+", message = "ID khách hàng phải là một số")
    private String userId;

    public BillTest toBillTest(DM_Goi dmGoi, EBookTime eBookTime, EGender eGender, User user) {
        return new BillTest(fullName, email, eGender, phone, DateFormat.parse(birthDay), null,
                null, null, null, null, dmGoi,
                DateFormat.parse(takeDate), eBookTime, locationRegion.toLocationRegion(), reasonNote,
                dmGoi.getGiaGoi(), homeService, false, null, user);
    }
}
