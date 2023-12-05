package com.cg.repository;

import com.cg.model.MedicalBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalBillRepository extends JpaRepository<MedicalBill, Long> {
    List<MedicalBill> getAllByCustomer_Id(Long customerId);

    @Query("select m from MedicalBill as m where (m.customer.id = :id and m.isRemove = false and m.isPaid = false) ")
    List<MedicalBill> getAllUnpaidBillsByCus(@Param("id") Long customerId);

    @Query("select m from MedicalBill as m where (m.appointment.id = :id and m.isPaid = true )")
    List<MedicalBill> getPaidBillsByApp(@Param("id") Long appointmentId);
}
