import java.io.*;
import java.nio.file.*;
import java.util.*;


public class Fetch {
    public static List<Item> fetchArmor(int capacity, String path, ItemType type) throws IOException {
        List<Item> items = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        lines = Files.readAllLines(Paths.get(path));
        Random rand = new Random();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (!line.isEmpty()) {
                String[] fields = line.split("\\s+");
                Item newItem = create(fields, type);
                items.add(newItem);
            }
        }
        List<Item> selectedItems = new ArrayList<>();
        Set<Integer> chosenIndices = new HashSet<>();

        while (selectedItems.size() < capacity && chosenIndices.size() < items.size()) {
            int index = rand.nextInt(items.size());
            if (!chosenIndices.contains(index)) {
                selectedItems.add(items.get(index));
                chosenIndices.add(index);
            }
        }

        return selectedItems;

    }

    public static Item create(String[] fields, ItemType type){
        Item item;
        String name;
        int price;
        int level;

        switch (type){
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
                item = new Spell(name, damage, price, level, mana_cost,spellType);
                break;
            case POT:
                name = fields[0];
                price = Integer.parseInt(fields[1]);
                level = Integer.parseInt(fields[2]);
                float effect_amount = Float.parseFloat(fields[3]);
                PotionType potionType = PotionType.valueOf(fields[4]);
                item = new Potion(name, price, level, effect_amount, effect_amount, potionType)


        }
    }
}
