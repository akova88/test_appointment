package com.cg.model;

import com.cg.model.dtos.speciality.SpecialityResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "specialities")
@Accessors(chain = true)
public class Speciality extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, name = "code_name")
    private String codeName;

    @Column(unique = true, nullable = false, columnDefinition = "nvarchar(255)")
    private String name;

     public SpecialityResDTO toSpecialityResDTO(){
         return new SpecialityResDTO()
                 .setId(id)
                 .setCodeName(codeName)
                 .setName(name);
     }
}
