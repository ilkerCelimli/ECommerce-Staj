package com.portifolyo.mesleki1.enums;

public enum ROLE {

    ADMIN("ADMIN"),
    USER("USER"),
    SHOP("SHOP");

    final String text;
    ROLE(String text) {
        this.text = text;
    }
}
