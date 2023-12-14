package com.cg.service.dm_goi;

import com.cg.model.DM_Goi;
import com.cg.model.dtos.dm_goi.DM_GoiReqDTO;
import com.cg.model.dtos.dm_goi.DM_GoiResDTO;
import com.cg.service.IGeneralService;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.util.List;

public interface IDMGoiService extends IGeneralService<DM_Goi, Long> {
//    List<DM_GoiResDTO> findAllDmGoiResDTO();

    Boolean existsDM_GoiByMaGoiOrTenGoi(String maGoi, String tenGoi);

    Boolean existsDM_GoiByMaGoiOrTenGoiAndIdNot(String maGoi, String tenGoi, Long id);

    DM_GoiResDTO create(DM_GoiReqDTO dmGoiReqDTO) throws JSONException;

    DM_GoiResDTO update(DM_Goi dmGoi);
}
