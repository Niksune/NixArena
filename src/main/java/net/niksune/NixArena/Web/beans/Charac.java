package net.niksune.NixArena.Web.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@NamedEntityGraph(name = "graph.Character.weapon", attributeNodes = @NamedAttributeNode("weaponEquipped"))
@NamedEntityGraph(name = "graph.Character.owner", attributeNodes = @NamedAttributeNode("ownerAccount"))
@NamedEntityGraph(name = "graph.Character.complete", attributeNodes = {@NamedAttributeNode("ownerAccount"),@NamedAttributeNode("weaponEquipped")})
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
