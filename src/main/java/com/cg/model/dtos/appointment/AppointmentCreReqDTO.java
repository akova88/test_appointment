package com.cg.model.dtos.appointment;

import com.cg.model.Appointment;
import com.cg.model.Doctor;
import com.cg.model.Room;
import com.cg.model.Speciality;
import com.cg.model.enums.ETime;
import com.cg.utils.DateFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppointmentCreReqDTO {

    @NotNull(message = "ID bác sĩ không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ID bác sĩ")
    @NotEmpty(message = "ID bác sĩ không thể để trống")
    @Pattern(regexp = "\\d+", message = "ID bác sĩ phải là một số")
    private String doctorId;

    @NotNull(message = "ID phòng không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ID phòng")
    @NotEmpty(message = "ID phòng không thể để trống")
    @Pattern(regexp = "\\d+", message = "ID phòng phải là một số")
    private String roomId;

    @NotNull(message = "Ngày khám không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ngày khám")
    @NotEmpty(message = "Ngày khám không thể để trống")
    @DateTimeFormat(pattern = "DD/MM/yyyy")
    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/\\d{4}$", message = "Ngày khám phải đúng định dạng dd/MM/yyyy")
    private String day;

    @NotNull(message = "Giờ khám không thể null")
    @NotBlank(message = "Hãy nập giá trị cho giờ khám")
    @NotEmpty(message = "Giờ khám không thể để trống")
    @Pattern(regexp = "^(C[1-3]|S[1-3])$", message = "Giờ khám không hợp lệ")
    private String timeName;

    @NotNull(message = "ID chuyên khoa không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ID chuyên khoa")
    @NotEmpty(message = "ID chuyên khoa không thể để trống")
    @Pattern(regexp = "\\d+", message = "ID chuyên khoa phải là một số")
    private String specialityId;

    public Appointment toAppointment(Doctor doctor, Room room, ETime eTime, Speciality speciality){
        return new Appointment()
                .setId(null)
                .setDoctor(doctor)
                .setRoom(room)
                .setDay(DateFormat.parse(day))
                .setTime(eTime)
                .setPrice(BigDecimal.valueOf(150000L))
                .setAvailable(true)
                .setSpeciality(speciality);
    }


}
