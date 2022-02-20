package net.niksune.NixArena.Web.services;

import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.repositories.AccountRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class InitializerService {

    @Autowired
    private AccountRepositoryInterface accountRepositoryInterface;

    @EventListener(ApplicationReadyEvent.class)
    public void intializeAIAccount() {
        if(!accountRepositoryInterface.existsByName("Master of Puppets"))
        {
            Account AIAccount = new Account("Master of Puppets");
            accountRepositoryInterface.save(AIAccount);
            System.out.println("Account created !");
        }
    }

}
