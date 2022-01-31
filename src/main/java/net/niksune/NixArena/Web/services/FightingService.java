package net.niksune.NixArena.Web.services;

import net.niksune.NixArena.Web.beans.Charac;
import net.niksune.NixArena.Web.beans.FightingReport;
import net.niksune.NixArena.Web.repositories.CharacRepositoryInterface;
import net.niksune.NixArena.Web.repositories.FightingReportRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class FightingService {

    @Autowired
    FightingReportRepositoryInterface fightingReportRepositoryInterface;
    @Autowired
    CharacRepositoryInterface characRepositoryInterface;

    public int duel(Charac charac1, Charac charac2) {

        int charac1HP = 100 * charac1.getLevel();
        int charac2HP = 100 * charac2.getLevel();

        FightingReport fightingReport = initializeFightingReport(charac1, charac2);

        int currentAttack;

        while (true) {
            currentAttack = attackCalculation(charac1);
            fightingReport.getAttacks().add(currentAttack);
            charac2HP -= currentAttack;
            if (charac2HP <= 0) {
                break;
            }

            currentAttack = attackCalculation(charac2);
            fightingReport.getAttacks().add(currentAttack);
            charac1HP -= currentAttack;
            if (charac1HP <= 0) {
                break;
            }

        }

        characRepositoryInterface.save(charac1);
        characRepositoryInterface.save(charac2);

        if (charac2HP <= 0) {
            return 1;
        } else
            return 2;

    }

    public int attackCalculation(Charac charac) {
        return ThreadLocalRandom.current().nextInt(charac.getLevel(), charac.getTotalAttack() + 1);
    }

    public FightingReport initializeFightingReport(Charac charac1, Charac charac2) {
        FightingReport fightingReport = new FightingReport(charac1, charac2, charac1.getName(), charac2.getName(), charac1.getLevel(), charac2.getLevel());

        if (charac1.getWeaponEquipped() != null) {
            fightingReport.setWeaponCharac1Name(charac1.getWeaponEquipped().getName());
            fightingReport.setWeaponCharac1Attack(charac1.getWeaponEquipped().getAttack());
        } else {
            fightingReport.setWeaponCharac1Name("Bare Hands");
            fightingReport.setWeaponCharac1Attack(0);
        }

        if (charac2.getWeaponEquipped() != null) {
            fightingReport.setWeaponCharac2Name(charac2.getWeaponEquipped().getName());
            fightingReport.setWeaponCharac2Attack(charac2.getWeaponEquipped().getAttack());
        } else {
            fightingReport.setWeaponCharac2Name("Bare Hands");
            fightingReport.setWeaponCharac2Attack(0);
        }

        charac1.getFightingReports().add(fightingReport);
        charac2.getFightingReports().add(fightingReport);

        return fightingReport;
    }
}
