package com.senyk.laba3.droid;

import com.senyk.laba3.droid.exceptions.DroidDeadException;
import com.senyk.laba3.droid.exceptions.WrongDataValue;
import com.senyk.laba3.droid.model.Type;
import com.senyk.laba3.droid.status.Status;

import java.text.DecimalFormat;
import java.util.Random;

public abstract class DroidImpl implements Droid{

    private static Long countDroid= Long.valueOf(0);
    private Long id;
    private String serialNumber;
    private String model;
    private Type type;
    private Integer health;
    private Integer damage;
    private Integer defence;
    private Status status = Status.Alive;
    private Integer department;

    public DroidImpl(Type type, Integer health, Integer damage, Integer defence, Integer department)
            throws WrongDataValue {
        if(health<type.getMinHealth()||health>type.getMaxHealth()||
                damage<type.getMinDamage()||damage>type.getMaxDamage()||
                defence<type.getMinDefence()||defence>type.getMaxDefence()||
                department<0||department>99) throw new WrongDataValue();
        if(DroidImpl.countDroid+1L<0L)
            DroidImpl.countDroid=0L;
        this.type = type;
        this.health = health;
        this.damage = damage;
        this.defence = defence;
        this.department = department;
        this.model =type.getModel();
        DroidImpl.countDroid++;
        this.id=DroidImpl.countDroid;
        setSerialNumber();
    }


    /**
     * set SN with pattern: CC_MM_DDDD_DDDD_DDDD_DDDD_DDD
     * CC - number of department
     * MM - model
     * DDDD_DDDD_DDDD_DDDD_DDD - number of droid
     */
    private void setSerialNumber() {
        String serialNumber=departmentToString()+this.type.getModel()+idToString();
        this.serialNumber = serialNumber;
    }
    private String idToString(){
        DecimalFormat decimalFormat =new DecimalFormat("0000000000000000000");
        return decimalFormat.format(this.id).toString();
    }
    private String departmentToString(){
        DecimalFormat decimalFormat =new DecimalFormat("00");
        return decimalFormat.format(this.department).toString();
    }



    /**
     * set health after getting damage
     * @param damage damage which this droid get from another
     */
    @Override
    public int setHealth(Integer damage) throws DroidDeadException {
        if (this.status==Status.Dead) throw new DroidDeadException();
        Integer h =this.health;
        Random random=new Random();
        Integer defence =(random.nextInt()%(this.defence-this.defence/2))+this.defence/2;
        Integer d= Integer.valueOf(defence.toString().length());
        int x=damage-(damage*defence/100);
        System.out.println("Droid with SN:"+this.serialNumber+" take damage:"+x);
        h=h-x;
        if(h<=0){
            status=Status.Dead;
            this.health = 0;
        }
        else if (h>this.health) {
            this.health--;}
        else {
            this.health = h;}
        return x;
    }
    private Integer pow(Integer b){
        Integer i=1;
        for (int j = 0; j < b; j++) {
            i*=10;
        }
        return i;
    }

    @Override
    public int attackAnotherDroid(Droid droid) throws DroidDeadException {
        int x=droid.setHealth(this.damage);
        return x;
    }

    //setters
    public void setDamage(Integer damage) {
        this.damage = damage;
    }
    public void setDefence(Integer defence) {
        this.defence = defence;
    }

    //getters
    public Long getId() {
        return id;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public String getModel() {
        return model;
    }
    public Type getType() {
        return type;
    }
    public Integer getHealth() {
        return health;
    }
    public Integer getDamage() {
        return damage;
    }
    public Integer getDefence() {
        return defence;
    }
    @Override
    public Status getStatus() {
        return status;
    }
    public Integer getDepartment() {
        return department;
    }


    @Override
    public void printStatus() {
        if (status==Status.Dead)
            System.out.println("Droid with SN:"+this.serialNumber+" is destroyed");
        else System.out.println("Droid's SN:"+this.serialNumber+", health:"+
                this.health+", damage:"+this.damage+", defence:"+this.defence);
    }



    @Override
    public String toString() {
        return "DroidImpl{" +
                "serialNumber='" + serialNumber + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", defence=" + defence +
                ", status=" + status +
                '}';
    }
}
