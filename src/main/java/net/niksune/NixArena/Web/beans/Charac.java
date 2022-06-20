package net.niksune.NixArena.Web.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraph(name = "graph.Character.weapon", attributeNodes = @NamedAttributeNode("weaponEquipped"))
@NamedEntityGraph(name = "graph.Character.owner", attributeNodes = @NamedAttributeNode("ownerAccount"))
@NamedEntityGraph(name = "graph.Character.complete", attributeNodes = {@NamedAttributeNode("ownerAccount"), @NamedAttributeNode("weaponEquipped")})
//@NamedEntityGraph(name = "graph.Character.fightingReports", attributeNodes = @NamedAttributeNode("fightingReports"))
public class Charac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String name;
    private int level = 1;
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Weapon weaponEquipped = null;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_account_id")
    //Prevent the circular (and infinite) call
    //But I DON'T understand WHY the charac list is called "characters" in JSON and not "characs" like the property
    // (after investigation) Holy S... That the name of the GETTERS AND SETTERS that matters for the name of json properties !?
    // I started with the name "characters" and changed because of reserved keyword problem. I wasn't ready
    // Now I changed the name back to characters but with a @JsonProperty
    @JsonIgnoreProperties("characters")
    private Account ownerAccount;
    private int totalAttack = 10;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<FightingReport> fightingReports = new ArrayList<>();

    public void setWeaponEquipped(Weapon weaponToEquip) {
        this.totalAttack = 10 * this.level;
        if (weaponToEquip != null)
            this.totalAttack += weaponToEquip.getAttack();
        this.weaponEquipped = weaponToEquip;
    }

    public void levelUp() {
        this.level++;
        this.totalAttack += 10;
    }

    @Override
    public String toString() {
        return "Charac{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
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
        this.totalAttack = 10 * level;
    }

    public List<FightingReport> getFightingReports() {
        return fightingReports;
    }

    public int getTotalAttack() {
        return totalAttack;
    }

    public void setTotalAttack(int totalAttack) {
        this.totalAttack = totalAttack;
    }

    public void setOwnerAccount(Account ownerAccount) {
        this.ownerAccount = ownerAccount;
    }

    public Account getOwnerAccount() {
        return ownerAccount;
    }

    public Weapon getWeaponEquipped() {
        return weaponEquipped;
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
}
