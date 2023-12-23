package com.cg.result.repository;

import com.cg.result.entity.DMGioiTinhDTO;
import com.cg.result.entity.DM_GioiTinh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DM_GioiTinhRepository extends JpaRepository<DM_GioiTinh, Integer> {
    @Query(value = "SELECT gt.Id, gt.Ma, gt.TenGioiTinh FROM CNTT_Dm_GioiTinh as gt", nativeQuery = true)
    List<DM_GioiTinh> getAllGioiTinh();

    @Query(value = "execute sp_DmGioiTinh", nativeQuery = true)
    List<IDMGioiTinh> getAllGt();

    @Query(value = "execute sp_DmGioiTinh", nativeQuery = true)
    List<DMGioiTinhDTO> getAllGt1();
}
