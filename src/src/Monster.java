public class Monster {
    private String name;
    private int health;
    private int level;
    private int mana;
    private int base_dmg;
    private int defense;
    private int dodge_chance;
    private boolean dead;

    public Monster(String name, int health, int level, int base_dmg, int dodge_chance, int defense) {
        this.name = name;
        this.health = health;
        this.level = level;
        this.base_dmg = base_dmg;
        this.dodge_chance = dodge_chance;
        this.defense = defense;
        this.dead = false;
    }

    public boolean is_dead(){
        return this.dead;
    }

    public void reduceHealth(float damage){
        this.health -= damage;
        if (this.health <= 0){
            this.dead = true;
        }
    }

    public String getName(){
        return this.name;
    }

    public float attack(){
        return this.base_dmg;
    }


}
