package net.niksune.NixArena.Web.beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedEntityGraph(name = "graph.Account.characters",
        attributeNodes = @NamedAttributeNode("characs"))
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID = 0;
    private String email;
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
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", characs=" + characs +
                '}';
    }

    public Account(int ID, String email, String password) {
        this.ID = ID;
        this.email = email;
        this.password = password;
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
