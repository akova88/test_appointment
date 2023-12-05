package com.cg.model.dtos.dmDichVu;

import com.cg.model.DMDichVu;
import com.cg.model.DMNhomDV;
import com.cg.model.dtos.dmNhomDv.DMNhomDvResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DMDichVuResDTO {
    private Long id;
    private String maDv;
    private String tenDv;
    private BigDecimal donGiaTT;
    private DMNhomDvResDTO dmNhomDv;

    public DMDichVu toDmDichVu() {
        return new DMDichVu()
                .setId(id)
                .setMaDv(maDv)
                .setTenDv(tenDv)
                .setDonGiaTT(donGiaTT)
                .setDmNhomDV(dmNhomDv.toDmNhomDV());
    }

    public DMDichVuResDTO(Long id, String maDv, String tenDv, BigDecimal donGiaTT, DMNhomDV dmNhomDv) {
        this.id = id;
        this.maDv = maDv;
        this.tenDv = tenDv;
        this.donGiaTT = donGiaTT;
        this.dmNhomDv = dmNhomDv.toDmNhomDvResDTO();
    }
}
