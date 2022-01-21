package net.niksune.NixArena.Web.Services;

import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.beans.Weapon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class AccountService {

    @Autowired
    private SessionFactory sessionFactory;

    public void assignCopyWeaponToChar(int idAccount, int weaponID, int numChar){

        Session session = sessionFactory.openSession();

        Account account = (Account)session.createQuery("from Account where id ="+idAccount).list().get(0);
        Weapon weaponToSwap = (Weapon)session.createQuery("from Weapon where id ="+weaponID).list().get(0);

        session.beginTransaction();

        //Removes the weapon from the weaponsStored
        for(int i=0;i<account.getWeaponsStored().size();i++)
            if(account.getWeaponsStored().get(i).getID() == weaponID)
            {
                account.getWeaponsStored().remove(i);
                break;
            }

        //If the character has a weapon, puts it in the weaponsStored
        if(account.getCharacs().get(numChar) != null)
            account.getWeaponsStored().add(account.getCharacs().get(numChar).getWeaponEquipped());

        //Assign the weapon to character
        account.getCharacs().get(numChar).setWeaponEquipped(weaponToSwap);

        session.getTransaction().commit();
        session.update(account);
        session.close();

    }

}
