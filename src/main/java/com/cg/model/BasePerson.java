package com.cg.model;

import com.cg.model.enums.EGender;
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
@Accessors(chain = true)
@MappedSuperclass
public class BasePerson extends BaseEntity{

    @Column(nullable = false, name = "full_name", columnDefinition = "nvarchar(255)")
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Column(unique = true, nullable = false)
    private String phone;

    @Column(nullable = false)
    private Date DOB;

    @Column(columnDefinition = "nvarchar(255)")
    private String job;

    @Column(nullable = false, unique = true)
    private String identityNumber;

    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    private String ethnic;

}
