package com.senyk.laba3.droids;

import com.senyk.laba3.droid.DroidImpl;
import com.senyk.laba3.droid.exceptions.WrongDataValue;
import com.senyk.laba3.droid.model.Type;

public class Universal extends DroidImpl {
    public Universal(Integer health, Integer damage, Integer defence, Integer department) throws WrongDataValue {
        super(Type.UNIVERSAL, health, damage, defence, department);
    }
}
