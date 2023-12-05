package com.cg.model.enums;

public enum ELevel {
    BS("Bác sĩ"), BSCKI("Bác sĩ chuyên khoa 1"), BSCKII("Bác sĩ chuyên khoa 2");

    private String value;

    ELevel(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
