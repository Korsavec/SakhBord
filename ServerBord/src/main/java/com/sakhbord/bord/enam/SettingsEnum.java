package com.sakhbord.bord.enam;

public enum SettingsEnum {

    THREE("THREE");

    private final String name;

    SettingsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
