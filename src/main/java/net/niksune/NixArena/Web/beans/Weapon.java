package net.niksune.NixArena.Web.beans;

import net.niksune.NixArena.Web.Services.WeaponService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Weapon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID = 0;
    private String name;
    private int level;
    private int attack;

    public Weapon() {
    }

    public Weapon(String name, int level) {
        this.name = name;
        this.level = level;
        this.attack = WeaponService.initAttack(level);
    }

    public Weapon(String name, int level, int attack) {
        this.name = name;
        this.level = level;
        this.attack = attack;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}