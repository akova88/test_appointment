package com.cg.model;

import com.cg.model.dtos.dmDichVu.DMDichVuResDTO;
import com.cg.model.dtos.dm_goi.DM_GoiResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "DM_Goi")
@Accessors(chain = true)
@Entity
public class DM_Goi extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MaGoi", columnDefinition = "nvarchar(15)", nullable = false, unique = true)
    private String maGoi;

    @Column(name = "TenGoi", columnDefinition = "nvarchar(150)", nullable = false)
    private String tenGoi;

    @Column(precision = 10, scale = 0, nullable = false)
    private BigDecimal giaGoi;

    @ManyToMany
    @JoinTable(
            name = "goi_dv",
            joinColumns = @JoinColumn(name = "dmdichvu_id"),
            inverseJoinColumns = @JoinColumn(name = "dmgoi_id")
    )
    private List<DMDichVu> dmDichVuList;

    public DM_GoiResDTO toDmGoiResDTO() {
        return new DM_GoiResDTO()
                .setId(id)
                .setMaGoi(maGoi)
                .setTenGoi(tenGoi)
                .setGiaGoi(giaGoi)
                .setDmDichVuList(dmDichVuList.stream().map(DMDichVu::toDmDichVuResDTO).collect(Collectors.toList()));
    }
}
