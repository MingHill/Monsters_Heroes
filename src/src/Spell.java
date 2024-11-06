public class Spell implements Item{
    private String name;
    private int price;
    private int level;
    private float mana_cost;
    private SpellType spellType;
    private int uses_left;
    private float damage;
    private ItemType itemType;

    public Spell(String name, float damage, int price, int level, float mana_cost, SpellType spellType) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.mana_cost = mana_cost;
        this.spellType = spellType;
        this.uses_left = 2;
        this.damage = damage;
        this.itemType = ItemType.SPL;
    }

    public String getName() {
        return this.name;
    }

    public String toString(){
        String output = "";
        output += "Spell   \n   Name: " + this.name + "\n   Spell Type: " + this.spellType.getName() + "\n   Price: " +
                this.price + " \n   Level: " + this.level + " \n   Damage: " + this.damage + " \n";
        return output;
    }

    public int repairItem(){
        this.uses_left = 2;
        return this.getPrice() / 2;
    }

    public void reduceUse(){
        this.uses_left--;
    }

    public int uses_left() {
        return this.uses_left;
    }
    public SpellType getSpellType() {return this.spellType;}
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
