package net.niksune.NixArena.Web.controllers;

import net.niksune.NixArena.Web.Services.AccountService;
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
    private AccountService accountService;


    /* ---------SCENARIOS--------- */

    @GetMapping("/mainScenario")
    public String mainScenario() {

        Account nix = new Account("autre@gmail.com", "Autre");
        Charac enzo = new Charac("Mathieu");
        Weapon rapier = new Weapon("Spear", 5);
        enzo.setWeaponEquipped(rapier);
        nix.getCharacters().add(enzo);
        accountRepositoryInterface.save(nix);
        return ("Account added with one armed character !");
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

    @GetMapping("/allAccountsComplete")
    public Iterable<Account> getAllAccountsComplete() {
        return accountRepositoryInterface.findAllCompleteBy();
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
    public Optional<Account> getAccountCompleteById(@PathVariable("id") String id) {
        return accountRepositoryInterface.findCompleteByID(Integer.parseInt(id));
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

    /* ---------INTERACTIONS--------- */

    @PostMapping("/accounts/{id}/add-charac")
    public int addCharacToAccount(@PathVariable("id") String id,@RequestBody Charac charac) {
        Account account = accountRepositoryInterface.findCompleteByID(Integer.parseInt(id)).get();
        account.getCharacters().add(charac);
        accountRepositoryInterface.save(account);
        return 1;
    }


}
