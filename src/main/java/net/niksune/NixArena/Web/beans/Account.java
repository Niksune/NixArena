package net.niksune.NixArena.Web.beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraph(name = "graph.Account.characters",
        attributeNodes = @NamedAttributeNode("characs"))
@NamedEntityGraph(name = "graph.Account.onlyInfos")
@NamedEntityGraph(
        name = "graph.Account.characAndWeapons",
        attributeNodes = @NamedAttributeNode(value = "characs", subgraph = "Charac.weaponEquipped"),
        subgraphs = {
                @NamedSubgraph(name = "Charac.weaponEquipped",
                        attributeNodes = @NamedAttributeNode(value = "weaponEquipped")) })
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID = 0;
    private String name;
    private String password;
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Charac> characs = new ArrayList<Charac>();


    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "ID=" + ID +
                ", email='" + name + '\'' +
                ", password='" + password + '\'' +
                ", characs=" + characs +
                '}';
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

    public List<Charac> getCharacters() {
        return characs;
    }

    public void setCharacters(List<Charac> characs) {
        this.characs = characs;
    }
}
