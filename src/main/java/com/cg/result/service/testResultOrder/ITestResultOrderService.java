package com.cg.result.service.testResultOrder;

import com.cg.result.entity.TestResultOrder;
import com.cg.service.IGeneralService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITestResultOrderService extends IGeneralService<TestResultOrder, String> {
    List<TestResultOrder> getAllTest(@Param("maSoKham") String maSoKham);
}
