package com.cg.model;

import com.cg.model.dtos.dmDichVu.DMDichVuResDTO;
import com.cg.model.dtos.dm_goi.DM_GoiResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "dm_goi")
@Accessors(chain = true)
@Entity
@SQLDelete(sql = "UPDATE dm_goi SET is_Remove=true WHERE id = ?")
@Where(clause = "is_Remove = false")
public class DM_Goi extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_goi", columnDefinition = "nvarchar(15)", nullable = false, unique = true)
    private String maGoi;

    @Column(name = "ten_goi", columnDefinition = "nvarchar(150)", nullable = false)
    private String tenGoi;

    @Column(name = "gia_goi", precision = 10, scale = 0, nullable = false)
    private BigDecimal giaGoi;

    @ManyToMany
    @JoinTable(
            name = "goi_dv",
            joinColumns = @JoinColumn(name = "dmgoi_id"),
            inverseJoinColumns = @JoinColumn(name = "dmdichvu_id")
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
