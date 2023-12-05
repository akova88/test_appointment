package com.cg.repository;

import com.cg.model.Customer;
import com.cg.model.Doctor;
import com.cg.model.dtos.customer.CustomerResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByUserId(Long userId);
    List<Customer> findAllByUser_Id(Long userId);
    Boolean existsByEmail(String email);

    Boolean existsByEmailAndIdNot(String email, Long id);

    @Query(value = "SELECT c FROM Customer AS c WHERE c.user.id = :userId AND c.isRemove = false ")
    Customer findCustomerByUserIdAndDeletedFalse(@Param("userId") Long userId);
    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAllByFullNameLike(String fullName, Pageable pageable);
}
