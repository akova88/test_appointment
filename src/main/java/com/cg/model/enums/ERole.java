package com.cg.model.enums;

public enum ERole {
    ROLE_ADMIN("ADMIN"), ROLE_USER("USER"), ROLE_DOCTOR("DOCTOR");

    private String value;

    ERole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
