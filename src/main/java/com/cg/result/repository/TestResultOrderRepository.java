package com.cg.result.repository;

import com.cg.result.entity.DM_GioiTinh;
import com.cg.result.service.testResultOrder.ITestResultOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultOrderRepository extends JpaRepository<DM_GioiTinh, Integer> {

    @Query(value = "exec Phuoc_DsPhieuKetQuaKB ?1, ?2", nativeQuery = true)
    List<ITestResultOrder> getAllTest(@Param("MaSoKham") String maSoKham, @Param("NgayKham") String ngayKham);
}
