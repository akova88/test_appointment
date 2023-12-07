package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.DM_Goi;
import com.cg.model.dtos.dm_goi.DM_GoiReqDTO;
import com.cg.model.dtos.dm_goi.DM_GoiResDTO;
import com.cg.service.dm_goi.IDMGoiService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dm_goi")
public class Dm_GoiAPI {
    @Autowired
    private IDMGoiService dmGoiService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getAllDmGoi() {
        List<DM_Goi> dmGois = dmGoiService.findAll();
        List<DM_GoiResDTO> dmGoiResDTOS = dmGois.stream().map(DM_Goi ::toDmGoiResDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dmGoiResDTOS, HttpStatus.OK);
    }

    @GetMapping("/{goiDvId}")
    public ResponseEntity<?> findById(@PathVariable Long goiDvId) {
        DM_Goi dmGoi = dmGoiService.findById(goiDvId).orElseThrow(() -> (
                new DataInputException("Mã gói dịch vụ không tồn tại")));

        return new ResponseEntity<>(dmGoi.toDmGoiResDTO(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid DM_GoiReqDTO dmGoiReqDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        String maGoiDv = dmGoiReqDTO.getMaGoi();
        String tenGoiDv = dmGoiReqDTO.getTenGoi();
        Boolean existed = dmGoiService.existsDM_GoiByMaGoiOrTenGoi(maGoiDv, tenGoiDv);
        if (existed) {
            throw new DataInputException("Tên gói hoặc mã gói xét nghiệm đã tồn tại");
        }

        DM_GoiResDTO dmGoiResDTO = null;
        try {
            dmGoiResDTO = dmGoiService.create(dmGoiReqDTO);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(dmGoiResDTO, HttpStatus.OK);
    }
}
