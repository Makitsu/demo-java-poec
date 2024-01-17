package com.jmd.test.poo.monster;

import com.jmd.test.poo.behavior.Diving;
import org.joda.time.LocalDate;


public class WaterMonster extends Monster implements Diving {


    public WaterMonster(String name, int damage, int life, int level, LocalDate birthdate) {
        super(name, damage, life,level,birthdate);
    }

    @Override
    public void dive() {
        System.out.println(super.getName()+" is diving...");
    }
}
