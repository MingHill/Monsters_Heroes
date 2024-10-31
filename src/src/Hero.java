import java.util.HashMap;

public class Hero {
    private String name;
    private int health;
    private int level;
    private int mana;
    private int strength;
    private int agility;
    private int dexterity;
    private int gold;
    private HashMap<String, Item> inventory = new HashMap<>(); //May switch to enum

    public Hero(String name, int health, int level, int mana, int strength, int agility, int dexterity) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.gold = 0;
    }


}
