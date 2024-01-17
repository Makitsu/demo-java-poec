package com.jmd.test.poo.monster;

import com.jmd.test.poo.behavior.Flying;
import org.joda.time.LocalDate;


public class AirMonster extends Monster implements Flying {

    private boolean feather = true;

    public AirMonster(String name, int damage, int life, boolean feather, int level, LocalDate birthdate) {
        super(name, damage, life,level,birthdate);
        this.feather = feather;
    }

    public void attack(Monster anotherMonster){
        super.attack(anotherMonster);
        super.attack(anotherMonster);
        this.feather = false;
    }


    @Override
    public void fly() {
        System.out.println(super.getName()+" is flying...");
    }

    public boolean isFeather() {
        return feather;
    }

    public void setFeather(boolean feather) {
        this.feather = feather;
    }
}
