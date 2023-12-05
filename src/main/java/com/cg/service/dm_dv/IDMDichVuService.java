package com.cg.service.dm_dv;

import com.cg.model.DMDichVu;
import com.cg.model.dtos.dmDichVu.DMDichVuReqDTO;
import com.cg.model.dtos.dmDichVu.DMDichVuResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IDMDichVuService extends IGeneralService<DMDichVu, Long> {
    List<DMDichVuResDTO> findAllDMDichVuResDTO();

    DMDichVuResDTO create(DMDichVuReqDTO dmDichVuReqDTO);

    Boolean existsDMDichVuByTenDv(String tenDv);

    Boolean existsDMDichVuByTenDvAndAndIdNot(String tenDv, Long id);
}
