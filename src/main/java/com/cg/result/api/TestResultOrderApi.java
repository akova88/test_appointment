package com.cg.result.api;

import com.cg.result.entity.TestResultOrder;
import com.cg.result.service.testResultOrder.ITestResultOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test_results")
public class TestResultOrderApi {
    @Autowired
    private ITestResultOrderService testResultOrderService;

    @GetMapping("/{maSoKham}")
    public ResponseEntity<?> getAll(@PathVariable String maSoKham) {
        List<TestResultOrder> testResultOrders = testResultOrderService.getAllTest(maSoKham);
        return new ResponseEntity<>(testResultOrders, HttpStatus.OK);
    }
}
