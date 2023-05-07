package com.example.demowithtests.domain;

import java.util.Arrays;

public enum PhotoType {
    APPLICATION_JPEG("image/jpeg"),
    APPLICATION_PNG("image/png");

    private final String value;

    PhotoType(String value) {
        this.value = value;
    }

    public static PhotoType getType(String testAlias) {
        return Arrays.stream(values()).filter(value -> value.value.equals(testAlias))
                .findAny().orElseThrow(() -> new UnsupportedOperationException(
                        String.format("Media type '%s' is not supported", testAlias)));
    }
}
