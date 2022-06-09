package net.niksune.NixArena.Web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.niksune.NixArena.Web.forms.ConnectionForm;
import net.niksune.NixArena.Web.repositories.*;
import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.beans.Charac;
import net.niksune.NixArena.Web.beans.Weapon;
import net.niksune.NixArena.Web.services.CharacService;
import net.niksune.NixArena.Web.services.FightOrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RestController
@RequestMapping("/API")
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
    private CharacRepositoryService characRepositoryService;

    @Autowired
    private CharacService characService;

    @Autowired
    private FightOrganizerService fightOrganizerService;


    /* ---------SCENARIOS--------- */

    @GetMapping("/mainScenario")
    public String mainScenario() {

        Account nix = new Account("Nix", "badada");

        Charac romeo = new Charac("Romeo");
        Weapon spear = new Weapon("Spear", 5);
        romeo.setWeaponEquipped(spear);
        nix.addCharacter(romeo);

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
        return accountRepositoryInterface.findInfosByID(UUID.fromString(id));
    }

    @GetMapping("/accountComplete/{id}")
    public Account getAccountCompleteById(@PathVariable("id") String id) {
        return accountRepositoryService.findCompleteByID(UUID.fromString(id));
    }

    @GetMapping("/accountComplete/{id}/{sortWeapons}")
    public Account getAccountCompleteById(@PathVariable("id") String id, @PathVariable("sortWeapons") String sortWeapons) {
        Account account = accountRepositoryService.findCompleteByID(UUID.fromString(id));
        if (sortWeapons.equals("1")) {
            Collections.sort(account.getWeaponsStored());
        } else if (sortWeapons.equals("2")) {
            Collections.sort(account.getWeaponsStored(), Collections.reverseOrder());
        }
        return account;
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
        accountRepositoryInterface.deleteById(UUID.fromString(id));
        return 1;
    }

    // Destroys the weapon but gives gold to the account equals to its attack value
    @DeleteMapping("/accounts/{idAccount}/weapons")
    public int deleteStoredWeapon(@PathVariable("idAccount") String idAccount, @RequestBody String json) {

        Account account = accountRepositoryInterface.findWithWeaponsStoredByID(UUID.fromString(idAccount)).get();

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(json);
            int numberWeapon = jsonNode.get("numberWeapon").asInt();
            account.addGold(account.getWeaponsStored().get(numberWeapon).getAttack());
            account.getWeaponsStored().remove(numberWeapon);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        accountRepositoryInterface.save(account);

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

    @GetMapping("/allCharacsWithFightingreports")
    public Iterable<Charac> getAllCharacsWithFightingreports() {
        return characRepositoryService.findAllWithFightingreportsBy();
    }

    @GetMapping("/charac/{id}/fightingreports")
    public Charac getCharacWith5LastFightingreportsById(@PathVariable("id") String id) {
        return characRepositoryService.findWithFightingreportsById(UUID.fromString(id));
    }

    // Others HTTP Requests

    @DeleteMapping("/characs/{id}")
    public int deleteCharacById(@PathVariable("id") String id) {

        characRepositoryService.destroyCharacAndRetrieveWeapon(UUID.fromString(id));

        return 1;
    }


    /* ---------INTERACTIONS--------- */

    @PostMapping("/accounts/connection")
    public UUID connection(@RequestBody ConnectionForm connectionForm) {
        Optional<Account> account = accountRepositoryInterface.findByNameAndPassword(connectionForm.getName(), connectionForm.getPassword());

        if(account.isEmpty())
            return new UUID( 0 , 0 );
        else
            return account.get().getID();

    }

    @PostMapping("/accounts/{id}/add-charac")
    public String addCharacToAccount(@PathVariable("id") String id, @RequestBody Charac charac) {
        Account account = accountRepositoryService.findCompleteByID(UUID.fromString(id));
        if (account.getCharacs().size() >= 3)
            return "TooManyCharacters";
        account.addCharacter(charac);
        accountRepositoryInterface.save(account);

        // For each not character made by a player, we generate 4 characters to the Master of Puppets
        Account master = accountRepositoryInterface.getWithCharacsByName("Master of Puppets");
        for (int i = 0; i < 4; i++) {
            master.addCharacter(new Charac(characService.randomName()));
        }

        accountRepositoryInterface.save(master);

        return "OK";
    }

    @PatchMapping("/accounts/{id}/equip-to")
    public String equipWeaponToCharac(@PathVariable("id") String idAccount, @RequestBody String json) {

        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
        String result = "KO";
        try {
            JsonNode jsonNode = mapper.readTree(json);
            int numChar = jsonNode.get("characterNumber").asInt();
            int idWeapon = jsonNode.get("idWeapon").asInt();

            result = accountRepositoryService.assignWeaponToChar(UUID.fromString(idAccount), idWeapon, numChar);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @PatchMapping("disarm-charac/{idCharacter}")
    public String disarmCharac(@PathVariable("idCharacter") String idCharacter) {

        return accountRepositoryService.disarmCharac(UUID.fromString(idCharacter));
    }


    /* ---------FULL SERVER SIDE--------- */

    @GetMapping("fight-round")
    public int fightRound() {

        return fightOrganizerService.allFights();

    }

}
