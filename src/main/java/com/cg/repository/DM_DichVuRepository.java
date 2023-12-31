package com.cg.repository;

import com.cg.model.DMDichVu;
import com.cg.model.dtos.dmDichVu.DMDichVuResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DM_DichVuRepository extends JpaRepository<DMDichVu, Long> {

   @Query("SELECT NEW com.cg.model.dtos.dmDichVu.DMDichVuResDTO(" +
           "dv.id," +
           "dv.maDv," +
           "dv.tenDv," +
           "dv.donGiaTT," +
           "dv.dmNhomDV) FROM DMDichVu AS dv WHERE dv.isRemove = false ")
   List<DMDichVuResDTO> findAllDMDichVuResDTO();

   Boolean existsDMDichVuByTenDv(String tenDv);

   Boolean existsDMDichVuByTenDvAndAndIdNot(String tenDv, Long id);
}
