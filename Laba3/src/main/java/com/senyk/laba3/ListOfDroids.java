package com.senyk.laba3;

import com.senyk.laba3.droid.Droid;
import com.senyk.laba3.droid.exceptions.WrongDataValue;
import com.senyk.laba3.droid.model.Type;
import com.senyk.laba3.droids.Tank;
import com.senyk.laba3.droids.Universal;
import com.senyk.laba3.droids.Warrior;

import java.util.ArrayList;
import java.util.List;

/**
 * container of droids
 */
public class ListOfDroids {
    private List<Droid> droidList=new ArrayList<>();

    public ListOfDroids(List<Droid> droidList) {
        this.droidList = droidList;
    }
    public ListOfDroids(int N) {
    }
    public ListOfDroids() {
    }


    public void setNewDroid(String type,Integer health, Integer damage, Integer defence, Integer department)
            throws WrongDataValue {
        Droid droid;
        switch (type.toUpperCase()){
            case "TANK":
                droid=new Tank(health, damage, defence, department);
                break;
            case "WARRIOR":
                droid=new Warrior(health, damage, defence, department);
                break;
            case "UNIVERSAL":
                droid=new Universal(health, damage, defence, department);
                break;
            default:
                droid=null;
                break;
        }
        if(droid!=null) {
            this.droidList.add(droid);
        }
        else {
            System.out.println("There is no type of this droid");
            throw new WrongDataValue();
        }
    }

    //setters and getters
    public List<Droid> getDroidList() {
        return droidList;
    }
    public void setDroidList(List<Droid> droidList) {
        this.droidList = droidList;
    }
}
