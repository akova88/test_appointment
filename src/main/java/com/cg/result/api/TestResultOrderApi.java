package com.cg.result.api;

import com.cg.exception.DataInputException;
import com.cg.result.entity.TestResultOrder;
import com.cg.result.entity.dto.PatientSearchDTO;
import com.cg.result.service.testResultOrder.ITestResultOrderService;
import com.cg.utils.AppUtils;
import com.cg.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/test_results")
public class TestResultOrderApi {
    @Autowired
    private ITestResultOrderService testResultOrderService;

    @Autowired
    private AppUtils appUtils;

    @PostMapping
    public ResponseEntity<?> getAll(@Valid @RequestBody PatientSearchDTO patientSearchDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return appUtils.mapErrorToResponse(bindingResult);

        String maTraCuu = patientSearchDTO.getMaTraCuu();
        String maBaoMat = patientSearchDTO.getMaBaoMat();
        String ngayKham = patientSearchDTO.getNgayKham();

        Date inputDate = DateFormat.parse(ngayKham);
        String outputDate = DateFormat.formatOut(inputDate);

        String maSoKham = maBaoMat+ "." + maTraCuu;
        List<TestResultOrder> testResultOrders = testResultOrderService.getAllTest(maSoKham, outputDate);

        if (testResultOrders.isEmpty()) {
            throw new DataInputException("Thông tin tra cứu không chính xác vui lòng kiểm tra lại");
        }

        return new ResponseEntity<>(testResultOrders, HttpStatus.OK);
    }
}
