package com.cg.model.dtos.medicalBill;


import com.cg.model.Appointment;
import com.cg.model.Customer;
import com.cg.model.MedicalBill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicalBillCreReqDTO {

    @NotNull(message = "ID lịch khám không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ID lịch khám")
    @NotEmpty(message = "ID lịch khám không thể để trống")
    @Pattern(regexp = "\\d+", message = "ID lịch khám phải là một số")
    private String appointmentId;

    @NotNull(message = "ID khách hàng không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ID khách hàng")
    @NotEmpty(message = "ID khách hàng không thể để trống")
    @Pattern(regexp = "\\d+", message = "ID khách hàng phải là một số")
    private String customerId;

    public MedicalBill toMedicalBill(Appointment appointment, Customer customer){
        return new MedicalBill()
                .setId(null)
                .setAppointment(appointment)
                .setCustomer(customer)
                .setPaid(false);
    }
}
