package net.niksune.NixArena.Web.controllers;

import net.niksune.NixArena.Web.Services.AccountService;
import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.beans.Charac;
import net.niksune.NixArena.Web.repositories.AccountRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MainWebController {

    @Autowired
    private AccountRepositoryInterface accountRepositoryInterface;

    @Autowired
    private AccountService accountService;

    @GetMapping("/allAccountsComplete")
    public Iterable<Account> getAllAccountsComplete() { return accountRepositoryInterface.findAllCompleteBy(); }

    @GetMapping("/allAccountsInfos")
    public Iterable<Account> getAllAccountsInfos() { return accountRepositoryInterface.findAllInfosBy(); }

    @GetMapping("/accountInfos/{id}")
    public Optional<Account> getAccountInfosById(@PathVariable("id") String id) {
        return accountRepositoryInterface.findInfosByID(Integer.parseInt(id));
    }

    @GetMapping("/accountComplete/{id}")
    public Optional<Account> getAccountCompleteById(@PathVariable("id") String id) {
        return accountRepositoryInterface.findCompleteByID(Integer.parseInt(id));
    }


    @GetMapping("/mainScenario")
    public String mainScenario() {

        Account nix = new Account("niksune@gmail.com","Tagada");
        Charac enzo = new Charac("Enzo");
        nix.getCharacters().add(enzo);
        accountRepositoryInterface.save(nix);
        return("Account added with one character !");

    }


}
