package com.cg.model.dtos.dmNhomDv;

import com.cg.model.DMNhomDV;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DMNhomDvResDTO {
    private Long id;
    private String maNhomDv;
    private String tenNhomDv;
    private String maChungLoai;

    public DMNhomDvResDTO(Long id, String maNhomDv, String tenNhomDv, String maChungLoai) {
        this.id = id;
        this.maNhomDv = maNhomDv;
        this.tenNhomDv = tenNhomDv;
        this.maChungLoai = maChungLoai;
    }

    public DMNhomDV toDmNhomDV() {
        return new DMNhomDV()
                .setId(id)
                .setMaNhomDv(maNhomDv)
                .setTenNhomDv(tenNhomDv)
                .setMaChungLoai(maChungLoai);
    }
}
