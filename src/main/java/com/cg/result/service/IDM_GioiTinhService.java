package com.cg.result.service;

import com.cg.result.entity.DM_GioiTinh;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IDM_GioiTinhService extends IGeneralService<DM_GioiTinh, Integer> {
    List<DM_GioiTinh> getAllGioiTinh();
}
