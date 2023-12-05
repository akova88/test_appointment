package com.cg.model.dtos.doctor;

import com.cg.model.Doctor;
import com.cg.model.Speciality;
import com.cg.model.User;
import com.cg.model.dtos.locationRegion.LocationRegionUpReqDTO;
import com.cg.model.enums.EGender;
import com.cg.model.enums.ELevel;
import com.cg.utils.DateFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DoctorUpReqDTO {
    @NotNull(message = "Tên không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Tên")
    @NotEmpty(message = "Tên không thể để trống")
    @Size(min = 5, max = 25, message = "Tên phải từ 5 - 25 ký tự")
    private String fullName;

    @NotNull(message = "Email không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Email")
    @NotEmpty(message = "Email không thể để trống")
    @Size(min = 5, max = 25, message = "Email phải từ 5 - 25 ký tự")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$", message = "Email phải đúng định dạnh abc@abc.abc")
    private String email;

    @NotNull(message = "Giới tính không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Giới tính")
    @NotEmpty(message = "Giới tính không thể để trống")
    @Pattern(regexp = "^(FEMALE|MALE)$", message = "Giới tính không hợp lệ")
    private String nameGender;

    @NotNull(message = "Số điện thoại không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Số điện thoại")
    @NotEmpty(message = "Số điện thoại không thể để trống")
    private String phone;

    @NotNull(message = "Ngày sinh không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Ngày sinh")
    @NotEmpty(message = "Ngày sinh không thể để trống")
    @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/\\d{4}$", message = "Ngày sinh phải theo định dạng dd/MM/yyyy")
    private String birthDay;

    private String job;

    @NotNull(message = "CMND không thể null")
    @NotBlank(message = "Hãy nập giá trị cho CMND")
    @NotEmpty(message = "CMND không thể để trống")
    @Pattern(regexp = "^\\d{9}$", message = "CMND phải là một số gồm 9 chữ số")
    private String identityNumber;

    @NotNull(message = "Dân tộc không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Dân tộc")
    @NotEmpty(message = "Dân tộc không thể để trống")
    private String ethnic;

    private LocationRegionUpReqDTO locationRegion;

    @NotNull(message = "ID chuyên khoa không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ID chuyên khoa")
    @NotEmpty(message = "ID chuyên khoa không thể để trống")
    @Pattern(regexp = "\\d+", message = "ID chuyên khoa phải là một số")
    private String specialityId;

    @NotNull(message = "Trình độ không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Trình độ")
    @NotEmpty(message = "Trình độ không thể để trống")
    @Pattern(regexp = "^(BS|BSCKI|BSCKII)$", message = "Trình độ không hợp lệ")
    private String levelName;

    public Doctor toDoctor(EGender eGender, Long doctorId, Long locationRegionId, User user, Speciality speciality, ELevel eLevel) {
        return new Doctor(fullName, email, eGender,phone, DateFormat.parse(birthDay),job, identityNumber,
                ethnic,doctorId,locationRegion.toLocationRegion(locationRegionId),user,speciality,eLevel);
    }
}
