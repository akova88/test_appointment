package com.cg.result.service.testResultDetail;

import com.cg.result.entity.TestResultDetail;
import com.cg.result.service.testResultOrder.ITestResultOrder;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITestResultDetailService extends IGeneralService<TestResultDetail, String> {
    List<TestResultDetail> getAllTestDetail(@Param("SID") String maSoKham, @Param("LoaiKQ") String loaiKq);
}
