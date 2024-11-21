import java.io.*;
import java.nio.file.*;
import java.util.*;




public class Fetch {
    private static String[] monsterPaths = new String[]{
            "data/Dragons.txt",
            "data/Exoskeletons.txt",
            "data/Spirits.txt"
    };

    private static String[] heroesPaths = new String[]{
            "data/Paladins.txt",
            "data/Warriors.txt",
            "data/Sorcerers.txt"
    };


    public static List<Item> fetchItems(int capacity, String path, ItemType type) throws IOException {
        List<Item> items = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        lines = Files.readAllLines(Paths.get(path));

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (!line.isEmpty()) {
                String[] fields = line.split("\\s+");
                Item newItem = createItems(fields, type);
                items.add(newItem);
            }
        }
        List<Item> selectedItems = new ArrayList<>();
        Set<Integer> chosenIndices = new HashSet<>();

        Random rand = new Random();
        while (selectedItems.size() < capacity && chosenIndices.size() < items.size()) {
            int index = rand.nextInt(items.size());
            if (!chosenIndices.contains(index)) {
                selectedItems.add(items.get(index));
                chosenIndices.add(index);
            }
        }
        return selectedItems;

    }

    public static Item createItems(String[] fields, ItemType type){
        Item item = null;
        String name;
        int price;
        int level;

        switch (type) {
            case ARM:
                name = fields[0];
                price = Integer.parseInt(fields[1]);
                level = Integer.parseInt(fields[2]);
                float dm_reduction = Float.parseFloat(fields[3]);
                item = new Armor(name, price, level, dm_reduction);
                break;
            case WEP:
                name = fields[0];
                price = Integer.parseInt(fields[1]);
                level = Integer.parseInt(fields[2]);
                int damage = Integer.parseInt(fields[3]);
                int num_hands = Integer.parseInt(fields[4]);
                item = new Weapon(name, price, level, damage, num_hands);
                break;
            case SPL:
                name = fields[0];
                price = Integer.parseInt(fields[1]);
                level = Integer.parseInt(fields[2]);
                damage = Integer.parseInt(fields[3]);
                float mana_cost = Float.parseFloat(fields[4]);
                SpellType spellType = SpellType.valueOf(fields[5]);
                item = new Spell(name, damage, price, level, mana_cost, spellType);
                break;
            case POT:
                name = fields[0];
                price = Integer.parseInt(fields[1]);
                level = Integer.parseInt(fields[2]);
                float effect_amount = Float.parseFloat(fields[3]);
                PotionType potionType = PotionType.valueOf(fields[4]);
                item = new Potion(name, price, level, effect_amount, potionType);
                break;
        }
        return item;
    }

    public static List<Hero> fetchHeroes() throws IOException {
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

    public static Monster fetchMonster(int highestLevel) throws IOException {
	    return fetchMonsters(1, highestLevel).getFirst();
    }

    public static List<Monster> fetchMonsters(int party_size, int highestLevel) throws IOException {
        List<Monster> monsterList = new ArrayList<>();
        List<String> lines = new ArrayList<>();

        for(String path : monsterPaths) {
            lines = Files.readAllLines(Paths.get(path));
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (!line.isEmpty()) {
                    String[] fields = line.split("\\s+");
                    Monster newMonster = createMonster(fields);
                    if (newMonster.getLevel() > highestLevel + 1 || newMonster.getLevel() < highestLevel - 1) {
                        continue;
                    }
                    monsterList.add(newMonster);
                }
            }
        }
        List<Monster> selectedMonsters = new ArrayList<>();
        Set<Integer> chosenIndices = new HashSet<>();
        Random rand = new Random();
        while (selectedMonsters.size() < party_size && chosenIndices.size() < monsterList.size()) {
            int index = rand.nextInt(monsterList.size());
            if (!chosenIndices.contains(index)) {
                selectedMonsters.add(monsterList.get(index));
                chosenIndices.add(index);
            }
        }
        return selectedMonsters;
    }

    private static Monster createMonster(String[] fields){
        String name = fields[0];
        int level = Integer.parseInt(fields[1]);
        int damage = Integer.parseInt(fields[2]);
        int defense = Integer.parseInt(fields[3]);
        int dodge_chance = Integer.parseInt(fields[4]);
        return new Monster(name, level, damage, dodge_chance, defense);
    }
}
