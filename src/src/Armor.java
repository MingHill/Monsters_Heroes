
 /*
  * Armor.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


public class Armor implements Item, Equipable {
    private String name;
    private int price;
    private int level;
    private float damage_reduction; // maybe change to float?
    private boolean is_equipped;
    private ItemType itemType;
    private int uses_left;


    public Armor(String name, int price, int level, float damage_reduction) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.damage_reduction = damage_reduction;
        this.is_equipped = false;
        this.itemType = ItemType.ARM;
        this.uses_left = 3;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel(){
        return this.level;
    }
    public String toString(){
       String output = "";
       output += "Armor\n   Name: " + this.name + " \n   Price: " + this.price + " \n   Level: " + this.level + " \n   Damage Reduction: " + this.damage_reduction + " \n   Equiped: " + this.is_equipped + " \n   Uses Left: " + this.uses_left;
       return output;
    }

    public ItemType getItemType(){
        return this.itemType;
    }

    public int repairItem(){
        this.uses_left = 3;
        return this.getPrice() / 2;
    }

    public void setEquip(boolean equip){
        this.is_equipped = equip;
    }
    public float getDamageReduction(){
        return this.damage_reduction;
    }
    public int getPrice(){return this.price;}
    public boolean isEquipped(){
        return this.is_equipped;
    }
    public int uses_left(){return this.uses_left;}
    public void reduceUse(){
        this.uses_left--;
        if (this.uses_left == 0){
            this.is_equipped = false;
            System.out.println(this.getName() + " has shattered! It is now unquiped! You may repair it at a market. ");
        }
    }
}
