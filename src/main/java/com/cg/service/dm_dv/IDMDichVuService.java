package com.cg.service.dm_dv;

import com.cg.model.DMDichVu;
import com.cg.model.dtos.dmDichVu.DMDichVuReqDTO;
import com.cg.model.dtos.dmDichVu.DMDichVuResDTO;
import com.cg.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDMDichVuService extends IGeneralService<DMDichVu, Long> {
    List<DMDichVuResDTO> findAllDMDichVuResDTO();

    Page<DMDichVu> findAllByTenDvLike(String tenDv, Pageable pageable);

    Page<DMDichVu> findAll(Pageable pageable);

    DMDichVu findDMDichVuByMaDv(String maDv);

    DMDichVuResDTO create(DMDichVuReqDTO dmDichVuReqDTO);

    Boolean existsDMDichVuByTenDv(String tenDv);

    Boolean existsDMDichVuByTenDvAndAndIdNot(String tenDv, Long id);
}
