package net.niksune.NixArena.Web.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class FightingReport {

    //We store the names/characteristics of each charac at the moment of the fight and the links to the actual characs
    //List of attacks starts by C1 attack C2, C2 attack C1, C1 attack C2 and so on

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID = 0;
    private String specialText;
    private UUID charac1ID;
    private UUID charac2ID;
    private String charac1Name;
    private String charac2Name;
    private int charac1Level;
    private int charac2Level;
    private String weaponCharac1Name;
    private String weaponCharac2Name;
    private int weaponCharac1Attack;
    private int weaponCharac2Attack;
    @ElementCollection
    private List<Integer> attacks = new ArrayList<>();

    public FightingReport(UUID charac1ID, UUID charac2ID, String charac1Name, String charac2Name, int charac1Level, int charac2Level) {
        this.charac1ID = charac1ID;
        this.charac2ID = charac2ID;
        this.charac1Name = charac1Name;
        this.charac2Name = charac2Name;
        this.charac1Level = charac1Level;
        this.charac2Level = charac2Level;
    }

    public FightingReport(String specialText) {
        this.specialText = specialText;
    }

    public FightingReport() {
    }

    public UUID getCharac1ID() {
        return charac1ID;
    }

    public UUID getCharac2ID() {
        return charac2ID;
    }

    public void setWeaponCharac1Name(String weaponCharac1Name) {
        this.weaponCharac1Name = weaponCharac1Name;
    }

    public void setWeaponCharac2Name(String weaponCharac2Name) {
        this.weaponCharac2Name = weaponCharac2Name;
    }

    public void setWeaponCharac1Attack(int weaponCharac1Attack) {
        this.weaponCharac1Attack = weaponCharac1Attack;
    }

    public void setWeaponCharac2Attack(int weaponCharac2Attack) {
        this.weaponCharac2Attack = weaponCharac2Attack;
    }

    public String getSpecialText() {
        return specialText;
    }

    public void setSpecialText(String specialText) {
        this.specialText = specialText;
    }

    public String getCharac1Name() {
        return charac1Name;
    }

    public String getCharac2Name() {
        return charac2Name;
    }

    public int getCharac1Level() {
        return charac1Level;
    }

    public int getCharac2Level() {
        return charac2Level;
    }

    public String getWeaponCharac1Name() {
        return weaponCharac1Name;
    }

    public String getWeaponCharac2Name() {
        return weaponCharac2Name;
    }

    public int getWeaponCharac1Attack() {
        return weaponCharac1Attack;
    }

    public int getWeaponCharac2Attack() {
        return weaponCharac2Attack;
    }

    public List<Integer> getAttacks() {
        return attacks;
    }
}
