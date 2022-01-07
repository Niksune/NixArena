package net.niksune.NixArena.Web.controllers;

import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.repositories.AccountRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainWebController {

    @Autowired
    private AccountRepositoryInterface accountRepositoryInterface;

    @GetMapping("/allAccounts")
    public Iterable<Account> getAllAccounts() {
        return accountRepositoryInterface.findAll();
    }

    @GetMapping("/ajoutParCode")
    public String ajoutParCode() {

        Account nix = new Account("niksune@gmail.com","Tagada");
        accountRepositoryInterface.save(nix);
        return("Account added !");
    }


}
