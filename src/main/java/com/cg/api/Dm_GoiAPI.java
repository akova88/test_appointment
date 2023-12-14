package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.DMDichVu;
import com.cg.model.DM_Goi;
import com.cg.model.dtos.dm_goi.DM_GoiReqDTO;
import com.cg.model.dtos.dm_goi.DM_GoiResDTO;
import com.cg.service.dm_dv.IDMDichVuService;
import com.cg.service.dm_goi.IDMGoiService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dm_goi")
public class Dm_GoiAPI {
    @Autowired
    private IDMGoiService dmGoiService;

    @Autowired
    private IDMDichVuService dmDichVuService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ResponseEntity<?> getAllDmGoi() {
        List<DM_Goi> dmGois = dmGoiService.findAll();
        List<DM_GoiResDTO> dmGoiResDTOS = dmGois.stream().map(DM_Goi::toDmGoiResDTO).collect(Collectors.toList());
        return new ResponseEntity<>(dmGoiResDTOS, HttpStatus.OK);
    }

    @GetMapping("/{goiDvId}")
    public ResponseEntity<?> findById(@PathVariable("goiDvId") String goiDvIdStr) {

        if (!ValidateUtil.isNumberValid(goiDvIdStr)) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã gói dịch vụ không đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long goiDvId = Long.parseLong(goiDvIdStr);
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

        DM_GoiResDTO dmGoiResDTO;
        try {
            dmGoiResDTO = dmGoiService.create(dmGoiReqDTO);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(dmGoiResDTO, HttpStatus.OK);
    }

    @PatchMapping("{goiDvId}")
    public ResponseEntity<?> update(@PathVariable("goiDvId") String goiDvIdStr,
                                    @RequestBody @Valid DM_GoiReqDTO dmGoiReqDTO,
                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            appUtils.mapErrorToResponse(bindingResult);
        }

        if (!ValidateUtil.isNumberValid(goiDvIdStr)) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã gói dịch vụ không đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long goiDvId = Long.parseLong(goiDvIdStr);
        DM_Goi dmGoi = dmGoiService.findById(goiDvId).orElseThrow(() -> (
                new DataInputException("Gói dịch vụ không tồn tại")));

        String maGoi = dmGoiReqDTO.getMaGoi();
        String tenGoi = dmGoiReqDTO.getTenGoi();
        String giaGoi = dmGoiReqDTO.getGiaGoi();

        if (!ValidateUtil.isNumberValid(giaGoi)) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Giá gói dịch vụ không đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Boolean goiExists = dmGoiService.existsDM_GoiByMaGoiOrTenGoiAndIdNot(maGoi, tenGoi, goiDvId);
        if (goiExists) {
            throw new DataInputException("Tên gói hoặc mã gói đã tồn tại");
        }

        List<DMDichVu> dmDichVuList = new ArrayList<>();
        String maDVlistStr = dmGoiReqDTO.getMaDvList();
        try {
            if (maDVlistStr != null && !maDVlistStr.isEmpty()) {
                JSONArray maDVlist = new JSONArray(maDVlistStr);

                for (int i = 0; i < maDVlist.length(); i++) {
                    String maDv = maDVlist.getString(i);
                    DMDichVu dmDichVu = dmDichVuService.findDMDichVuByMaDv(maDv);
                    dmDichVuList.add(dmDichVu);
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        dmGoi.setMaGoi(maGoi);
        dmGoi.setTenGoi(tenGoi);
        dmGoi.setGiaGoi(BigDecimal.valueOf(Long.parseLong(giaGoi)));
        dmGoi.setDmDichVuList(dmDichVuList);
        DM_GoiResDTO dmGoiResDTO = dmGoiService.update(dmGoi);

        return new ResponseEntity<>(dmGoiResDTO,HttpStatus.OK);
    }
}
