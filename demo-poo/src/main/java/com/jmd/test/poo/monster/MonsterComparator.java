package com.jmd.test.poo.monster;

import java.util.Comparator;
import java.util.Objects;

public class MonsterComparator  implements Comparator<Monster> {

    @Override
    public int compare(Monster monster, Monster anotherMonster) {

        if(Objects.isNull(monster.getLevel())){
            return 1;
        }

        if(Objects.isNull(anotherMonster.getLevel())){
            return -1;
        }

        if(Objects.equals(anotherMonster.getLevel(), monster.getLevel())){
            return 0;
        }

        return monster.getLevel() < anotherMonster.getLevel() ? 1 : -1;
    }


}
