public class Monster {
    private String name;
    private int health;
    private int level;
    private int mana;
    private int base_dmg;
    private int agility; // ability to dodge
    private int defense;
    private int dodge_change;

    public Monster(String name, int health, int level, int base_dmg, int agility, int defense) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.base_dmg = base_dmg;
        this.agility = agility;
        this.defense = defense;
    }



}
