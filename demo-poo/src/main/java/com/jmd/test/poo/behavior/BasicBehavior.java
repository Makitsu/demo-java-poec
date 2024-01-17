package com.jmd.test.poo.behavior;

import com.jmd.test.poo.monster.Monster;

public interface BasicBehavior {

    void attack(Monster anotherMonster);

    default void basicAttack(Monster anotherMonster){
        System.out.println("Do nothing !");
    }

}
