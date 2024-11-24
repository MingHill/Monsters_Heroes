import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Market implements Space{
    private Coordinate location;
    private HashMap<ItemType, List<Item>> menu;
    private boolean is_accesible;
    private final SpaceType spaceType;

    public Market(Coordinate location) throws IOException {
        this.location = location;
        this.is_accesible = true;
        this.menu = fill_menu();
        this.spaceType = SpaceType.MAR;
    }

    // Placeholder for filling a menu
    // Each market will have 3 random potions, 3 random weapons, 2 random armor, 2 of each type of spell
    private HashMap<ItemType, List<Item>> fill_menu() throws IOException {
        List<Item> armorItems = Fetch.fetchItems(2, "data/Armory.txt", ItemType.ARM);
        List<Item> weaponItems = Fetch.fetchItems(3, "data/Weaponry.txt", ItemType.WEP);
        List<Item> potionItems = Fetch.fetchItems(3, "data/Potions.txt", ItemType.POT);
        List<Item> iceSpellItems = Fetch.fetchItems(2, "data/IceSpells.txt", ItemType.SPL);
        List<Item> fireSpellItems = Fetch.fetchItems(2, "data/FireSpells.txt", ItemType.SPL);
        List<Item> lightSpellItems = Fetch.fetchItems(2, "data/LightningSpells.txt", ItemType.SPL);
        List<Item> spellItems = new ArrayList<>();
        spellItems.addAll(lightSpellItems);
        spellItems.addAll(fireSpellItems);
        spellItems.addAll(iceSpellItems);
        HashMap<ItemType, List<Item>> menu = new HashMap<>();
        menu.put(ItemType.ARM, armorItems);
        menu.put(ItemType.SPL, spellItems);
        menu.put(ItemType.WEP, weaponItems);
        menu.put(ItemType.POT, potionItems);
        return menu;
    }

    public String toString() {
        return "M";
    }

    public Coordinate getCoordinate() {
        return this.location;
    }

   public boolean addItem(Item item) {
        ItemType itemType = item.getItemType();
        this.menu.putIfAbsent(itemType, new ArrayList<Item>());
        this.menu.get(itemType).add(item);
        return true;
    }

    public HashMap<ItemType, List<Item>> getMenu() {
        return this.menu;
    }

    public boolean removeItem(Item item) {
        ItemType itemType = item.getItemType();
        List<Item> items = this.menu.get(itemType);
        if (items != null) {
            boolean removed = items.remove(item);
            return true;
        }
        return false;
    }

    public void viewMenu(){
        int j = 1;
        for (HashMap.Entry<ItemType, List<Item>> entry: this.menu.entrySet()){
            ItemType key = entry.getKey();
            List<Item> value = entry.getValue();
            System.out.println("Item Type: " + key.getName());
            System.out.println("-----------------------------------------------------------------");
            for (Item item : value){
                System.out.println(j + ". " +item.toString());
                j++;
            }
        }
    }

    public boolean is_accessible(){
        return this.is_accesible;
    }

    public SpaceType getSpaceType(){
        return this.spaceType;
    }

}



