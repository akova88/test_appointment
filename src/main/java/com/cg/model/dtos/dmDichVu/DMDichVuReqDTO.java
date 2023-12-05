package com.cg.model.dtos.dmDichVu;

import com.cg.model.DMDichVu;
import com.cg.model.DMNhomDV;
import com.cg.model.dtos.dmNhomDv.DMNhomDvResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DMDichVuReqDTO {

    @NotBlank(message = "Mã Dịch Vụ là bắt buộc")
    private String maDv;

    @NotBlank(message = "Tên Dịch Vụ không được để trống")
    @Size(min = 5, max = 150, message = "Tên phải từ 5 - 150 ký tự")
    private String tenDv;

    @NotNull(message = "Giá dịch vụ là bắt buộc")
    @DecimalMin(value = "0.01", message = "Giá dịch vụ phải là định dạng số lớn hơn 0")
    private String donGiaTT;

    @NotNull(message = "Vui lòng chọn nhóm dịch vụ")
    private String maNhomDv;

    public DMDichVu toDmDichVu(DMNhomDV dmNhomDV) {
        return new DMDichVu()
                .setId(null)
                .setMaDv(maDv)
                .setTenDv(tenDv)
                .setDonGiaTT(BigDecimal.valueOf(Long.parseLong(donGiaTT)))
                .setDmNhomDV(dmNhomDV);
    }
}
