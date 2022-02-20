package net.niksune.NixArena.Web.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CharacService {

    public String randomName() {

        String[] names = {"Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie", "Ashe", "Auerlion Sol", "Azir", "Bard", "Blitzcrank", "Brand", "Braum", "Caitlyn", "Camille", "Cassiopeia", "Cho’Gath", "Corki", "Darius", "Diana", "Dr. Mundo", "Draven", "Ekko", "Elise", "Evelynn", "Ezreal", "Fiddlesticks", "Fiora", "Fizz", "Galio", "Gangplank", "Garen", "Gnar", "Gragas", "Graves", "Hecarim", "Heimerdinger", "Illaoi", "Irelia", "Ivern", "Janna", "Jarvan IV", "Jax", "Jayce", "Jhin", "Jinx", "Kalista", "Karma", "Karthus", "Kassadin", "Katarina", "Kayle", "Kennen", "Kha’Zix", "Kindred", "Kled", "Kog’Maw", "LeBlanc", "Lee Sin", "Leona", "Lissandra", "Lucian", "Lulu", "Lux", "Malphite", "Malzahar", "Maokai", "Master Yi", "Miss Fortune", "Mordekaiser", "Morgana", "Nami", "Nasus", "Nautilus", "Nidalee", "Nocturne", "Nunu", "Olaf", "Orianna", "Pantheon", "Poppy", "Quinn", "Rammus", "Rek’Sai", "Renekton", "Rengar", "Riven", "Rumble", "Ryze", "Sejuani", "Shaco", "Shen", "Shyvana", "Singed", "Sion", "Sivir",
                "Skarner", "Sona", "Soraka", "Swain", "Syndra", "Tahn Kench", "Taliyah", "Talon", "Taric", "Teemo", "Thresh", "Tristana", "Trundle", "Tryndamere", "Twisted Fate", "Twitch", "Udyr", "Urgot", "Varus", "Vayne", "Veigar", "Vel’Koz", "Vi", "Viktor", "Vladimir", "Volibear", "Warwick", "Wukon", "Xerath", "Xin Zhao", "Yasuo", "Yorick", "Zac", "Zed", "Ziggs", "Zilean", "Zyra", "Astaroth", "Cassandra", "Cervantes", "Charade", "Inferno", "Ivy", "Kilik", "Maxi", "Maxi", "Mitsurugi", "Nightmare", "Raphael", "Seong Mi-na", "Sophitia", "Taki", "Talim", "Voldo", "Xianghua", "Yoshimitsu", "Yunsung", "Abel", "Akuma", "Balrog", "Blanka", "Cammy", "Chun-Li", "Cody", "Dhalsim", "Dudley", "Elena", "Guile", "Guy", "Hugo", "Ibuki", "Juri", "Ken", "M. Bison", "Poison", "Rolento", "Rufus", "Ryu", "Sagat", "Sakura", "Vega", "Zangief", "Alisa", "Asuka", "Bob", "Bryan", "Christie", "Heihachi", "Hwoarang", "Jack-X", "Jin", "Julia", "Kazuya", "King", "Kuma", "Lars", "Law", "Lei", "Lili", "Marduk", "Nina", "Ogre", "Paul",
                "Raven", "Steve", "Xiaoyu", "Yoshimitsu", "Cole", "Kuro", "Mega Man", "Pac-Man", "Toro"};

        int rnd = new Random().nextInt(names.length);
        return names[rnd];
    }

}
