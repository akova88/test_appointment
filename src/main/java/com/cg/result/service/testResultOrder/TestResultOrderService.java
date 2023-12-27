package com.cg.result.service.testResultOrder;

import com.cg.result.entity.TestResultOrder;
import com.cg.result.repository.TestResultOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestResultOrderService implements ITestResultOrderService{

    @Autowired
    private TestResultOrderRepository testResultOrderRepository;


    @Override
    public List<TestResultOrder> getAllTest(String maSoKham, String ngayKham) {
        List<ITestResultOrder> iTestResultOrders = testResultOrderRepository.getAllTest(maSoKham, ngayKham);
        List<TestResultOrder> testResultOrders = iTestResultOrders.stream().map(iTestResultOrder -> {
            TestResultOrder testResultOrder = new TestResultOrder();
            testResultOrder.setSTT(iTestResultOrder.getsoTT());
            testResultOrder.setGoiLuc(iTestResultOrder.getgoiLuc());
            testResultOrder.setKhoaTH(iTestResultOrder.getkhoaTH());
            testResultOrder.setMaDv(iTestResultOrder.getmaDv());
            testResultOrder.setTenDv(iTestResultOrder.gettenDv());
            testResultOrder.setNoiChoTH(iTestResultOrder.getnoiChoTH());
            testResultOrder.setNgayKq(iTestResultOrder.getngayKq());
            testResultOrder.setMaNoiDL(iTestResultOrder.getmaNoiDL());
            testResultOrder.setSoPhieu(iTestResultOrder.getsoPhieu());
            testResultOrder.setDaTT(iTestResultOrder.getdaTT());
            testResultOrder.setSoTT(iTestResultOrder.getsTT());
            return testResultOrder;
        }).collect(Collectors.toList());

        return testResultOrders;
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
