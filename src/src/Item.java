public interface Item {
// Make hashcode for each item
    int hashCode();
    ItemType getItemType();
    String getName();
    int getLevel();
    int getPrice();
    int uses_left();
    int repairItem();
}
