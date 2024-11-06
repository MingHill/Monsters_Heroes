
public class Weapon implements Item, Equipable {
    private String name;
    private int price;
    private int level;
    private int damage;
    private int num_hands;
    private boolean is_equipped;
    private ItemType itemType;
    private int uses_left;

    public Weapon (String name, int price, int level, int damage, int num_hands) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.damage = damage;
        this.num_hands = num_hands;
        this.is_equipped = false;
        this.itemType = ItemType.WEP;
        this.uses_left = 4;
    }

    public String toString(){
        String output = "";
        output += "Weapon:\n   Name: " + this.name + "\n   Price: " + this.price + " \n   Level: " + this.level +
                "\n   Damage: " + this.damage + "\n   Number of Hands Required: " + this.num_hands +  "\n   Equiped: " + this.is_equipped + "\n   Uses Left: " + this.uses_left + "\n";
        return output;

    }
    public void setEquip(boolean equip){
        this.is_equipped = equip;
    }

    public int repairItem(){
        this.uses_left = 4;
        return this.getPrice() / 2;
    }


    public ItemType getItemType(){
        return this.itemType;
    }
    public String getName(){
        return this.name;
    }
    public int getDamage(){return this.damage;}
    public int getLevel(){
        return this.level;
    }
    public int getPrice(){return this.price;}

    public void reduceUse(){
        this.uses_left -= 1;
    }
    public int uses_left(){
        return this.uses_left;
    }
}
