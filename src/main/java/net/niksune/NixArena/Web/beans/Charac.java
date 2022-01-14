package net.niksune.NixArena.Web.beans;

import javax.persistence.*;

@Entity
public class Charac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String name;
    private int XP = 0;
    private int level = 1;
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Weapon weaponEquipped = null;

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

    public Weapon getWeaponEquipped() {
        return weaponEquipped;
    }

    public void setWeaponEquipped(Weapon weaponEquipped) {
        this.weaponEquipped = weaponEquipped;
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
