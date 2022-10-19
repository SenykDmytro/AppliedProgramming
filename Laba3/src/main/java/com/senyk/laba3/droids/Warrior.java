package com.senyk.laba3.droids;

import com.senyk.laba3.droid.DroidImpl;
import com.senyk.laba3.droid.exceptions.WrongDataValue;
import com.senyk.laba3.droid.model.Type;

public class Warrior extends DroidImpl {
    public Warrior(Integer health, Integer damage, Integer defence, Integer department) throws WrongDataValue {
        super(Type.WARRIOR, health, damage, defence, department);
    }
}
