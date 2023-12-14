package com.cg.model.enums;

public enum ETime {
    S1("8:00-9:00"),
    S2("9:00-10:00"),
    S3("10:00-11:00"),
    C1("14:00-15:00"),
    C2("15:00-16:00"),
    C3("16:00-17:00");

    private String value;

    ETime(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
