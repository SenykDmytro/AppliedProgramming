package com.senyk.laba3;

import com.senyk.laba3.droid.Droid;
import com.senyk.laba3.droid.status.Status;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Droid> team=new ArrayList<>();

    private boolean status;

    public Team(String name, List<Droid> team) {
        this.name = name;
        this.team = team;
    }

    public Team(String name) {
        this.name = name;
    }

    public void addDroidToTeam(Droid droid){
        this.team.add(droid);
    }

    public boolean isStatus() {
        boolean status=true;
        int x=0;
        for (int i = 0; i < team.size(); i++) {
            if(team.get(i).getStatus()==Status.Dead)
                x++;
        }
        if (x==team.size())
        return false;
        else return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Droid> getTeam() {
        return team;
    }

    public void setTeam(List<Droid> team) {
        this.team = team;
    }
}
