package com.atharva.SocialMediaAPI.dataType;

public enum ReportType {
    POST(1),
    COMMENT(2),
    USER(3);

    private final int value;

    ReportType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
