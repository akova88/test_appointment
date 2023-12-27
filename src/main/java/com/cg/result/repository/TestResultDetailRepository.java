package com.cg.result.repository;

import com.cg.result.entity.DM_GioiTinh;
import com.cg.result.service.testResultDetail.ITestResultDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultDetailRepository extends JpaRepository<DM_GioiTinh, Integer> {
    @Query(value = "exec spKetQuaLanXN ?1, ?2", nativeQuery = true)
    List<ITestResultDetail> getAllTestDetail(@Param("SID") String maSoKham, @Param("LoaiKQ") String loaiKq);
}
