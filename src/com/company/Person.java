package com.company;

import java.util.Random;

public class Person {
    private String name;
    private double health;
    private double damage;
    private double armour;

    void addDamage(double damage)
    {
        this.health-=(1-this.armour)*damage;
    }

    boolean isAlive()
    {
        return this.health>0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", damage=" + damage +
                ", armour=" + armour +
                '}';
    }


    public Person(String name, double health, double damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.armour = Math.random();
    }

    public Person(String name, double health, double damage, double armour) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.armour = armour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHealth() {
        return health;
    }

    public double getDamage() {
        return damage;
    }

    public double getArmour() {
        return armour;
    }

    public void setArmour(double armour) {
        this.armour = armour;
    }
}
