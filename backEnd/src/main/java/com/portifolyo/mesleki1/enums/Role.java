package com.portifolyo.mesleki1.enums;

public enum Role {

    USER("USER"),
    SHOP("SHOP");

    final String text;
    Role(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}
