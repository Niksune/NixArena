package net.niksune.NixArena.Web.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class WeaponService {

    public static int initAttack(int level){
        //-4 to 5
        int variance = ThreadLocalRandom.current().nextInt(-4, 6);
        return 10*level + variance;
    }

}
