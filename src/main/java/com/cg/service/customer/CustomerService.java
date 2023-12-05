package com.cg.service.customer;

import com.cg.model.Customer;
import com.cg.model.LocationRegion;
import com.cg.repository.CustomerRepository;
import com.cg.repository.LocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService implements ICustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LocationRegionRepository locationRegionRepository;
    @Override
    public Customer create(LocationRegion locationRegion, Customer customer) {
        LocationRegion locationRegion1 = locationRegionRepository.save(locationRegion);
        customer.setLocationRegion(locationRegion1);
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Page<Customer> findAllByFullNameLike(String fullName, Pageable pageable) {
        return customerRepository.findAllByFullNameLike(fullName, pageable);
    }

    @Override
    public List<Customer> findAllByUser_Id(Long userId) {
        return customerRepository.findAllByUser_Id(userId);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findCustomerByUserIdAndDeletedFalse(Long userId) {
        return customerRepository.findCustomerByUserIdAndDeletedFalse(userId);
    }

    @Override
    public Customer findCustomerByUserId(Long userId) {
        return customerRepository.findCustomerByUserId(userId);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByEmailAndIdNot(String email, Long id) {
        return customerRepository.existsByEmailAndIdNot(email, id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
