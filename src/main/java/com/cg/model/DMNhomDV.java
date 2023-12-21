package com.cg.model;

import com.cg.model.dtos.dmNhomDv.DMNhomDvResDTO;
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
@Table(name = "dmnhomdv")
@Accessors(chain = true)
@Entity
public class DMNhomDV extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manhomdv", columnDefinition = "nvarchar(15)", nullable = false, unique = true)
    private String maNhomDv;

    @Column(name = "ten_nhomdv", columnDefinition = "nvarchar(150)", nullable = false)
    private String tenNhomDv;

    @Column(name = "ma_chung_loai", columnDefinition = "nvarchar(5)", nullable = false)
    private String maChungLoai;

    public DMNhomDvResDTO toDmNhomDvResDTO() {
        return  new DMNhomDvResDTO()
                .setId(id)
                .setMaNhomDv(maNhomDv)
                .setTenNhomDv(tenNhomDv)
                .setMaChungLoai(maChungLoai);
    }
}
