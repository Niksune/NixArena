package net.niksune.NixArena.Web.Services;


import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.repositories.AccountRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepositoryInterface accountRepositoryInterface;

    public Iterable<Account> getAllAccounts() {
        Iterable<Account> accounts = accountRepositoryInterface.findAll();

        //Load the characters for each account (crappy solution : n+1 select problem + keeping open session)
        accounts.forEach(account -> {
            account.getCharacters().size();
        });

        return accounts;
    }


}
