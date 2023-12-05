package com.cg.model.dtos.room;

import com.cg.model.Speciality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class RoomResDTO {

    private Long id;

    private String specialityName;

    private String name;

    private Boolean isAvailable;
}
