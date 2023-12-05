package com.cg.api;

import com.cg.model.DM_Goi;
import com.cg.model.dtos.dm_goi.DM_GoiResDTO;
import com.cg.service.dm_goi.IDMGoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dm_goi")
public class Dm_GoiAPI {
    @Autowired
    private IDMGoiService dmGoiService;

    @GetMapping
    public ResponseEntity<?> getAllDmGoi() {
        List<DM_Goi> dmGoiResDTOS = dmGoiService.findAll();
        return new ResponseEntity<>(dmGoiResDTOS, HttpStatus.OK);
    }
}
