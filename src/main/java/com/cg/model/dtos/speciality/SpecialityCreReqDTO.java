package com.cg.model.dtos.speciality;

import com.cg.model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpecialityCreReqDTO {
    @NotNull(message = "Mã chuyên khoa không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Mã chuyên khoa")
    @NotEmpty(message = "Mã chuyên khoa không thể để trống")
    @Size(min = 3, max = 7, message = "Mã chuyên khoa phải từ 3 - 7 ký tự")
    private String codeName;

    @NotNull(message = "Tên chuyên khoa không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Tên chuyên khoa")
    @NotEmpty(message = "Tên chuyên khoa không thể để trống")
    @Size(min = 3, max = 25, message = "Tên chuyên khoa phải từ 3 - 25 ký tự")
    private String name;

    public Speciality toSpeciality(){
        return new Speciality()
                .setId(null)
                .setCodeName(codeName)
                .setName(name);
    }


}
