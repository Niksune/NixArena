package net.niksune.NixArena.Web.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Charac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String name;
    private int XP = 0;
    private int level = 1;

    @Override
    public String toString() {
        return "Charac{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", XP=" + XP +
                ", level=" + level +
                '}';
    }

    public Charac() {
    }

    public Charac(String name) {
        this.name = name;
    }

    public Charac(String name, int level) {
        this.name = name;
        this.level = level;
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

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
