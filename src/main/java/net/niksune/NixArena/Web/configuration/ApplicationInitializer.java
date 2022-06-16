package net.niksune.NixArena.Web.configuration;

import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.repositories.AccountRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;


@Configuration
public class ApplicationInitializer {

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
