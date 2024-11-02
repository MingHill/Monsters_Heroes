
public class Weapon implements Item{
    private String name;
    private int price;
    private int level;
    private int damage;
    private boolean both_hands;
    private boolean is_equipped;

    public Weapon (String name, int price, int level, int damage, boolean both_hands) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.damage = damage;
        this.both_hands = both_hands;
        this.is_equipped = false;
    }

    public String toString(){
        String output = "";
        output += "Weapon( \n Name: " + this.name + " \n Price: " + this.price + " \n Level: " + this.level +
                " \n Damage: " + this.damage + "\n Both Hand Required: " + this.both_hands +  ")\n";
        return output;

    }

    public int getDamage(){return this.damage;}
}
