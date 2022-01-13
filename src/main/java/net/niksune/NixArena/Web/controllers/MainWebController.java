package net.niksune.NixArena.Web.controllers;

import net.niksune.NixArena.Web.Services.AccountService;
import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.beans.Charac;
import net.niksune.NixArena.Web.repositories.AccountRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainWebController {

    @Autowired
    private AccountRepositoryInterface accountRepositoryInterface;

    @Autowired
    private AccountService accountService;

    @GetMapping("/allAccountsComplete")
    public Iterable<Account> getAllAccountsComplete() { return accountRepositoryInterface.findAll(); }

    /*
    @GetMapping("/allAccountsInfos")
    public Iterable<Account> getAllAccountsComplete() {
        return accountService.getAllAccounts();
    }
    */


    @GetMapping("/mainScenario")
    public String mainScenario() {

        Account nix = new Account("niksune@gmail.com","Tagada");
        Charac enzo = new Charac("Enzo");
        nix.getCharacters().add(enzo);
        accountRepositoryInterface.save(nix);
        return("Account added with one character !");

    }


}
