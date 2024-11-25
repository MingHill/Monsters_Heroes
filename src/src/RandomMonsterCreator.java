
 /*
  * RandomMonsterCreator.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class RandomMonsterCreator implements MonsterCreator {

    private static String[] monsterPaths = new String[]{
            "data/Dragons.txt",
            "data/Exoskeletons.txt",
            "data/Spirits.txt"
    };

    public List<Monster> createMonsters(int numMonsters, int maxLevel) throws IOException {
	List<Monster> monsterList = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        for(String path : monsterPaths) {
            lines = Files.readAllLines(Paths.get(path));
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (!line.isEmpty()) {
                    String[] fields = line.split("\\s+");
                    Monster newMonster = createMonster(fields);
                    if (newMonster.getLevel() > maxLevel + 1 || newMonster.getLevel() < maxLevel - 1) {
                        continue;
                    }
                    monsterList.add(newMonster);
                }
            }
        }
        List<Monster> selectedMonsters = new ArrayList<>();
        Set<Integer> chosenIndices = new HashSet<>();
        Random rand = new Random();
        while (selectedMonsters.size() < numMonsters && chosenIndices.size() < monsterList.size()) {
            int index = rand.nextInt(monsterList.size());
            if (!chosenIndices.contains(index)) {
                selectedMonsters.add(monsterList.get(index));
                chosenIndices.add(index);
            }
        }
        return selectedMonsters;
    }

    private Monster createMonster(String[] fields) {
	String name = fields[0];
        int level = Integer.parseInt(fields[1]);
        int damage = Integer.parseInt(fields[2]);
        int defense = Integer.parseInt(fields[3]);
        int dodge_chance = Integer.parseInt(fields[4]);
        return new Monster(name, level, damage, dodge_chance, defense);
    }



}
