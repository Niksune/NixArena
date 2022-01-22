package net.niksune.NixArena.Web.repositories;


import net.niksune.NixArena.Web.beans.Account;
import net.niksune.NixArena.Web.beans.Charac;
import net.niksune.NixArena.Web.beans.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CharacRepositoryService {

    @Autowired
    private AccountRepositoryInterface accountRepositoryInterface;

    @Autowired
    private CharacRepositoryInterface characRepositoryInterface;

    @Transactional
    public void destroyCharacAndRetrieveWeapon(int idCharac){

        Charac charac = characRepositoryInterface.findCompleteByID(idCharac);

        if(charac.getWeaponEquipped() != null)
        {
            charac.getOwnerAccount().getWeaponsStored().add(charac.getWeaponEquipped());
        }

        charac.getOwnerAccount().getCharacs().remove(charac);

        characRepositoryInterface.deleteById(idCharac);

    }

}
