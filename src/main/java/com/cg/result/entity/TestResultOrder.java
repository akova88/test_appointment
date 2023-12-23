package com.cg.result.entity;

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

public class TestResultOrder {
    private String soPhieu;
    private String ngayKq;
    private String sTT;
    private String maDv;
    private String tenDv;
    private String khoaTH;
    private String maNoiDL;
    private int daTT;
    private String noiChoTH;
    private int soTT;
    private String goiLuc;
}
