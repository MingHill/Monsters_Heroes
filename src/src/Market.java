import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class Market implements Space{
    private Coordinate location;
    private HashMap<ItemType, List<Item>> menu;
    private boolean is_accesible;

    public Market(Coordinate location) {
        this.location = location;
        this.is_accesible = true;
        //this.menu = fill_menu();
    }

    // Placeholder for filling a menu
    // Each market will have 3 random potions, 3 random weapons, 2 random armor, 2 of each type of spell
    private HashMap<ItemType, Item[]> fill_menu() {

    }

    public String toString() {
        return " M ";
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
        for (HashMap.Entry<ItemType, List<Item>> entry: this.menu.entrySet()){
            ItemType key = entry.getKey();
            List<Item> value = entry.getValue();
            System.out.println("Item Type: " + key.getName());
            for (Item item : value){
                System.out.println(item.toString());
            }
        }
    }

}



