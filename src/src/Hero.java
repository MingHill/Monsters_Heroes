
 /*
  * Hero.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Hero implements Space {
    private static int numHeroes = 0;

    private String name;
    private float health;
    private int level;
    private float currentMana;
    private float strength;
    private float agility;
    private float dexterity;
    private int gold;
    private float currentExperience;
    private float dodge_chance;
    private float defense;
    private float totalMana;

    private HashMap<ItemType, List<Item>> inventory = new HashMap<>(); //May switch to enum
    private HeroType heroType;
    private Armor armor;
    private boolean fainted;
    private Weapon weapon;

    private SpaceType spaceType;
    private boolean is_accessible;
    private Coordinate coordinate;
    private int heroID;


    public Hero(String name, int level, float mana, float strength, float agility, float dexterity, HeroType heroType, int gold) {
        this.name = name;
        this.health = level * 100;
        this.level = level;
        this.currentMana = mana;
        this.totalMana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.gold = 0;
        this.dodge_chance = (float) (this.getAgility() * 0.002);
        this.heroType = heroType;
        this.gold = gold;
        this.armor = null;
        this.fainted = false;
        this.weapon = null;
        this.defense = 0.95f; // % of damage blocked
        this.spaceType = SpaceType.HERO;
        this.coordinate = null;
    }

    public void getRewards(int gold, int exp){
        this.gold += gold;
        this.currentExperience += exp;
        System.out.println(this.getName() + " has gained " + gold + " gold and " + exp + " exp.");
        System.out.println(this.getName() + "'s total are now: \n   Gold: " + gold + "\n   Experience: " + exp);
        this.checkLevelUp();
    }

    public static void resetNumHeros(){
        numHeroes = 0;
    }

    public void setCoordinate(Coordinate coord) {
        this.coordinate = coord;
    }

    // need to fix threshold logic
    public void checkLevelUp(){
        float threshold = this.getLevel();
        if (this.getCurrentExperience() >= threshold){
            this.levelUp();
        }
    }

    private void levelUp() {
        this.level++;
        this.health = 100 * this.getLevel();
        this.totalMana = (1.1f * this.getTotalMana());
        this.currentMana = this.getTotalMana();
        this.currentExperience = 0;
        //add more to level up
        float[] favors = this.getHeroType().getFavors();
        this.strength = (float)(favors[0] * (this.getStrength() * 1.05f));
        this.agility = (float)(favors[1] * (this.getAgility() * 1.05f));
        this.dexterity = (float)(favors[2] * (this.getDexterity() * 1.05f));

        System.out.println("Congrats! " + this.getName() + " has leveled up to " + this.level);
        System.out.println("His new stats are: \n");
        this.toString();
    }

    public void roundRegen(){
        this.health = this.getHealth() * 1.1f;
        this.currentMana = this.getMana() * 1.1f;
        System.out.println(this.getName() + " has regenerated. \n   HP: " + this.getHealth() + "\n   MANA: " + this.getMana());
    }

    public void revive(){
        increaseHealth(this.getLevel() / 2f);
        increaseMana((this.getTotalMana() / 2f) - this.getMana());
        System.out.println(this.getName() + " has been revived. \n   HP: " + this.getHealth() + "\n   MANA: " + this.getMana());
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

    // Methods to increase or decrease stats
    public void increaseHealth(float amount){
        this.health += amount;
        System.out.println(this.getName() + " has increased health to " + this.health);
    }
    public void increaseDexterity(float amount){
        this.dexterity += amount;
        System.out.println(this.getName() + " has increased dexterity to " + this.dexterity);
    }
    public void increaseAgility(float amount){
        this.agility += amount;
        System.out.println(this.getName() + " has increased agility to " + this.agility);
    }
    public void increaseStrength(float amount){
        this.strength += amount;
        System.out.println(this.getName() + " has increased strength to " + this.strength);
    }
    public void increaseMana(float amount){
        this.currentMana += amount;
        System.out.println(this.getName() + " has increased mana to " + this.currentMana);
    }
    public void increaseDefense(float amount){
        this.defense += amount;
        System.out.println(this.getName() + " has increased defense to " + this.defense);
    }
    public void reduceHealth(float amount){
        this.health -= amount;
        if (this.health <= 0){
            this.fainted = true;
        }
    }

    // Damage methods
    public float weaponDamage(Weapon weapon){
        return (float) ((this.getStrength() + weapon.getDamage() / 2f));
    }
    public float spellDamage(Spell spell){
        return (float)(spell.getDamage() + (this.getDexterity() / 10000) * spell.getDamage());
    }


    public String toString(){
        String output = "";
        output += "Name: " + this.getName() + "\n    Hero Type: " + this.getHeroType()+ "\n    Level: " + this.getLevel() + "\n    Health: " + this.getHealth() + "\n    Mana: " + this.getMana() + "\n    Strength: " + this.getStrength() + "\n    Agility: " + this.getAgility() + "\n    Dexterity: " + this.getDexterity();
        return output;
    }

    public String displayInventory(){
        String output = this.getName() + "'s Inventory: \n";
        int j = 1;
        for (HashMap.Entry<ItemType, List<Item>> entry: this.inventory.entrySet()){
            ItemType key = entry.getKey();
            List<Item> value = entry.getValue();
            output +=  "Item Type: " + key.getName() + "\n";
            output += "-----------------------------------------------------------------\n";
            for (Item item : value){
                output += "   " + j + ". " + item.toString() + "\n";
                j++;
            }
        }
        return output;
    }

    public boolean displayAvailableItem(ItemType itemType){
        List<Item> itemList = this.inventory.get(itemType);
        if (itemList == null || itemList.isEmpty()){
            System.out.println("No available " + itemType.getName() + "'s ");
            return false;
        }
        for (Item item : itemList){
            System.out.println(" " + item.toString());
        }
        return true;
    }

    // May not need to class
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

    public void repair(Item item){
        int price = item.getPrice() / 2;
        if (this.getGold() < price){
            System.out.println("You don't have enought gold to repair");
        }else{
            price = item.repairItem();
            System.out.println(item.getName() + " has been repaired for " + price + " gold and now has " + item.uses_left() + " uses left");
        }

    }

    // Market interaction methods
    public void buyItem(int item, Market market){
        int j = 1; // match item
        Item selected = null;
        ItemType itemType = null;
        for(HashMap.Entry<ItemType, List<Item>> entry: market.getMenu().entrySet()){
            for(Item item1 : entry.getValue()){
                if(j == item){
                    selected = item1;
                    itemType = entry.getKey();
                    break;
                }
                j++;
            }
            if (selected != null){
                break;
            }
        }
        if (selected == null){
            System.out.println("Item does not exist");
            return;
        }
        if (selected.getLevel() > this.getLevel()){
            System.out.println("You're level is not high enough to purchase this item");
            return;

        }
        if (selected.getPrice() > this.getGold()){
            System.out.println("You don't have enough gold to purchase this item");
            return;
        }
        market.removeItem(selected);
        this.gold -= selected.getPrice();
        this.inventory.putIfAbsent(itemType, new ArrayList<>());
        this.inventory.get(itemType).add(selected);
        System.out.println(this.getName() + " has been purchased " + selected.getName() + " for " + selected.getPrice());
        System.out.println(this.getName() + " now has " + this.getGold() + " gold");
    }
    public void sellItem(int item, Market market){
        int j = 1;
        Item selected = null;
        for(HashMap.Entry<ItemType, List<Item>> entry: this.inventory.entrySet()){
            for(Item item1 : entry.getValue()){
                if(j == item){
                    selected = item1;
                    this.inventory.get(entry.getKey()).remove(selected);
                    this.gold += selected.getPrice() / 2;
                    market.addItem(selected);
                    System.out.println(this.getName() + " has sold " + selected.getName() + " for " + selected.getPrice() / 2);
                    System.out.println(this.getName() + " now has " + this.getGold() + " gold");
                    return;
                }
                j++;
            }
        }
        System.out.println("Item was not found");
    }


    // Use items methods
    public void usePotion(Potion potion){
        potion.reduceUses();
        switch (potion.getPotionType()){
            case HP:
                this.increaseHealth(PotionType.HP.getEffectAmount());
                break;
            case MP:
                this.increaseMana(PotionType.MP.getEffectAmount());
                break;
            case STR:
                this.increaseStrength(PotionType.STR.getEffectAmount());
                break;
            case DEX:
                this.increaseDexterity(PotionType.DEX.getEffectAmount());
                break;
            case AGI:
                this.increaseAgility(PotionType.AGI.getEffectAmount());
                break;
            case MER:
                this.increaseStrength(PotionType.MER.getEffectAmount());
                this.increaseHealth(PotionType.MER.getEffectAmount());
                this.increaseMana(PotionType.MER.getEffectAmount());
                this.increaseAgility(PotionType.MER.getEffectAmount());
                break;
            case AMB:
                this.increaseStrength(PotionType.AMB.getEffectAmount());
                this.increaseHealth(PotionType.AMB.getEffectAmount());
                this.increaseMana(PotionType.AMB.getEffectAmount());
                this.increaseAgility(PotionType.AMB.getEffectAmount());
                this.increaseDexterity(PotionType.AMB.getEffectAmount());
                this.increaseDefense(0.01f);
                break;
            }
            System.out.println("After taking " + potion.getName() + ", " + this.getName() + " new stats are: \n" + this.toString());

    }


    public boolean hasEnoughMana(Spell spell) {
	return this.getMana() >= spell.getManaCost();
    }

    //need to edit to make spell announcements
    public boolean useSpell(Spell spell, Monster monster){
        if (!hasEnoughMana(spell)){
            return false;
        }
        float damage = this.spellDamage(spell);
        spell.reduceUse();
        monster.reduceHealth(damage);
        Announce.heroAttackDamage(this.getName(), damage, monster);
        switch (spell.getSpellType()){
            case ICE:
                monster.reduceDamage();
                System.out.println(this.getName() + " used ice spell and reduced monster damage to " + monster.getDamage());
                break;
            case FIR:
                monster.reduceDefense();
                System.out.println(this.getName() + " used fire spell and reduced monster defense to " + monster.getDefense());
                break;
            case LGT:
                monster.reduceDodgeChance();
                System.out.println(this.getName() + " used lighting spell and reduced monster dodge chance to " + monster.getDodgeChance());
                break;
        }
        if (monster.is_dead()){
            Announce.killedMonster(this.getName(), monster);
        }
        return true;
    }
    public void useWeapon(Monster monster){
        float damage = this.getStrength();
        if (this.weapon != null){
            damage = weaponDamage(this.weapon);
            this.weapon.reduceUse();
        }
        monster.reduceHealth(damage);
        Announce.heroAttackDamage(this.getName(), damage, monster);
        if (monster.is_dead()){
            Announce.killedMonster(this.getName(), monster);
        }
    }


    public <T extends Equipable> void equipItem(T item, T currentItem){
        if (currentItem != null){
            currentItem.setEquip(false);
        }
        item.setEquip(true);
        if (item instanceof Armor){
            this.armor = (Armor)item;
        }else{
            this.weapon = (Weapon) item;
        }
        System.out.println(this.getName() + " has equiped " + ((Item) item).getName());
    }

    public float receiveDamage(int damage){
        if(Dice.dogde(this.getDodge_chance())){
            System.out.println(this.getName() + "was able to succesfully dodge the attack");
            return 0.0f;
        }
        float total_damage;
        if (this.armor != null){
            total_damage = (damage - this.armor.getDamageReduction()) * this.getDefense();
            this.armor.reduceUse();
            if(this.armor.uses_left() == 0){
                this.armor = null;
            }
        }else{
            total_damage = damage - this.getDefense();
        }

        this.reduceHealth(total_damage);
        return total_damage;
    }

    public void respawn() {
	increaseHealth(this.getLevel() * 100);
	this.fainted = false;
	this.currentMana = this.getTotalMana();

        System.out.println(this.getName() + " has been respawned. \n   HP: " + this.getHealth() + "\n   MANA: " + this.getMana());
    }


    // Getter methods
    public boolean isFainted(){
        return this.fainted;
    }

    public float getHealth() {return this.health;}

    public int getLevel() {return this.level;}

    public int getGold(){return this.gold;}

    public float getMana(){return this.currentMana;}

    public float getStrength(){return this.strength;}

    public float getAgility(){return this.agility;}

    public String getName(){return this.name;}

    public float getDexterity(){return this.dexterity;}

    public float getCurrentExperience(){return this.currentExperience;}

    public HeroType getHeroType(){return this.heroType;}

    public Armor getArmor(){return this.armor;}

    public Weapon getWeapon(){return this.weapon;}

    public float getDefense(){return this.defense;}

    public float getDodge_chance(){return this.dodge_chance;}

    public float getTotalMana(){return this.totalMana;}
    public HashMap<ItemType, List<Item>> getInventroy(){
        return this.inventory;
    }
    public int getHeroID(){return this.heroID;}

    public void setHeroID() {
	this.heroID = ++numHeroes;
    }

}
