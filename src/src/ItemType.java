/*
May not need this class
 */

public enum ItemType {
    SWR("Health Item"),
    SPL("Mana Item"),
    POT("Strength Item"),
    ARM("Armor Item");


    private final String name;
    ItemType(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

}
