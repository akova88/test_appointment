package com.cg.result.api;

import com.cg.result.entity.DMGioiTinhDTO;
import com.cg.result.entity.DM_GioiTinh;
import com.cg.result.service.dm_gioitinh.IDM_GioiTinhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dm_gioi_tinh")
public class DM_GioiTinhApi {
    @Autowired
    IDM_GioiTinhService dmGioiTinhService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<DM_GioiTinh> dmGioiTinhs = dmGioiTinhService.getAllGioiTinh();
        return new ResponseEntity<>(dmGioiTinhs, HttpStatus.OK);
    }
}
