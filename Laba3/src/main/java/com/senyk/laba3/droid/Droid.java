package com.senyk.laba3.droid;

import com.senyk.laba3.droid.exceptions.DroidDeadException;
import com.senyk.laba3.droid.status.Status;

public interface Droid {
    void printStatus();
    int attackAnotherDroid(Droid droid) throws DroidDeadException;
    int setHealth(Integer damage) throws DroidDeadException;
    Status getStatus();
    public String getSerialNumber();
}
