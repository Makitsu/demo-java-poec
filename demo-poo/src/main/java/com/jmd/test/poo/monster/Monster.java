package com.jmd.test.poo.monster;

import com.jmd.test.poo.behavior.BasicBehavior;
import org.joda.time.LocalDate;


import java.util.Objects;

public abstract class Monster implements BasicBehavior {
    private String name;
    private int damage;
    private int life;
    private Integer level;
    private LocalDate birthdate;

    protected Monster(String name, int damage, int life, int level,LocalDate birthdate) {
        this.name = name;
        this.damage = damage;
        this.life = life;
        this.level = level;
        this.birthdate = birthdate;
    }

    @Override
    public void attack(Monster anotherMonster){

        anotherMonster.setLife(anotherMonster.getLife() - this.getDamage());

        String message;
        if(anotherMonster.getLife()  > 0){
            message = anotherMonster.getName().concat(" has ").concat(String.valueOf(anotherMonster.getDamage())).concat(" points remaining.");
        }else{
            message = anotherMonster.getName().concat(" is KO !");
        }

        System.out.println(message);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return damage == monster.damage
                && life == monster.life
                && level == monster.level
                && Objects.equals(name, monster.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, damage, life, level);
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", birthdate=" + birthdate +
                '}';
    }
}
