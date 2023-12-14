package com.cg.model.enums;

public enum EBookTime {
    M1("07h30"),
    M2("08h00"),
    M3("08h30"),
    M4("09h00"),
    M5("09h30"),
    M6("10h00"),
    M7("10h30"),
    M8("11h00"),

    A1("13h30"),
    A2("14h00"),
    A3("14h30"),
    A4("15h00"),
    A5("15h30"),
    A6("16h00"),
    A7("16h30"),
    A8("17h00");

    private String value;

    EBookTime(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
