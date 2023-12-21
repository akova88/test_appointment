package com.cg.model;

import com.cg.model.dtos.dmDichVu.DMDichVuResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "dmdich_vu")
@Accessors(chain = true)
@Entity
public class DMDichVu extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "madv", columnDefinition = "nvarchar(15)", nullable = false, unique = true)
    private String maDv;

    @Column(name = "tendv", columnDefinition = "nvarchar(150)", nullable = false)
    private String tenDv;

    @Column(name = "don_giatt", precision = 10, scale = 0, nullable = false)
    private BigDecimal donGiaTT;

    @ManyToOne
    @JoinColumn(name = "dm_nhom_dv_id", referencedColumnName = "id", nullable = false)
    private DMNhomDV dmNhomDV;

    @ManyToMany(mappedBy = "dmDichVuList", fetch = FetchType.LAZY)
    private List<DM_Goi> dmGoiList;

    public DMDichVuResDTO toDmDichVuResDTO() {
        return new DMDichVuResDTO()
                .setId(id)
                .setMaDv(maDv)
                .setTenDv(tenDv)
                .setDonGiaTT(donGiaTT)
                .setDmNhomDv(dmNhomDV.toDmNhomDvResDTO());
    }

}
