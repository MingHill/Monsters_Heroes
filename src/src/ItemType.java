
 /*
  * ItemType.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


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
