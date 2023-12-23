package com.cg.result.service.testResultOrder;

import com.cg.result.entity.TestResultOrder;
import com.cg.result.repository.TestResultOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TestResultOrderService implements ITestResultOrderService{

    @Autowired
    private TestResultOrderRepository testResultOrderRepository;


    @Override
    public List<TestResultOrder> getAllTest(String maSoKham) {
        return testResultOrderRepository.getAllTest(maSoKham);
    }

    @Override
    public List<TestResultOrder> findAll() {
        return null;
    }

    @Override
    public Optional<TestResultOrder> findById(String id) {
        return Optional.empty();
    }

    @Override
    public TestResultOrder save(TestResultOrder testResultOrder) {
        return null;
    }

    @Override
    public void delete(TestResultOrder testResultOrder) {

    }

    @Override
    public void deleteById(String id) {

    }
}
