public class Spell implements Item{
    private String name;
    private int price;
    private int level;
    private float mana_cost;
    private SpellType type;
    private boolean in_stock;
    private float damage;
    private ItemType itemType;

    public Spell(String name, float damage, int price, int level, float mana_cost, SpellType type) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.mana_cost = mana_cost;
        this.type = type;
        this.in_stock = true;
        this.damage = damage;
        this.itemType = ItemType.SPL;
    }

    public String getName() {
        return this.name;
    }

    public String toString(){
        String output = "";
        output += "Spell(\n Name: " + this.name + "Spell Type: " + this.type.getName() + "\n Price: " +
                this.price + " \n Level: " + this.level + " \n Damage: " + this.damage + " )\n";
        return output;
    }

    public float getDamage(){
        return damage;
    }
    public float getManaCost(){
        return this.mana_cost;
    }

    public ItemType getItemType(){
        return this.itemType;
    }
    public int getPrice(){return this.price;}
    public int getLevel(){return this.level;}

}
