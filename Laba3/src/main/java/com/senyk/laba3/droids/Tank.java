package com.senyk.laba3.droids;

import com.senyk.laba3.droid.DroidImpl;
import com.senyk.laba3.droid.exceptions.WrongDataValue;
import com.senyk.laba3.droid.model.Type;

public class Tank extends DroidImpl {
    public Tank(Integer health, Integer damage, Integer defence, Integer department) throws WrongDataValue {
        super(Type.TANK, health, damage, defence, department);
    }

}
