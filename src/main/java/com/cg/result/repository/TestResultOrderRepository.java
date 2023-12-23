package com.cg.result.repository;

import com.cg.result.entity.DM_GioiTinh;
import com.cg.result.entity.TestResultOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultOrderRepository extends JpaRepository<DM_GioiTinh, Integer> {

    @Query(value = "exec spDsPhieuKetQuaKB :maSoKham", nativeQuery = true)
    List<TestResultOrder> getAllTest(@Param("maSoKham") String maSoKham);
}
