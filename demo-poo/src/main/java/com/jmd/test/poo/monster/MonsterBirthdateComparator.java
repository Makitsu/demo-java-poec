package com.jmd.test.poo.monster;

import java.util.Comparator;
import java.util.Objects;

public class MonsterBirthdateComparator implements Comparator<Monster> {

    @Override
    public int compare(Monster monster, Monster anotherMonster) {

        if(Objects.isNull(monster.getBirthdate())){
            return 1;
        }

        if(Objects.isNull(anotherMonster.getBirthdate())){
            return -1;
        }


        if(anotherMonster.getBirthdate().isEqual(monster.getBirthdate())){
            return 0;
        }

        return anotherMonster.getBirthdate().isAfter(monster.getBirthdate()) ? 1 : -1;
    }


}
