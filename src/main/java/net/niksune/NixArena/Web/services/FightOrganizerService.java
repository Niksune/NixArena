package net.niksune.NixArena.Web.services;

import net.niksune.NixArena.Web.beans.Charac;
import net.niksune.NixArena.Web.beans.FightingReport;
import net.niksune.NixArena.Web.repositories.AccountRepositoryInterface;
import net.niksune.NixArena.Web.repositories.CharacRepositoryInterface;
import net.niksune.NixArena.Web.repositories.FightingReportRepositoryInterface;
import net.niksune.NixArena.Web.repositories.WeaponRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class FightOrganizerService {

    @Autowired
    private AccountRepositoryInterface accountRepositoryInterface;
    @Autowired
    private CharacRepositoryInterface characRepositoryInterface;
    @Autowired
    private WeaponRepositoryInterface weaponRepositoryInterface;
    @Autowired
    private FightingReportRepositoryInterface fightingReportRepositoryInterface;

    @Autowired
    private FightingService fightingService;
    @Autowired
    private FightPreparerService fightPreparerService;

    @Transactional
    public int allFights() {

        List<Charac> allCharacters = characRepositoryInterface.findAllByOrderByLevelAsc();

        allCharacters = fightPreparerService.randomizer(allCharacters);

        for (int i = 0; i < allCharacters.size() - 1; i += 2) {
            Charac charac1 = allCharacters.get(i);
            Charac charac2 = allCharacters.get(i + 1);

            int result = fightingService.duel(charac1, charac2);
            if (result == 1) {
                victory(charac1);
                lose(charac2);
            } else if (result == 2 && charac1.getLevel() == charac2.getLevel()) {
                lose(charac1);
                victory(charac2);
            }
            //That means that a more leveled character won
            //By the rule of orders charac1 is always less leveled or egal than charac2
            else if (result == 2 && charac1.getLevel() != charac2.getLevel()) {
                lose(charac1);
                victoryOutLevel(charac2);
            }
        }

        if (allCharacters.size() % 2 == 1)
            soloChar(allCharacters.get(allCharacters.size() - 1));

        return 1;
    }

    @Transactional
    public int manualFight(UUID id) {
        List<Charac> allCharacters = characRepositoryInterface.findAllByOrderByLevelAsc();
        Charac player = characRepositoryInterface.getById(id);

        //Loop to get characters weaker than the player
        while (true){
            //Lists to get characters in function our level
            List<Charac> lowLevel = new ArrayList<>();
            List<Charac> sameLevel = new ArrayList<>();

            for(Charac chara:allCharacters) {
                if (chara.getID() == player.getID()){
                    ;
                }else if (chara.getLevel() == player.getLevel()){
                    sameLevel.add(chara);
                }else if (chara.getLevel() < player.getLevel()) {
                    lowLevel.add(chara);
                }
            }

            //re-assigns characters in allCharacters
            allCharacters.clear();
            if (sameLevel.size()>0) {
                allCharacters = sameLevel;
            }else {
                allCharacters = lowLevel;
            }
            break;
            }

        Random rand = new Random();
        int sizeNumber = allCharacters.size();
        int randomNumber = rand.nextInt(sizeNumber);

        Charac charac1 = player;
        Charac charac2 = allCharacters.get(randomNumber);

        int result = fightingService.duel(charac1, charac2);
        if (result == 1) {
            victory(charac1);
            lose(charac2);
        } else if (result == 2 && charac1.getLevel() == charac2.getLevel()) {
            lose(charac1);
            victory(charac2);
        }
        //That means that a more leveled character won
        //By the rule of orders charac1 is always less leveled or egal than charac2
        else if (result == 2 && charac1.getLevel() != charac2.getLevel()) {
            lose(charac1);
            victoryOutLevel(charac2);
            };

        return 1;
    }

    public void victory(Charac charac) {
        charac.levelUp();
        characRepositoryInterface.save(charac);
    }

    public void victoryOutLevel(Charac charac) {
        //Gets the just made fighting report
        charac.getFightingReports().get(charac.getFightingReports().size() - 1).setSpecialText("OverLevel");
        if (charac.getOwnerAccount().getWeaponsStored().size() < 25)
            charac.getOwnerAccount().getWeaponsStored().add(WeaponService.newWeapon(charac.getLevel() + 5));
        characRepositoryInterface.save(charac);

    }

    public void lose(Charac charac) {
        if (charac.getOwnerAccount().getWeaponsStored().size() < 25)
            charac.getOwnerAccount().getWeaponsStored().add(WeaponService.newWeapon(charac.getLevel()));
        characRepositoryInterface.save(charac);
    }

    public void soloChar(Charac charac) {
        FightingReport fightingReport = new FightingReport("Alone");
        charac.getFightingReports().add(fightingReport);

        lose(charac);
    }

}
