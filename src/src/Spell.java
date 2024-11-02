public class Spell implements Item{
    private String name;
    private int price;
    private int level;
    private float effect_amount;
    private float mana_cost;
    private SpellType type;
    private boolean in_stock;
    private float damage;

    public Spell(String name, float damage, int price, int level, float effect_amount, int mana_cost, SpellType type, boolean in_stock) {
        this.name = name;
        this.price = price;
        this.level = level;
        this.effect_amount = effect_amount;
        this.mana_cost = mana_cost;
        this.type = type;
        this.in_stock = in_stock;
        this.damage = damage;
    }

    public float getDamage(){
        return damage;
    }
    public float getManaCost(){
        return this.mana_cost;
    }
    public int getPrice(){return this.price;}
    public int getLevel(){return this.level;}

}
