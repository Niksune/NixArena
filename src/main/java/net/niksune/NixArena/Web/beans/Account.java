package net.niksune.NixArena.Web.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedEntityGraph(name = "graph.Account.characters", attributeNodes = @NamedAttributeNode("characs"))
@NamedEntityGraph(name = "graph.Account.onlyInfos")
@NamedEntityGraph(name = "graph.Account.characsWithEquippedWeapon", attributeNodes = @NamedAttributeNode(value = "characs", subgraph = "Charac.weaponEquipped"), subgraphs = {@NamedSubgraph(name = "Charac.weaponEquipped", attributeNodes = @NamedAttributeNode(value = "weaponEquipped"))})
@NamedEntityGraph(name = "graph.Account.weaponsStored", attributeNodes = @NamedAttributeNode("weaponsStored"))
//Removed because of Hibernate cannot load two lists. If both lists are needed loading is done by @Transactional methods
//@NamedEntityGraph(name = "graph.Account.complete", attributeNodes = {@NamedAttributeNode("weaponsStored"),@NamedAttributeNode(value = "characs", subgraph = "Charac.weaponEquipped")}, subgraphs = {@NamedSubgraph(name = "Charac.weaponEquipped", attributeNodes = @NamedAttributeNode(value = "weaponEquipped"))})

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID = 0;
    private String name;
    private String password;
    private int gold = 0;
    @JsonProperty("characters")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Charac> characs = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Weapon> weaponsStored = new ArrayList<>();


    public void addCharacter(Charac charac){
        this.characs.add(charac);
        charac.setOwnerAccount(this);
    }

    public void addGold(int goldToAdd){
        setGold(getGold()+goldToAdd);
    }


    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" + "ID=" + ID + ", email='" + name + '\'' + ", password='" + password + '\'' + ", characs=" + characs + '}';
    }

    public Account(int ID, String name, String password) {
        this.ID = ID;
        this.name = name;
        this.password = password;
    }

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public List<Weapon> getWeaponsStored() {
        return weaponsStored;
    }

    public void setWeaponsStored(List<Weapon> weaponsStored) {
        this.weaponsStored = weaponsStored;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Charac> getCharacs() {
        return characs;
    }

    public void setCharacs(List<Charac> characs) {
        this.characs = characs;
    }
}
