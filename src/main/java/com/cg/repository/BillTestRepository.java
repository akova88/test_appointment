package com.cg.repository;

import com.cg.model.BillTest;
import com.cg.model.dtos.billTest.BillTestResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillTestRepository extends JpaRepository<BillTest, Long> {
    @Query("SELECT NEW com.cg.model.dtos.billTest.BillTestResDTO(" +
            "bt.id," +
            "bt.maPhieu," +
            "bt.dmGoi," +
            "bt.takeDate," +
            "bt.timeBook," +
            "bt.fullName," +
            "bt.email," +
            "bt.gender," +
            "bt.phone," +
            "bt.DOB," +
            "bt.locationRegion," +
            "bt.reasonNote," +
            "bt.price," +
            "bt.homeService," +
            "bt.isConfirm," +
            "bt.staffConfirmed," +
            "bt.user" +
            ") FROM BillTest AS bt WHERE bt.isRemove = false ")
    List<BillTestResDTO> findAllBillTestResDTO();
}
