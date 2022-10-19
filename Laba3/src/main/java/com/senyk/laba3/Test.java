package com.senyk.laba3;

import com.senyk.laba3.droid.Droid;
import com.senyk.laba3.droid.exceptions.DroidDeadException;
import com.senyk.laba3.droid.exceptions.WrongDataValue;
import com.senyk.laba3.droid.model.Type;
import com.senyk.laba3.droids.Tank;
import com.senyk.laba3.droids.Universal;

import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {

        System.out.println(Type.TANK);
        System.out.println(Type.WARRIOR);
        System.out.println(Type.UNIVERSAL);
        Application1 application=new Application1();
        application.start();
    }
}
