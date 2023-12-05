package com.cg.model.dtos.user;


import com.cg.model.Role;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegisterReqDTO {

    @NotNull(message = "Username không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Username")
    @NotEmpty(message = "Username không thể để trống")
    @Size(min = 3, max = 20, message = "Username phải từ 3 - 20 ký tự")
    private String username;

    @NotNull(message = "Password không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Password")
    @NotEmpty(message = "Password không thể để trống")
    @Size(min = 3, max = 20, message = "Password phải từ 3 - 20 ký tự")
    private String password;

    @NotNull(message = "Password không thể null")
    @NotBlank(message = "Hãy nập giá trị cho Password")
    @NotEmpty(message = "Password không thể để trống")
    @Size(min = 3, max = 20, message = "Password phải từ 3 - 20 ký tự")
    private String rePassword;

    public User toUser(Role role) {
        return new User()
                .setUsername(username)
                .setPassword(password)
                .setRole(role)
                ;
    }
}