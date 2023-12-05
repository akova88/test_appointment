package com.cg.model.enums;

public enum EGender {
    FEMALE("NỮ"), MALE("NAM");

    private String value;

    EGender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
