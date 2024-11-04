
public class Weapon implements Item{
    private String name;
    private int price;
    private int level;
    private int damage;
    private int num_hands;
    private boolean is_equipped;
    private ItemType itemType;

    public Weapon (String name, int price, int level, int damage, int num_hands) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.damage = damage;
        this.num_hands = num_hands;
        this.is_equipped = false;
        this.itemType = ItemType.WEP;
    }

    public String toString(){
        String output = "";
        output += "Weapon( \n Name: " + this.name + " \n Price: " + this.price + " \n Level: " + this.level +
                " \n Damage: " + this.damage + "\n Number of Hands Required: " + this.num_hands +  ")\n";
        return output;

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
}
