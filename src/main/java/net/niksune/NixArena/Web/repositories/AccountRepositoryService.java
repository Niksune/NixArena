package net.niksune.NixArena.Web.repositories;


import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.beans.Charac;
import net.niksune.NixArena.Web.beans.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AccountRepositoryService {

    @Autowired
    private AccountRepositoryInterface accountRepositoryInterface;
    @Autowired
    private CharacRepositoryInterface characRepositoryInterface;
    @Autowired
    private WeaponRepositoryInterface weaponRepositoryInterface;


    //This method is needed because Hibernate cannot autoload several lists
    //I found the solution of using a set instead of the second list
    //But 1/I want a list, 2/I add a problem : the list loaded the same number of items than in the set, even if in the DB it wasn't the same number
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Transactional
    public Account findCompleteByID(UUID ID) {

        Account account = accountRepositoryInterface.getById(ID);

        //Load weapons stored
        account.getWeaponsStored().forEach(Weapon::getName);

        //account.getCharacs().forEach(Charac::getName);
        account.getCharacs().forEach(charac -> {
            if(charac.getWeaponEquipped() != null)
                charac.getWeaponEquipped().getName();
        });

        return account;
    }

    @Transactional
    public String assignWeaponToChar(UUID idAccount, int weaponID, int numChar){

        Account account = accountRepositoryInterface.getById(idAccount);
        Weapon weaponToSwap = weaponRepositoryInterface.getById(weaponID);

        if(account.getCharacs().get(numChar).getLevel()>=weaponToSwap.getLevel()) {

            //Removes the weapon from the weaponsStored
            for (int i = 0; i < account.getWeaponsStored().size(); i++)
                if (account.getWeaponsStored().get(i).getID() == weaponID) {
                    account.getWeaponsStored().remove(i);
                    break;
                }

            //If the character has a weapon, puts it in the weaponsStored
            if (account.getCharacs().get(numChar) != null)
                account.getWeaponsStored().add(account.getCharacs().get(numChar).getWeaponEquipped());

            //Assign the weapon to character
            account.getCharacs().get(numChar).setWeaponEquipped(weaponToSwap);

            return "OK";
        }
        else
        {
            return "LevelTooLow";
        }

    }

    @Transactional
    public String disarmCharac(UUID idCharac){

        Charac charac = characRepositoryInterface.getById(idCharac);
        Account account = charac.getOwnerAccount();
        if(charac.getWeaponEquipped() == null)
            return "NoWeaponEquipped";
        Weapon weaponToRemove = charac.getWeaponEquipped();

        account.getWeaponsStored().add(weaponToRemove);

        charac.setWeaponEquipped(null);

        return "OK";

    };
}
