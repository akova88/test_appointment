package com.cg.service.dm_nhom_dv;

import com.cg.model.DMNhomDV;
import com.cg.model.dtos.dmNhomDv.DMNhomDvResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IDMNhomDvService extends IGeneralService<DMNhomDV, Long> {
    List<DMNhomDvResDTO> findAllDmNhomDvResDTO();

    DMNhomDV findDMNhomDVByMaNhomDv(String maNhomDmDichVu);
}
