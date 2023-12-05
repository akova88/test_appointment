package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.model.DMDichVu;
import com.cg.model.dtos.dmDichVu.DMDichVuReqDTO;
import com.cg.model.dtos.dmDichVu.DMDichVuResDTO;
import com.cg.service.dm_dv.IDMDichVuService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/dm_dv")
public class DM_DichVuAPI {

    @Autowired
    private IDMDichVuService dmDichVuService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getAllDMDichVu() {
        List<DMDichVuResDTO> dmDichVuResDTOS = dmDichVuService.findAllDMDichVuResDTO();
        return new ResponseEntity<>(dmDichVuResDTOS, HttpStatus.OK);
    }

    @GetMapping("/{dichVuId}")
    public ResponseEntity<?> getDichVuById(@PathVariable Long dichVuId) {
        DMDichVu dmDichVu = dmDichVuService.findById(dichVuId).orElseThrow(() -> {
            throw new DataInputException("Mã Dịch Vụ không tồn tại");
        });
        return new ResponseEntity<>(dmDichVu.toDmDichVuResDTO(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid DMDichVuReqDTO dmDichVuReqDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        String tenDv = dmDichVuReqDTO.getTenDv();
        Boolean existTenDv = dmDichVuService.existsDMDichVuByTenDv(tenDv);
        if (existTenDv) {
            throw new DataInputException("Tên dịch vụ đã tồn tại!");
        }

        DMDichVuResDTO dmDichVuResDTO = dmDichVuService.create(dmDichVuReqDTO);

        return new ResponseEntity<>(dmDichVuResDTO, HttpStatus.OK);
    }

}
