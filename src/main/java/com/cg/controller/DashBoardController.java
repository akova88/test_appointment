package com.cg.controller;

import com.cg.model.enums.ETime;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("/admin")
public class DashBoardController {

    @GetMapping
    public String dashboardPage(){
        return "dashboard/blank-dashboard-page";
    }

    @GetMapping("/appointments")
    public String appointmentPage(Model model){
        Map<String, String> times = new HashMap<>();
        for (ETime eTime: ETime.values()
             ) {
            times.put(eTime.name(),eTime.getValue());
        }

        model.addAttribute("times",times);

        return "dashboard/appointment/list";
    }

    @GetMapping("/customers")
    public String customersPage(){
        return "dashboard/customer/list";
    }
    @GetMapping("/medical-bills")
    public String billsPage(){
        return "dashboard/bill/list";
    }
    @GetMapping("/doctors")
    public String doctorsPage(){
        return "dashboard/doctor/list";
    }
    @GetMapping("/rooms")
    public String roomsPage(){
        return "dashboard/room/list";
    }
    @GetMapping("/specialities")
    public String specialitiesPage(){
        return "dashboard/speciality/list";
    }
    @GetMapping("/users")
    public String usersPage(){
        return "dashboard/user/list";
    }
    @GetMapping("/dm_nhom_dv")
    public String nhomDvPage() {
        return "dashboard/nhom_dv/list";
    }
    @GetMapping("/dm_dv")
    public String dichVuPage() {
        return "dashboard/dich_vu/list";
    }


}
