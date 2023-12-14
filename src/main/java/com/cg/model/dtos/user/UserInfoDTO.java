package com.cg.model.dtos.user;

import com.cg.model.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfoDTO {
    private String username;
    private String fullName;
    private ERole roleName;
    private String roleCode;
}
