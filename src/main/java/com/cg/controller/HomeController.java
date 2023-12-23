package com.cg.controller;

import com.cg.model.*;
import com.cg.model.dtos.medicalBill.MedicalBillResDTO;
import com.cg.model.enums.EBookTime;
import com.cg.model.enums.EGender;
import com.cg.model.enums.ETime;
import com.cg.service.appointment.AppointmentService;
import com.cg.service.customer.ICustomerService;
import com.cg.service.doctor.DoctorService;
import com.cg.service.medicalBill.IMedicalBillService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IMedicalBillService medicalBillService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/login")
    public String login() {
        return "dashboard/user/login";

    }

    @GetMapping("/register")
    public String register() {
        return "dashboard/user/register";
    }

    @GetMapping
    public ModelAndView accessHomepage(Principal user) {
        ModelAndView model = new ModelAndView("homepage/index");
        if (user == null) {
            return model;
        }
        String username = user.getName();
        User user1 = userService.getByUsername(username);
        String roleName = user1.getRole().getName().name();
        model.addObject("user", user);
        model.addObject("username", username);
        model.addObject("roleName", roleName);
        return model;
    }

    @GetMapping("/profile")
    public ModelAndView profilePage(Principal user) {
        ModelAndView model = new ModelAndView("homepage/profile");
        if (user == null) {
            return model;
        }
        ModelAndView modelChoose = new ModelAndView("homepage/profile-choose");
        String username = user.getName();
        User user1 = userService.getByUsername(username);
        Long userId = user1.getId();
        String roleName = user1.getRole().getName().name();
        model.addObject("roleName", roleName);

        if (roleName.equals("ROLE_DOCTOR")){
            Doctor doctor = doctorService.findDoctorByUserId(userId);
            if (doctor == null || doctor.isDeleted()){
                model.addObject("user", user);
                return model;
            }
            modelChoose.addObject("customer", doctor);
        }else {
            Customer customer = customerService.findCustomerByUserIdAndDeletedFalse(userId);
            if (customer == null || customer.isDeleted()) {
                model.addObject("user", user);
                return model;
            }
            modelChoose.addObject("customer", customer);
        }


        modelChoose.addObject("user", user);
        return modelChoose;
    }

    @GetMapping("/profile-create")
    public ModelAndView profilePageCreate(Principal user) {
        if (user == null) {
            return new ModelAndView("redirect:/login");
        }
        String username = user.getName();
        User user1 = userService.getByUsername(username);
        Long userId = user1.getId();

        Customer customer = customerService.findCustomerByUserIdAndDeletedFalse(userId);
        if (customer != null){
            return new ModelAndView("redirect:/profile");
        }

        ModelAndView modelAndView = new ModelAndView("homepage/profile-create");
        String roleName = user1.getRole().getName().name();
        modelAndView.addObject("roleName", roleName);
        modelAndView.addObject("user", user);
        modelAndView.addObject("userId", userId);
        return modelAndView;
    }

    @GetMapping("/profile-update")
    public ModelAndView profilePageUpdate(Principal user) {
        String username = user.getName();
        User user1 = userService.getByUsername(username);
        Long userId = user1.getId();
        Customer customer = customerService.findCustomerByUserIdAndDeletedFalse(userId);

        ModelAndView modelAndView = new ModelAndView("homepage/profile-update");
        String roleName = user1.getRole().getName().name();
        modelAndView.addObject("roleName", roleName);
        modelAndView.addObject("user", user);
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @GetMapping("/choose-appointment")
    public String chooseAppointment(Model model, Principal user1){

        String username = appUtils.getPrincipalUsername();
        User user = userService.getByUsername(username);
        Long userId = user.getId();
        Customer customer = customerService.findCustomerByUserIdAndDeletedFalse(userId);

        if (customer == null){

            return "redirect:/profile";
        }

        Long customerId = customer.getId();

        model.addAttribute("customerId", customerId);

        Map<String, String> times = new HashMap<>();
        for (ETime eTime : ETime.values()
        ) {
            times.put(eTime.name(), eTime.getValue());
        }

        String roleName = user.getRole().getName().name();
        model.addAttribute("roleName", roleName);
        model.addAttribute("times",times);
        model.addAttribute("user",user1);

        return "homepage/choose-appointment";
    }

    @GetMapping("/appointment-confirm")
    public String cart(Model model, Principal user1){
        String username = appUtils.getPrincipalUsername();
        User user = userService.getByUsername(username);
        Long userId = user.getId();
        Customer customer = customerService.findCustomerByUserIdAndDeletedFalse(userId);

        if (customer == null){

            return "redirect:/profile";
        }
        Long customerId = customer.getId();

        String roleName = user.getRole().getName().name();
        model.addAttribute("roleName", roleName);
        model.addAttribute("customer",customer);
        model.addAttribute("customerId",customerId);
        model.addAttribute("user",user1);

        return "homepage/appointment-confirm";
    }


    @GetMapping("/customer")
    public ModelAndView customerPage(Principal user) {
        String username = user.getName();
        User user1 = userService.getByUsername(username);
        Long userId = user1.getId();
        Customer customer = customerService.findCustomerByUserIdAndDeletedFalse(userId);
        ModelAndView modelAndView = new ModelAndView("homepage/customer");
        String roleName = user1.getRole().getName().name();
        modelAndView.addObject("roleName", roleName);
        modelAndView.addObject("user", user);
        modelAndView.addObject("userId", userId);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PreAuthorize("hasAnyAuthority('DOCTOR')")
    @GetMapping("/doctor")
    public String doctor(Model model, Principal user) {
        String username = user.getName();
        User user1 = userService.getByUsername(username);

        Map<String, String> times = new HashMap<>();
        for (ETime eTime : ETime.values()
        ) {
            times.put(eTime.name(), eTime.getValue());
        }
        String roleName = user1.getRole().getName().name();
        model.addAttribute("roleName", roleName);
        model.addAttribute("times", times);
        model.addAttribute("user", user);

        return "homepage/doctor";
    }


    @GetMapping("/checkout")
    public String checkout(Model model, Principal user1){
        String username = appUtils.getPrincipalUsername();
        User user = userService.getByUsername(username);
        Long userId = user.getId();
        Customer customer = customerService.findCustomerByUserIdAndDeletedFalse(userId);

        if (customer == null){

            return "redirect:/profile";
        }
        Long customerId = customer.getId();

        model.addAttribute("customer",customer);
        model.addAttribute("customerId",customerId);

        List<MedicalBill> medicalBills = medicalBillService.getAllByCustomer_Id(customerId);
        List<MedicalBillResDTO> medicalBillResDTOS = new ArrayList<>();
        BigDecimal prices = BigDecimal.ZERO;
        BigDecimal fee = BigDecimal.ZERO;
        for (MedicalBill medicalBill: medicalBills){
            if (!medicalBill.isPaid() && !medicalBill.isDeleted()){
                MedicalBillResDTO medicalBillResDTO = medicalBill.toMedicalBillResDTO();
                medicalBillResDTOS.add(medicalBillResDTO);
                prices = prices.add(medicalBill.getAppointment().getPrice());
                fee = fee.add(BigDecimal.valueOf(5000L));
            }
        }
        BigDecimal total = prices.add(fee);

        String roleName = user.getRole().getName().name();
        model.addAttribute("roleName", roleName);
        model.addAttribute("medicalBillResDTOS",medicalBillResDTOS);
        model.addAttribute("total",total);
        model.addAttribute("fee",fee);
        model.addAttribute("prices",prices);
        model.addAttribute("user",user1);

        return "homepage/checkout";
    }

    @GetMapping("/hinh-thuc-dat-kham")
    public String booking() {
        return "homepage/hinh-thuc-dat-kham";
    }

    @GetMapping("/tra-cuu-ket-qua")
    public String bookingLookup() {
        return "homepage/tra-cuu-ket-qua";
    }

    @GetMapping("/dat-lich-thanh-cong")
    public String bookSuccess() {
        return "homepage/dat-lich-thanh-cong";
    }

    @GetMapping("/phieu-ket-qua")
    public String resultLookup() {
        return "homepage/fragment/result/return-table";
    }

    @GetMapping("/dat-lich-lay-mau")
    public ModelAndView datLich(Principal user) {
        ModelAndView model = new ModelAndView("homepage/dat-lich-lay-mau");

        Map<String, String> times = new HashMap<>();
        Map<String, String> genders = new HashMap<>();
        for (EBookTime eBookTime : EBookTime.values()
        ) {
            times.put(eBookTime.name(), eBookTime.getValue());
        }

        for (EGender eGender : EGender.values()) {
            genders.put(eGender.name(), eGender.getValue());
        }

        model.addObject("times", times);
        model.addObject("genders", genders);
        if (user == null) {
            return model;
        }
        String username = user.getName();
        User user1 = userService.getByUsername(username);
        Long userId = user1.getId();

        String roleName = user1.getRole().getName().name();
        model.addObject("roleName", roleName);
        model.addObject("user", user);
        model.addObject("userId", userId);

        return model;
    }
}
