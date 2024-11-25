
 /*
  * Potion.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


public class Potion implements Item{
    private String name;
    private int price;
    private int level;
    private float effect_amount;
    private int uses_left;
    private PotionType potionType;
    private ItemType itemType;

    public Potion(String name, int price, int level, float effect_amount, PotionType potionType){
        this.name = name;
        this.price = price;
        this.level = level;
        this.effect_amount = effect_amount;
        this.uses_left = 1;
        this.potionType = potionType;
        this.itemType = ItemType.POT;
    }

    public String getName() {
        return this.name;
    }

    public void reduceUses(){
        this.uses_left--;
    }

    public int repairItem(){
        this.uses_left = 1;
        return this.getPrice() / 2;
    }

    public PotionType getPotionType(){
        return this.potionType;
    }
    public String toString(){
        String output = "";
        output += "Potion\n   Name: " + this.name + "\n   Potion Type: " + this.potionType.getName() + "\n   Price: " + this.price + " \n   Level: " + this.level + " \n   Effect Amount: " + this.effect_amount + " \n";

        return output;
    }
    public int getLevel(){
        return this.level;
    }
    public int getPrice(){return this.price;}
    public ItemType getItemType(){
         return this.itemType;
    }
    public int uses_left(){return this.uses_left;}

}
