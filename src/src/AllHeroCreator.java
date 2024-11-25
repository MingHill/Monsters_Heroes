
 /*
  * AllHeroCreator.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class AllHeroCreator implements HeroCreator {

    private static String[] heroesPaths = new String[]{
            "data/Paladins.txt",
            "data/Warriors.txt",
            "data/Sorcerers.txt"
    };

    public List<Hero> createHeroes() throws IOException {
	List<Hero> heroList = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        int c = 1;
        for (String path : heroesPaths) {
            lines = Files.readAllLines(Paths.get(path));
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (!line.isEmpty()) {
                    String[] fields = line.split("\\s+");
                    Hero newHero = createHero(fields);
                    heroList.add(newHero);
                    System.out.println(c + ": " + newHero + "\n");
                    c++;
                }
            }
        }
        return heroList;
    }

    private static Hero createHero(String[] fields){
        String name = fields[0];
        float mana = Float.parseFloat(fields[1]);
        float strength = Float.parseFloat(fields[2]);
        float agility = Float.parseFloat(fields[3]);
        float dexterity = Float.parseFloat(fields[4]);
        int starting_money = Integer.parseInt(fields[5]);
        int starting_exp = Integer.parseInt(fields[6]);
        HeroType type = HeroType.valueOf(fields[7]);
        return new Hero(name, starting_exp, mana, strength, agility, dexterity, type, starting_money);
    }
}
