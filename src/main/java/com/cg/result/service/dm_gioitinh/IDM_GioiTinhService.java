package com.cg.result.service.dm_gioitinh;

import com.cg.result.entity.DMGioiTinhDTO;
import com.cg.result.entity.DM_GioiTinh;
import com.cg.result.repository.IDMGioiTinh;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IDM_GioiTinhService extends IGeneralService<DM_GioiTinh, Integer> {
    List<DM_GioiTinh> getAllGioiTinh();

    List<DM_GioiTinh> getAllGt();

    List<DMGioiTinhDTO> getAllGt1();
}
