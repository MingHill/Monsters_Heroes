public class Monster implements Space {

    private static int numMonsters = 0;

    private String name;
    private int health;
    private final int level;
    private int base_dmg;
    private int defense;
    private int dodge_chance;
    private boolean dead;
    private int goldReward;
    private int experienceReward;

    private SpaceType spaceType;
    private boolean is_accessible;
    private Coordinate coordinate;
    private int monsterID;

    public Monster(String name, int level, int base_dmg, int dodge_chance, int defense) {
        this.name = name;
        this.health = level * 100;
        this.level = level;
        this.base_dmg = base_dmg;
        this.dodge_chance = dodge_chance;
        this.defense = defense;
        this.dead = false;
        this.spaceType = SpaceType.MON;
        this.coordinate = null;
        set_reward();
    }

    public void setCoordinate(Coordinate coord) {
        this.coordinate = coord;
    }

    public SpaceType getSpaceType(){
        return this.spaceType;
    }

    public Coordinate getCoordinate(){
        return this.coordinate;
    }

    public boolean is_accessible(){
        return this.is_accessible;
    }

    public String toString(){
        String output = "";
        output += "Monster name: " + this.name + "\n   Level: " + this.level + "\n   Health: " + this.health + "\n   Damage: " + this.base_dmg +
                "\n   Dodgechange:   " + this.dodge_chance + "\n   Defense:   " + this.defense + "\n";
        return output;
    }

    private void set_reward(){
        this.goldReward = this.getLevel() * 100;
        this.experienceReward = this.getLevel() / 2;
    }

    public boolean is_dead(){
        return this.dead;
    }


    public int getGoldReward(){
        return this.goldReward;
    }

    public int getExperienceReward(){
        return this.experienceReward;
    }
    public void reduceHealth(float damage){
        this.health -= damage;
        if (this.health <= 0){
            this.dead = true;
        }
    }

    public void reduceDamage(){
        this.base_dmg = (int)(this.getDamage() * 0.01f);
    }

    public void reduceDefense(){
        this.defense = (int)(this.getDefense() * 0.01f);
    }

    public void reduceDodgeChance(){
        this.dodge_chance = (int)(this.getDodgeChance() * 0.01f);
    }

    public String getName(){
        return this.name;
    }

    public float attack(){
        return this.base_dmg;
    }

    public int getLevel(){
        return this.level;
    }

    public int getMonsterID() {
	return this.monsterID;
    }

    public void setMonsterID() {
	this.monsterID = ++numMonsters;
    }

    public int getDamage(){return this.base_dmg;}
    public int getDefense(){return this.defense;}
    public int getDodgeChance(){return this.dodge_chance;}


}
