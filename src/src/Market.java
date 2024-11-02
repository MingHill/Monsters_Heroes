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
        this.menu = fill_menu();
    }

    // Placeholder for filling a menu
    private HashMap<ItemType, Item[]> fill_menu() {
        return new HashMap<ItemType, Item[]>();
    }

    public String toString() {
        return "Market";
    }

//    public boolean addItem(Item item) {
//        this.menu.put(item, new Item[]{item});
//        return true;
//    }

//    public boolean removeItem(Item item) {
//        Item[] sublist = this.menu.get(item);
//        for (Item item: sublist){
//
//        }
//    }


}
