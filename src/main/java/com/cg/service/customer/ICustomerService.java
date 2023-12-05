package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.LocationRegion;
import com.cg.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer, Long> {
    Customer create(LocationRegion locationRegion, Customer customer);

    Customer findCustomerByUserId(Long userId);

    List<Customer> findAllByUser_Id(Long userId);

    Boolean existsByEmail(String email);

    Boolean existsByEmailAndIdNot(String email, Long id);

    Customer findCustomerByUserIdAndDeletedFalse(Long userId);
    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAllByFullNameLike (String fullName, Pageable pageable);

}
