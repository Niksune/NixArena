package net.niksune.NixArena.Web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.niksune.NixArena.Web.Services.AccountService;
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

    @Autowired
    private AccountService accountService;


    /* ---------SCENARIOS--------- */

    @GetMapping("/mainScenario")
    public String mainScenario() {

        Account nix = accountRepositoryInterface.findWeaponsStoredByID(5).get();//new Account("autre@gmail.com", "Autre");
        /*
        Charac romeo = new Charac("Romeo");
        Weapon spear = new Weapon("Spear", 5);
        romeo.setWeaponEquipped(spear);
        nix.addCharacter(romeo);
        */
        Weapon w1 = new Weapon("Blade", 6);
        Weapon w2 = new Weapon("Fan", 8);
        nix.getWeaponsStored().add(w1);
        nix.getWeaponsStored().add(w2);
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


    @PostMapping("/accounts/{id}/set-main")
    public int setMainToAccount(@PathVariable("id") String id, @RequestBody String json) {
        Account account = accountRepositoryService.findCompleteByID(Integer.parseInt(id));
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(json);
            int num = jsonNode.get("num").asInt();
            account.setNumberMain(num);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        accountRepositoryInterface.save(account);
        return 1;
    }


    @PostMapping("/accounts/{id}/equip-to")
    public int equipWeaponToCharac(@PathVariable("id") String idAccount, @RequestBody String json) {

        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(json);
            int numChar = jsonNode.get("characterNumber").asInt();
            int idWeapon = jsonNode.get("idWeapon").asInt();

            accountService.assignCopyWeaponToChar(Integer.parseInt(idAccount),idWeapon,numChar);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return 1;
    }


}
