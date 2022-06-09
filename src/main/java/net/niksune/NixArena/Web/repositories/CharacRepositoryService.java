package net.niksune.NixArena.Web.repositories;


import net.niksune.NixArena.Web.beans.Charac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CharacRepositoryService {

    @Autowired
    private AccountRepositoryInterface accountRepositoryInterface;

    @Autowired
    private CharacRepositoryInterface characRepositoryInterface;

    @Transactional
    public void destroyCharacAndRetrieveWeapon(UUID idCharac){

        Charac charac = characRepositoryInterface.findCompleteByID(idCharac);

        if(charac.getWeaponEquipped() != null)
        {
            charac.getOwnerAccount().getWeaponsStored().add(charac.getWeaponEquipped());
        }

        charac.getOwnerAccount().getCharacs().remove(charac);

        characRepositoryInterface.deleteById(idCharac);

    }

    @Transactional
    public List<Charac> findAllWithFightingreportsBy() {

        List<Charac> characs = characRepositoryInterface.findAll();

        characs.forEach(charac -> {charac.getFightingReports().forEach(fightingReport -> {fightingReport.getAttacks().forEach(Integer::intValue);});});

        return characs;
    }

    @Transactional
    public Charac findWithFightingreportsById(UUID idCharac) {

        Charac charac = characRepositoryInterface.findById(idCharac).get();

        int numberOfFightingReports = charac.getFightingReports().size();

        if(numberOfFightingReports > 5)
            numberOfFightingReports = 5;

        for(int i = 0; i < numberOfFightingReports; i++)
            charac.getFightingReports().forEach(fightingReport -> {fightingReport.getAttacks().forEach(Integer::intValue);});

        return charac;
    }


}
