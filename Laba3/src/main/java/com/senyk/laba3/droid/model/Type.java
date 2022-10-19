package com.senyk.laba3.droid.model;

public enum Type {
    TANK("TK",1000,900,60,40,20,10),
    WARRIOR("WR",500,400,110,90,5,1),
    UNIVERSAL("UN",800,700,90,60,10,5);

    private String model;
    private Integer maxHealth;
    private Integer minHealth;
    private Integer maxDamage;
    private Integer minDamage;
    private Integer maxDefence;
    private Integer minDefence;

    Type(String model, Integer maxHealth, Integer minHealth, Integer maxDamage,
         Integer minDamage, Integer maxDefence, Integer minDefence) {
        this.model = model;
        this.maxHealth = maxHealth;
        this.minHealth = minHealth;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.maxDefence = maxDefence;
        this.minDefence = minDefence;
    }



    public Integer getMaxHealth() {
        return maxHealth;
    }
    public Integer getMinHealth() {
        return minHealth;
    }
    public Integer getMaxDamage() {
        return maxDamage;
    }
    public Integer getMinDamage() {
        return minDamage;
    }
    public Integer getMaxDefence() {
        return maxDefence;
    }
    public Integer getMinDefence() {
        return minDefence;
    }
    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "Type{" +
                "model='" + model + '\'' +
                ", maxHealth=" + maxHealth +
                ", minHealth=" + minHealth +
                ", maxDamage=" + maxDamage +
                ", minDamage=" + minDamage +
                ", maxDefence=" + maxDefence +
                ", minDefence=" + minDefence +
                '}';
    }
}
