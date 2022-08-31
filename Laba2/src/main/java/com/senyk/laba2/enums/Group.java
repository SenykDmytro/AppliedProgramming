package com.senyk.laba2.enums;

import java.util.Random;

public enum Group {
    G1(Faculty.F1),
    G2(Faculty.F1),
    G3(Faculty.F1),
    G4(Faculty.F2),
    G5(Faculty.F2),
    G6(Faculty.F2),
    G7(Faculty.F2),
    G8(Faculty.F2),
    G9(Faculty.F2),
    G10(Faculty.F3),
    G11(Faculty.F3),
    G12(Faculty.F3),
    G13(Faculty.F3),
    G14(Faculty.F3),
    G15(Faculty.F3),
    G16(Faculty.F3),
    G17(Faculty.F3),
    G18(Faculty.F3),
    G19(Faculty.F3),
    G20(Faculty.F4);

    private Faculty faculty;
    Group(Faculty faculty) {
        this.faculty = faculty;
    }
    public Faculty getFaculty() {
        return faculty;
    }

    public static Group generateGroup() {
        Group[] values = Group.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];
    }
}
