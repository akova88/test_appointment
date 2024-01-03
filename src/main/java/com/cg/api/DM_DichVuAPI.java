package com.cg.api;


import com.cg.exception.DataInputException;
import com.cg.model.DMDichVu;
import com.cg.model.dtos.dmDichVu.DMDichVuReqDTO;
import com.cg.model.dtos.dmDichVu.DMDichVuResDTO;
import com.cg.service.dm_dv.IDMDichVuService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("/pagination")
    public ResponseEntity<?> getAllPagination(@RequestParam(required = false) String kw,
                                              @RequestParam("page") int page,
                                              @RequestParam(value = "limit", defaultValue = "2") int limit,
                                              @RequestParam(value = "sort-by", defaultValue = "id") String sort,
                                              @RequestParam(value = "order", defaultValue = "asc") String order
                                              ) {
        try {
            Pageable pageable;
            if (order.equals("asc")) {
                pageable = PageRequest.of(page, limit, Sort.by(sort).ascending());
            } else {
                pageable = PageRequest.of(page, limit, Sort.by(sort).descending());
            }

            Page<DMDichVu> dmDichVus;
            if (kw == null) {
                dmDichVus = dmDichVuService.findAll(pageable);
            } else {
                kw = "%" + kw + "%";
                dmDichVus = dmDichVuService.findAllByTenDvLike(kw, pageable);
            }

            List<DMDichVu> dmDichVuList = dmDichVus.getContent();
            List<DMDichVuResDTO> dmDichVuResDTOS = dmDichVuList.stream().map(DMDichVu::toDmDichVuResDTO).collect(Collectors.toList());
            Map<String, Object> response = new HashMap<>();
            response.put("dmDichVus", dmDichVuResDTOS);
            response.put("totalPages", dmDichVus.getTotalPages());
            response.put("totalItems", dmDichVus.getTotalElements());
            response.put("currentPage", dmDichVus.getNumber());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
