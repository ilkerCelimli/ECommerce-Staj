package com.portifolyo.mesleki1.enums;

public enum OrderStatus {
    ORDER_TAKEN("order_taken"),
    GETTING_READY("getting_ready"),
    TRANSPORT("transport"),
    WAS_DELIVERED("was_delivered");

    private String text;

     OrderStatus(String text) {
        this.text = text;
    }


}
