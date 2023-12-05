package com.cg.model.dtos.dm_goi;

import com.cg.model.DMDichVu;
import com.cg.model.dtos.dmDichVu.DMDichVuResDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class DM_GoiResDTO {
    private Long id;
    private String maGoi;
    private String tenGoi;
    private BigDecimal giaGoi;
    private List<DMDichVuResDTO> dmDichVuList;

    public DM_GoiResDTO(Long id, String maGoi, String tenGoi, BigDecimal giaGoi, List<DMDichVu> dmDichVuList) {
        this.id = id;
        this.maGoi = maGoi;
        this.tenGoi = tenGoi;
        this.giaGoi = giaGoi;
        this.dmDichVuList = dmDichVuList.stream().map(DMDichVu::toDmDichVuResDTO).collect(Collectors.toList());
    }
}
