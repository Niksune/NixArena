package net.niksune.NixArena.Web.controllers;

import net.niksune.NixArena.Web.repositories.AccountRepositoryService;
import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.beans.Charac;
import net.niksune.NixArena.Web.beans.Weapon;
import net.niksune.NixArena.Web.repositories.AccountRepositoryInterface;
import net.niksune.NixArena.Web.repositories.CharacRepositoryInterface;
import net.niksune.NixArena.Web.repositories.WeaponRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://127.0.0.1:5500/actual-game/", "http://127.0.0.1:5500/general-management/"})
public class MainWebController {

    @Autowired
    private AccountRepositoryInterface accountRepositoryInterface;
    @Autowired
    private CharacRepositoryInterface characRepositoryInterface;
    @Autowired
    private WeaponRepositoryInterface weaponRepositoryInterface;

    @Autowired
    private AccountRepositoryService accountRepositoryService;


    /* ---------SCENARIOS--------- */

    @GetMapping("/mainScenario")
    public String mainScenario() {

        Account nix = new Account("autre@gmail.com", "Autre");
        Charac romeo = new Charac("Romeo");
        Weapon spear = new Weapon("Spear", 5);
        romeo.setWeaponEquipped(spear);
        nix.addCharacter(romeo);
        Weapon rapier = new Weapon("Rapier", 3);
        Weapon dagger = new Weapon("Dagger", 2);
        nix.getWeaponsStored().add(rapier);
        nix.getWeaponsStored().add(dagger);
        accountRepositoryInterface.save(nix);
        return ("Account added with one armed character and several weapons !");
    }

    @GetMapping("/checkRead")
    public void checkRead() {

        /*
        Account acc = accountRepositoryService.findCompleteByID(13).get();
        System.out.println(acc.getCharacs().size());
        for (Charac c : acc.getCharacs()) {
            System.out.println(c);
        }
        System.out.println(acc.getWeaponsStored().size());
        for (Weapon w : acc.getWeaponsStored()) {
            System.out.println(w);
        }
        */

    }

    @GetMapping("/makeWeapons")
    public String makeWeapons() {
        for (int i = 1; i < 11; i++) {
            Weapon w = new Weapon("Tooth Pick", i);
            weaponRepositoryInterface.save(w);
        }
        return ("Weapons Created");
    }

    /* ---------ACCOUNTS--------- */

    @GetMapping("/allAccountsAllCharacsWithEquippedWeapon")
    public Iterable<Account> getAllAccountsAllCharacsWithEquippedWeapon() {
        return accountRepositoryInterface.findAllCharacsWithEquippedWeaponBy();
    }

    @GetMapping("/allAccountsWithCharacs")
    public Iterable<Account> getAllAccountsWithCharacs() {
        return accountRepositoryInterface.findAllWithCharacsBy();
    }

    @GetMapping("/allAccountsInfos")
    public Iterable<Account> getAllAccountsInfos() {
        return accountRepositoryInterface.findAllInfosBy();
    }

    @GetMapping("/accountInfos/{id}")
    public Optional<Account> getAccountInfosById(@PathVariable("id") String id) {
        return accountRepositoryInterface.findInfosByID(Integer.parseInt(id));
    }

    @GetMapping("/accountComplete/{id}")
    public Account getAccountCompleteById(@PathVariable("id") String id) {
        return accountRepositoryService.findCompleteByID(Integer.parseInt(id));
    }

    // Others HTTP Requests

    @PostMapping("/accounts")
    public int postAccount(@RequestBody Account account) {
        System.out.println("Ajout de : " + account);
        accountRepositoryInterface.save(account);
        return 1;
    }

    @DeleteMapping("/accounts/{id}")
    public int deleteAccountById(@PathVariable("id") String id) {
        accountRepositoryInterface.deleteById(Integer.parseInt(id));
        return 1;
    }

    /* ---------CHARACS--------- */

    @GetMapping("/allCharacs")
    public Iterable<Charac> getAllCharacs() {
        return characRepositoryInterface.findAll();
    }

    @GetMapping("/allCharacsWithWeapon")
    public Iterable<Charac> getAllCharacsWithWeapon() {
        return characRepositoryInterface.findAllWithWeaponBy();
    }

    @GetMapping("/allCharacsWithOwner")
    public Iterable<Charac> getAllCharacsWithOwner() {
        return characRepositoryInterface.findAllWithOwnerBy();
    }

    /* ---------INTERACTIONS--------- */

    @PostMapping("/accounts/{id}/add-charac")
    public int addCharacToAccount(@PathVariable("id") String id, @RequestBody Charac charac) {
        Account account = accountRepositoryService.findCompleteByID(Integer.parseInt(id));
        account.addCharacter(charac);
        accountRepositoryInterface.save(account);
        return 1;
    }


}
