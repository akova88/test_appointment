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
public class TestResultDetail {
    private String SID;
    private String Updatetime;
    private String Intime;
    private String ResultTime;
    private String PID;
    private String OrderId;
    private String LocationID;
    private String NoiChiDinh;
    private String PatientName;
    private String Sex;
    private String Age;
    private String Address;
    private String PDescription;
    private String PDoctorName;
    private String Testcode;
    private String TestName;
    private String Result;
    private String Unit;
    private String NormalRange;
    private Integer Color;
    private String TestHead;
    private String Category;
    private String NoiThucHien;
    private String KhoaThucHien;
    private String OrderTestCode;
    private String MaDV;
    private String TenDV;
}
