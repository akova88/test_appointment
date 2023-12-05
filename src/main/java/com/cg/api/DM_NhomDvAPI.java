package com.cg.api;

import com.cg.model.dtos.dmNhomDv.DMNhomDvResDTO;
import com.cg.service.dm_nhom_dv.IDMNhomDvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dm_nhom")
public class DM_NhomDvAPI {

    @Autowired
    private IDMNhomDvService dmNhomDvService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<DMNhomDvResDTO> dmNhomDvResDTOS = dmNhomDvService.findAllDmNhomDvResDTO();
        return new ResponseEntity<>(dmNhomDvResDTOS, HttpStatus.OK);
    }
}
