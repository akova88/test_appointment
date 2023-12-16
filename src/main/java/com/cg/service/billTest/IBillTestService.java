package com.cg.service.billTest;

import com.cg.model.BillTest;
import com.cg.model.LocationRegion;
import com.cg.model.dtos.billTest.BillTestResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IBillTestService extends IGeneralService<BillTest, Long> {
    List<BillTestResDTO> findAllBillTestResDTO();

    BillTest create(BillTest billTest, LocationRegion locationRegion);
}
