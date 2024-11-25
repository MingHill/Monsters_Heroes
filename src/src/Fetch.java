
 /*
  * Fetch.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * This is a very simple facade for the factories we've created for each of the object types
  * that is initialized from files. Each of the factories is instantiated as a particular type
  * of factory: anywhere Fetch is used to create objects, that factory will be called. If different
  * factories are needed, the factories can be instantiated and called directly.
  *
  * Credits: All code is our own.
  */

import java.io.*;
import java.nio.file.*;
import java.util.*;


public class Fetch {

    private static MonsterCreator monsterFactory = new RandomMonsterCreator();
    private static HeroCreator heroFactory = new AllHeroCreator();
    private static ItemCreator itemFactory = new RandomItemCreator();

    public static List<Item> fetchItems(int capacity, String path, ItemType type) throws IOException {
	return itemFactory.createItems(capacity, path, type);
    }

    public static List<Hero> fetchHeroes() throws IOException {
	return heroFactory.createHeroes();
    }

    public static Monster fetchMonster(int highestLevel) throws IOException {
	return monsterFactory.createMonsters(1, highestLevel).get(0);
    }

    public static List<Monster> fetchMonsters(int partySize, int highestLevel) throws IOException {
	return monsterFactory.createMonsters(partySize, highestLevel);
    }

}
