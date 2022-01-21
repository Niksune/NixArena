package net.niksune.NixArena.Web.repositories;


import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.beans.Weapon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRepositoryService {

    @Autowired
    private SessionFactory sessionFactory;

    //This method is needed because Hibernate cannot autoload several lists
    //I found the solution of using a set instead of the second list
    //But 1/I want a list, 2/I add a problem : the list loaded the same number of items than in the set, even if in the DB it wasn't the same number
    public Account findCompleteByID(int ID) {

        Session session = sessionFactory.openSession();

        Account account = (Account)session.createQuery("from Account where id ="+ID).list().get(0);

        //Load weapons stored
        account.getWeaponsStored().forEach(Weapon::getName);

        //account.getCharacs().forEach(Charac::getName);
        account.getCharacs().forEach(charac -> {
            if(charac.getWeaponEquipped() != null)
                charac.getWeaponEquipped().getName();
        });

        session.close();

        return account;
    }


}
