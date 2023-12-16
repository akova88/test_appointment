package com.cg.repository;

import com.cg.model.DM_Goi;
import com.cg.model.dtos.dm_goi.DM_GoiResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DM_GoiRepository extends JpaRepository<DM_Goi, Long> {

//    @Query("SELECT NEW com.cg.model.dtos.dm_goi.DM_GoiResDTO(" +
//            "goi.id," +
//            "goi.maGoi," +
//            "goi.tenGoi," +
//            "goi.giaGoi," +
//            "goi.dmDichVuList) FROM DM_Goi as goi where goi.isRemove = false")
//    List<DM_GoiResDTO> findAllDmGoiResDTO();

    DM_Goi findDM_GoiByMaGoi(String maGoi);

    Boolean existsDM_GoiByMaGoiOrTenGoi(String maGoi, String tenGoi);

    Boolean existsDM_GoiByMaGoiOrTenGoiAndIdNot(String maGoi, String tenGoi, Long id);
}
