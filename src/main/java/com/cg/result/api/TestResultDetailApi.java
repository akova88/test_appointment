package com.cg.result.api;


import com.cg.result.entity.TestResultDetail;
import com.cg.result.entity.dto.TestResultDetailReqDTO;
import com.cg.result.service.testResultDetail.ITestResultDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/result_details")
public class TestResultDetailApi {
    @Autowired
    private ITestResultDetailService testResultDetailService;

    @PostMapping
    public ResponseEntity getResultDetailBySIDAndLoaiKq(@RequestBody TestResultDetailReqDTO testResultDetailReqDTO) {
        String SID = testResultDetailReqDTO.getSoPhieu();
        String loaiKQ = testResultDetailReqDTO.getLoaiKQ();

        List<TestResultDetail> testResultDetails = testResultDetailService.getAllTestDetail(SID, loaiKQ);
        return new ResponseEntity<>(testResultDetails, HttpStatus.OK);
    }
}
