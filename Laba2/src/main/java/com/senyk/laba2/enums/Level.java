package com.senyk.laba2.enums;

import java.util.Random;

public enum Level {
    First,
    Second,
    Third,
    Fourth,
    Fifth;

    public static Level generateLevel() {
        Level[] values = Level.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];
    }
}
