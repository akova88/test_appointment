package com.cg.result.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "CNTT_Dm_GioiTinh")
@Accessors(chain = true)
@Entity
public class DM_GioiTinh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Ma", columnDefinition = "nchar(2)")
    private String Ma;

    @Column(name = "TenGioiTinh", columnDefinition = "nvarchar(20)")
    private String tenGioiTinh;

}
