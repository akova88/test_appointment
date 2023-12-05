package com.cg.model.dtos.medicalBill;

import com.cg.model.Appointment;
import com.cg.model.Customer;
import com.cg.model.dtos.appointment.AppointmentResDTO;
import com.cg.model.dtos.customer.CustomerResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class MedicalBillResDTO {

    private Long id;

    private String code;

    private AppointmentResDTO appointment;

    private CustomerResDTO customer;

    private boolean isPaid;

    private Date paidDate;
}
