
 /*
  * Item.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


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
