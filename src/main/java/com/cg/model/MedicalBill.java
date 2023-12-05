package com.cg.model;

import com.cg.model.dtos.appointment.AppointmentResDTO;
import com.cg.model.dtos.medicalBill.MedicalBillResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "medical_bills")
@Accessors(chain = true)
@Entity
public class MedicalBill extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id", nullable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;

    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;

    @Column(name = "paid_date")
    private Date paidDate;

    public MedicalBillResDTO toMedicalBillResDTO(){
        return new MedicalBillResDTO()
                .setId(id)
                .setCode(code)
                .setAppointment(appointment.toAppointmentResDTO())
                .setCustomer(customer.toCustomerResDTO())
                .setPaid(isPaid)
                .setPaidDate(paidDate);
    }
}
