package com.cg.model.dtos.room;


import com.cg.model.Room;
import com.cg.model.Speciality;
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
public class RoomCreReqDTO {

    @NotNull(message = "ID chuyên khoa không thể null")
    @NotBlank(message = "Hãy nập giá trị cho ID chuyên khoa")
    @NotEmpty(message = "ID chuyên khoa không thể để trống")
    @Pattern(regexp = "\\d+", message = "ID chuyên khoa phải là một số")
    private String specialityId;

    @NotNull(message = "Tên phòng không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Tên phòng")
    @NotEmpty(message = "Tên phòng không thể để trống")
    @Pattern(regexp = "^B[1-9]-F[1-9]-R[1-9]$", message = "Tên phòng không hợp lệ. Vui lòng nhập theo định dạng Bx-Fx-Rx")
    private String name;

    public Room toRoom(Speciality speciality){
        return new Room()
                .setId(null)
                .setSpeciality(speciality)
                .setName(name)
                .setIsAvailable(true);
    }

}
