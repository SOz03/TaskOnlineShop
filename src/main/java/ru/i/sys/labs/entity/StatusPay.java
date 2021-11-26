package ru.i.sys.labs.entity;

import lombok.*;

@ToString
public enum StatusPay {

    PAID("Оплачен"),
    NOT_PAID("Не оплачен");

    private String name;

    StatusPay(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
