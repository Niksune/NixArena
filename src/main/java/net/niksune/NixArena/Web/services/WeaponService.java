package net.niksune.NixArena.Web.services;

import net.niksune.NixArena.Web.beans.Weapon;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class WeaponService {

    public static int initAttack(int level){
        //-4 to 5
        int variance = ThreadLocalRandom.current().nextInt(-4, 6);
        return 10*level + variance;
    }

    public static Weapon newWeapon(int level){

        String[] names = {"Rapier","Dagger","Short Blade","Long Blade","Two Handed Blade","Scimitar",
        "Short Bow","Long Bow","Composite Bow","Knife","Crossbow","Shurikens","Katana","Staff","Bo","Nunchaku",
        "Throwing Knifes","Claws","Magic Gauntlet","Axe","Morning Star","Mace"};

        int numberInArray = ThreadLocalRandom.current().nextInt(0, names.length);
        int levelWeapon = ThreadLocalRandom.current().nextInt(1, level+1);

        return new Weapon(names[numberInArray],levelWeapon);
    }

}
