import java.util.ArrayList;
import java.util.HashMap;

public class Hero {
    private String name;
    private int health;
    private int level;
    private float mana;
    // skills
    private float strength;
    private float agility;
    private float dexterity;

    private int gold;

    private float currentExperience;
    private float dodge_chance;
    private HashMap<String, Item> inventory = new HashMap<>(); //May switch to enum
    private HeroType heroType;
    private Armor armor;

    public Hero(String name, int health, int level, float mana, float strength, float agility, float dexterity, HeroType heroType) {
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
        this.armor = null;
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
        this.displayStats();
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

    public String displayStats(){
        String output = "";
        output += " Level: " + this.getLevel() + "\n Health: " + this.getHealth() + "\n Mana: " + this.getMana() + "\n Strength: " + this.getStrength() + "\n Agility: " + this.getAgility() + "\n Dexterity: " + this.getDexterity();
        return output;
    }

    public String displayInventory(){
        return "";
    }


    public int getHealth() {return this.health;}
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
