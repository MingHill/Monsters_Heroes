import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Hero{
    private String name;
    private float health;
    private int level;
    private float mana;
    private float strength;
    private float agility;
    private float dexterity;
    private int gold;

    private float currentExperience;
    private float dodge_chance;
    private HashMap<ItemType, List<Item>> inventory = new HashMap<>(); //May switch to enum
    private HeroType heroType;
    private Armor armor;
    private boolean fainted;


    public Hero(String name, float health, int level, float mana, float strength, float agility, float dexterity, HeroType heroType, int gold) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.gold = 0;
        this.dodge_chance = (float) (this.getAgility() * 0.002);
        this.heroType = heroType;
        this.gold = gold;
        this.armor = null;
        this.fainted = false;
    }

    public void checkLevelUp(){
        float threshold = this.getLevel() * 5;
        if (this.getCurrentExperience() >= threshold){
            this.levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.health = 100 * this.getLevel();
        this.mana = (float) (1.1 * this.getMana());
        //add more to level up
        float[] favors = this.getHeroType().getFavors();
        this.strength = (float)(favors[0] * (this.getStrength() * 1.05));
        this.agility = (float)(favors[1] * (this.getAgility() * 1.05));
        this.dexterity = (float)(favors[2] * (this.getDexterity() * 1.05));

        System.out.println(this.getName() + " you are now level " + this.level);
        this.toString();
    }

    public void regen(){
        this.health = this.getHealth() * 1.1f;
        this.mana = this.getMana() * 1.1f;
    }

    public boolean isFainted(){
        return this.fainted;
    }

    public void increaseHealth(float amount){
        this.health += amount;
    }

    public void reduceHealth(float amount){
        this.health -= amount;
        if (this.health <= 0){
            this.fainted = true;
        }
    }

    public float weaponDamage(Weapon weapon){
        return (float) ((this.getStrength() + weapon.getDamage()) * 0.05);
    }

    public float spellDamage(Spell spell){
        return (float)(spell.getDamage() + (this.getDexterity() / 10000) * spell.getDamage());
    }

    public void equipArmor(Armor armor){
        if (this.armor != null){
            Armor removed = this.armor;
            removed.setEquip(false);
        }
        armor.setEquip(true);
        this.armor = armor;
    }

    public String toString(){
        String output = "";
        output += "Name: " + this.getName() + "\n" + "  Level: " + this.getLevel() + "\n  Health: " + this.getHealth() + "\n  Mana: " + this.getMana() + "\n  Strength: " + this.getStrength() + "\n  Agility: " + this.getAgility() + "\n    Dexterity: " + this.getDexterity();
        return output;
    }

    public void displayInventory(){
        for (HashMap.Entry<ItemType, List<Item>> entry: this.inventory.entrySet()){
            ItemType key = entry.getKey();
            List<Item> value = entry.getValue();
            System.out.println("Item Type: " + key.getName());
            for (Item item : value){
                System.out.println(item.toString());
            }
        }
    }

    public boolean selectItem(Item item, String name){
        ItemType type = item.getItemType();
        List<Item> values = this.inventory.get(type);
        Item selected;
        for (Item item1 : values){
            if (item1.getName().equals(name)){
                selected = item1;
                return true;
            }
        }
        return false;
    }

    public boolean buyItem(Item item, String name, Market market){
        if (item.getLevel() > this.getLevel()){
            System.out.println("You're level is not high enough to purchase this item");
            return false;
        }
        if (item.getPrice() > this.getGold()){
            System.out.println("You don't have enough gold to purchase this item");
            return false;
        }
        ItemType type = item.getItemType();
        List<Item> values = market.getMenu().get(type);
        Item selected = null;
        for (Item item1 : values){
            if (item1.getName().equals(name)){
                selected = item1;
            }
        }
        market.removeItem(selected);
        this.gold -= item.getPrice();
        this.inventory.get(type).add(selected);
        return true;
    }

    public boolean sellItem(Item item, String name, Market market){
        ItemType type = item.getItemType();
        List<Item> values = this.inventory.get(type);
        Item selected = null;
        for (Item item1 : values){
            if (item1.getName().equals(name)){
                selected = item1;
            }
        }
        inventory.get(type).remove(selected);
        this.gold += item.getPrice() / 2;
        return true;
    }

    public void usePotion(Potion potion, Monster monster){
    }

    public void useSpell(Spell spell){

    }

    public void useWeapon(Weapon weapon, Monster monster){
        float damage = weaponDamage(weapon);
        monster.reduceHealth(damage);
        Announce.heroAttack(this.getName(), damage, monster);
    }

    public float getHealth() {return this.health;}
    public int getLevel() {return this.level;}
    public int getGold(){return this.gold;}
    public float getMana(){return this.mana;}
    public float getStrength(){return this.strength;}
    public float getAgility(){return this.agility;}
    public String getName(){return this.name;}
    public float getDexterity(){return this.dexterity;}
    public float getCurrentExperience(){return this.currentExperience;}
    public HeroType getHeroType(){return this.heroType;}
    public Armor getArmor(){return this.armor;}

}
