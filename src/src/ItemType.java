/*
May not need this class
 */

public enum ItemType {
    WEP("Weapon Item"),
    SPL("Spell Item"),
    POT("Potion Item"),
    ARM("Armor Item");


    private final String name;
    ItemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
