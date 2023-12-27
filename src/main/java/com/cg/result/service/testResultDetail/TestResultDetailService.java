package com.cg.result.service.testResultDetail;

import com.cg.result.entity.TestResultDetail;
import com.cg.result.repository.TestResultDetailRepository;
import com.cg.result.service.testResultOrder.ITestResultOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestResultDetailService implements ITestResultDetailService{

    @Autowired
    private TestResultDetailRepository testResultDetailRepository;


    @Override
    public List<TestResultDetail> findAll() {
        return null;
    }

    @Override
    public List<TestResultDetail> getAllTestDetail(String maSoKham, String loaiKq) {
        List<ITestResultDetail> iTestResultDetails = testResultDetailRepository.getAllTestDetail(maSoKham, loaiKq);
        List<TestResultDetail> testResultDetails = iTestResultDetails.stream().map(iTestResultDetail -> {
            TestResultDetail testResultDetail = new TestResultDetail()
                    .setSID(iTestResultDetail.getSID())
                    .setUpdatetime(iTestResultDetail.getUpdatetime())
                    .setIntime(iTestResultDetail.getIntime())
                    .setResultTime(iTestResultDetail.getResultTime())
                    .setPID(iTestResultDetail.getPID())
                    .setOrderId(iTestResultDetail.getOrderId())
                    .setLocationID(iTestResultDetail.getLocationID())
                    .setNoiChiDinh(iTestResultDetail.getNoiChiDinh())
                    .setPatientName(iTestResultDetail.getPatientName())
                    .setSex(iTestResultDetail.getSex())
                    .setAge(iTestResultDetail.getAge())
                    .setAddress(iTestResultDetail.getAddress())
                    .setPDescription(iTestResultDetail.getPDescription())
                    .setPDoctorName(iTestResultDetail.getPDoctorName())
                    .setTestcode(iTestResultDetail.getTestcode())
                    .setTestName(iTestResultDetail.getTestName())
                    .setResult(iTestResultDetail.getResult())
                    .setUnit(iTestResultDetail.getUnit())
                    .setNormalRange(iTestResultDetail.getNormalRange())
                    .setColor(iTestResultDetail.getColor())
                    .setTestHead(iTestResultDetail.getTestHead())
                    .setCategory(iTestResultDetail.getCategory())
                    .setNoiThucHien(iTestResultDetail.getNoiThucHien())
                    .setKhoaThucHien(iTestResultDetail.getKhoaThucHien())
                    .setOrderTestCode(iTestResultDetail.getOrderTestCode())
                    .setMaDV(iTestResultDetail.getMaDV())
                    .setTenDV(iTestResultDetail.getTenDV());
            return testResultDetail;
        }).collect(Collectors.toList());

        return testResultDetails;
    }

    @Override
    public Optional<TestResultDetail> findById(String id) {
        return Optional.empty();
    }

    @Override
    public TestResultDetail save(TestResultDetail testResultDetail) {
        return null;
    }

    @Override
    public void delete(TestResultDetail testResultDetail) {

    }

    @Override
    public void deleteById(String id) {

    }
}
