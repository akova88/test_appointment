package com.cg.repository;

import com.cg.model.DMNhomDV;
import com.cg.model.dtos.dmNhomDv.DMNhomDvResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DM_NhomDVRepository extends JpaRepository<DMNhomDV, Long> {

    @Query("SELECT NEW com.cg.model.dtos.dmNhomDv.DMNhomDvResDTO(" +
            "ndv.id, " +
            "ndv.maNhomDv, " +
            "ndv.tenNhomDv, " +
            "ndv.maChungLoai) FROM DMNhomDV AS ndv " +
            "where ndv.isRemove = false " +
            "ORDER BY ndv.id DESC")
    List<DMNhomDvResDTO> findAllDmNhomDvResDTO();

    DMNhomDV findDMNhomDVByMaNhomDv(String maNhomDmDichVu);
}
