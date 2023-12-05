package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.exception.EmailExistsException;
import com.cg.model.Customer;
import com.cg.model.Doctor;
import com.cg.model.LocationRegion;
import com.cg.model.User;
import com.cg.model.dtos.appointment.AppointmentResDTO;
import com.cg.model.dtos.customer.CustomerCreReqDTO;
import com.cg.model.dtos.customer.CustomerResDTO;
import com.cg.model.dtos.customer.CustomerUpReqDTO;
import com.cg.model.dtos.doctor.DoctorResDTO;
import com.cg.model.dtos.locationRegion.LocationRegionCreReqDTO;
import com.cg.model.dtos.locationRegion.LocationRegionUpReqDTO;
import com.cg.model.enums.EGender;
import com.cg.service.customer.ICustomerService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    AppUtils appUtils;

    @Autowired
    IUserService userService;

    @Autowired
    ICustomerService customerService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        List<CustomerResDTO> customerResDTOS = new ArrayList<>();
        List<Customer> customers = customerService.findAll();

        for (Customer customer: customers){
            if (!customer.isDeleted()) {
                CustomerResDTO customerResDTO = customer.toCustomerResDTO();
                customerResDTOS.add(customerResDTO);
            }
        }
        return new ResponseEntity<>(customerResDTOS,HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<?> getAll(@RequestParam(required = false) String kw,
//                                    @RequestParam("page") int page,
//                                    @RequestParam(value = "limit", defaultValue = "2") int limit,
//                                    @RequestParam(value = "sort-by", defaultValue = "id") String sort,
//                                    @RequestParam(value = "order", defaultValue = "asc") String order) {
//        Pageable pageable;
//        if (order.equals("asc")) {
//            pageable = PageRequest.of(page - 1, limit, Sort.by(sort).ascending());
//        } else {
//            pageable = PageRequest.of(page - 1, limit, Sort.by(sort).descending());
//        }
//        Page<Customer> customers;
//        if (kw == null) {
//            customers = customerService.findAll(pageable);
//        } else {
//            kw = "%"+kw+"%";
//            customers = customerService.findAllByFullNameLike(kw, pageable);
//        }
//
//        List<Customer> customerList = customers.getContent();
//        List<CustomerResDTO> customerResDTOS = new ArrayList<>();
//        for (Customer customer: customerList){
//            if (!customer.isDeleted()) {
//                CustomerResDTO customerResDTO = customer.toCustomerResDTO();
//                customerResDTOS.add(customerResDTO);
//            }
//        }
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("customers", customers);
//
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.findById(customerId).orElseThrow(() -> {
            throw new DataInputException("Mã khách hàng không tồn tại");
        });
        CustomerResDTO customerResDTO = customer.toCustomerResDTO();
        return new ResponseEntity<>(customerResDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CustomerCreReqDTO customerCreReqDTO,
                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            appUtils.mapErrorToResponse(bindingResult);
        }
        String email  = customerCreReqDTO.getEmail();
        Boolean emailExists = customerService.existsByEmail(email);
        if (emailExists) {
            throw new EmailExistsException("Email đã tồn tại");
        }

        String eGenderName = customerCreReqDTO.getNameGender();
        EGender eGender;
        try {
            eGender = EGender.valueOf(eGenderName);
        }catch (IllegalArgumentException e){
            throw new DataInputException("Giới tính không tồn tại");
        }

        LocationRegionCreReqDTO locationRegionCreReqDTO = customerCreReqDTO.getLocationRegion();
        LocationRegion locationRegion = locationRegionCreReqDTO.toLocationRegion();

        Long userid = Long.parseLong(customerCreReqDTO.getUserId());
        User user = userService.findById(userid).orElseThrow(() -> new DataInputException("Ngưới dùng không tồn tại"));



        Customer customer = customerCreReqDTO.toCustomer(eGender, user);
        Customer newCustomer = customerService.create(locationRegion, customer);

        CustomerResDTO customerResDTO = newCustomer.toCustomerResDTO();
        return new ResponseEntity<>(customerResDTO,HttpStatus.CREATED);
    }

    @PatchMapping("/{customerId}")
    public ResponseEntity<?> update(@PathVariable("customerId") String customerIdStr,
                                    @RequestBody CustomerUpReqDTO customerUpReqDTO,
                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            appUtils.mapErrorToResponse(bindingResult);
        }

        if (!ValidateUtil.isNumberValid(customerIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã khách hàng không đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }


        Long customerId = Long.parseLong(customerIdStr);
        Customer customer = customerService.findById(customerId).orElseThrow(()->{
            throw new DataInputException("Khách hàng không tồn tại");
        });

        String email  = customerUpReqDTO.getEmail();
        Boolean emailExists = customerService.existsByEmailAndIdNot(email, customerId);
        if (emailExists) {
            throw new EmailExistsException("Email đã tồn tại");
        }

        Long locationRegionId = customer.getLocationRegion().getId();
        User user = customer.getUser();

        String eGenderName = customerUpReqDTO.getNameGender();
        EGender eGender;
        try {
            eGender = EGender.valueOf(eGenderName);
        }catch (IllegalArgumentException e){
            throw new DataInputException("Giới tính không tồn tại");
        }

        LocationRegionUpReqDTO locationRegionUpReqDTO = customerUpReqDTO.getLocationRegion();
        LocationRegion locationRegion = locationRegionUpReqDTO.toLocationRegion(locationRegionId);

        Customer updatedCustomer = customerUpReqDTO.toCustomer(eGender,user,customerId,locationRegionId);
        Customer newCustomer = customerService.create(locationRegion, updatedCustomer);
        CustomerResDTO customerResDTO = newCustomer.toCustomerResDTO();

        return new ResponseEntity<>(customerResDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<?> suspend(@PathVariable("customerId") String customerIdStr) {

        if (!ValidateUtil.isNumberValid(customerIdStr)){
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã khách hàng không đúng định dạng");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long customerId = Long.parseLong(customerIdStr);
        Customer customer = customerService.findById(customerId).orElseThrow(() -> {
            throw  new DataInputException("Khách hàng không tồn tại");
        });

        customer.setDeleted(true);

        Customer newCustomer = customerService.save(customer);
        CustomerResDTO customerResDTO = newCustomer.toCustomerResDTO();
        return new ResponseEntity<>(customerResDTO, HttpStatus.OK);
    }


}
