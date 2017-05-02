package com.tatvacoconet.entity;

public enum VerticalData {
    SBI("SBI"),
    HDFC("HDFC"),
    BOI("BOI");

    private final String name;

    private VerticalData(String s) {
        name = s;
    }
    public String getName() {
        return name;
    }
}
