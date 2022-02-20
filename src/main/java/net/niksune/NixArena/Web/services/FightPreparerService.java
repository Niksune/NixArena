package net.niksune.NixArena.Web.services;

import net.niksune.NixArena.Web.beans.Charac;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FightPreparerService {

    public List<Charac> randomizer(List<Charac> characters){

//        for(Charac charac : characters)
//            System.out.println(charac);

        int maxLevel = maxLevel(characters);

//        System.out.println(maxLevel);

        List<List<Charac>> charactersByLevel = new ArrayList<>();

        for(int i = 0;i<=maxLevel;i++)
            charactersByLevel.add(new ArrayList<>());

        for(Charac charac : characters)
            charactersByLevel.get(charac.getLevel()).add(charac);

        //No charac has lvl 0 but we do not care
        for(int i = 0;i<=maxLevel;i++)
            Collections.shuffle(charactersByLevel.get(i));

        characters = new ArrayList<>();

        for(int i = 0;i<=maxLevel;i++)
            characters.addAll(charactersByLevel.get(i));

//        for(Charac charac : characters)
//            System.out.println(charac);

        return characters;
    }

    public int maxLevel(List<Charac> characters){

        int max = 0;

        for(Charac charac : characters)
            if(charac.getLevel() > max)
                max = charac.getLevel();

        return max;
    }
}
