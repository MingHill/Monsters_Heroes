public class Armor implements Item{
    private String name;
    private int price;
    private int level;
    private float damage_reduction; // maybe change to float?
    private boolean is_equipped;
    private ItemType itemType;


    public Armor(String name, int price, int level, float damage_reduction) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.damage_reduction = damage_reduction;
        this.is_equipped = false;
        this.itemType = ItemType.ARM;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel(){
        return this.level;
    }
    public String toString(){
       String output = "";
       output += "Armor(\n Name: " + this.name + " \n Price: " + this.price + " \n Level: " + this.level + " \n Damage Reduction: " + this.damage_reduction + " )\n";
       return output;
    }

    public ItemType getItemType(){
        return this.itemType;
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
}
