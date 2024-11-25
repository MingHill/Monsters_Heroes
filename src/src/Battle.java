import java.io.IOException;
import java.util.*;

public class Battle {
    private Party party;
    private HashMap<String, Monster> monsters;
    private int heroesRemaining;
    private int monstersRemaining;
    private int goldRewards;
    private int experienceRewards;


    public Battle(Party party) throws IOException {
        this.party = party;
        this.monsters = initiateMonsters();
        this.heroesRemaining = party.getSize();
        this.monstersRemaining = party.getSize();
        this.setRewards();
    }

    private HashMap<String, Monster> initiateMonsters() throws IOException {
        /*
        Code to random choose size number of monster and initialize them and insert them into the
        hashmap to be returned
         */
        List<Monster> monsterList = Fetch.fetchMonsters(this.party.getSize(), this.party.highestLevel());
        HashMap<String, Monster> monsters = new HashMap<>();
        for (Monster monster : monsterList) {
            monsters.put(monster.getName(), monster);
        }
        return monsters;
    }

    public boolean battle(){
        int turn = 1; // 0 = heros turn, 1 = monsters turn
        while(true){
            switch(turn){
                case 1: //Heros turn
                    for(Hero hero : this.party.getHeroes().values()){
                        if (hero.isFainted()){
                            continue;
                        }
                        heroMove(hero);
                        if(this.checkWin()){
                            System.out.println("Congratulations! Your party has slain all the monsters!!");
                            this.getRewards();
                            return true;
                        }
                    }
                    break;
                case -1:
                    for(Monster monster: this.monsters.values()){
                        if (monster.is_dead()){
                            continue;
                        }
                        monsterMove(monster);
                    }
                    for(Hero hero : this.party.getHeroes().values()){
                        if (!hero.isFainted()){
                            hero.roundRegen();
                        }
                    }
                    if (this.checkLoss()){
                        System.out.println("All your heroes are dead!");
                        return false;
                    }
                    break;
            }
            turn *= -1;
        }

    }



    private void getRewards(){
        for(Hero hero : this.party.getHeroes().values()){
            if (hero.isFainted()){
                hero.revive();
            }else{
                hero.getRewards(this.getGoldRewards(), this.getExperienceRewards());
            }
        }
    }

    private void setRewards(){
        int gold = 0;
        int xp = 0;
        for (Monster monster : this.monsters.values()) {
            gold += monster.getGoldReward();
            xp += monster.getExperienceReward();
        }
        this.goldRewards = gold;
        this.experienceRewards = xp;
    }

    private void monsterMove(Monster monster) {
        System.out.println("It is now " + monster.getName() + "'s turn");
        System.out.println("Stats: \n " + monster);
        Hero hero = selectHero();
        int damage = monster.getDamage();
        float damage_taken = hero.receiveDamage(damage);
        Announce.monsterAttackDamage(hero, damage_taken, monster);
        if(hero.isFainted()){
            this.heroesRemaining--;
            Announce.heroFainted(hero, monster);
        }
    }

    private Hero selectHero() {
        List<Hero> is_alive = new ArrayList<>();
        Random rand = new Random();
        int count = 0;
        for (Hero hero : this.party.getHeroes().values()) {
            if (!hero.isFainted()){
                is_alive.add(hero);
                count++;
            }
        }
        int randomIndex = rand.nextInt(is_alive.size());
        return is_alive.get(randomIndex);
    }

    private void heroMove(Hero hero){
        System.out.println("It is now " + hero.getName() + "'s turn");
        System.out.println("Stats: \n " + hero);
        String displayedInventory = hero.displayInventory();
        HashMap<ItemType, List<Item>> heroInventory = hero.getInventroy();

        if (Input.viewHeroInventory()){
            System.out.println(displayedInventory);
        }
        while(true){
            int moveChoice = Input.heroMoveChoice(); // 1: attack 2: Cast spell 3: Use potion 4. Equip Armor or sword
            if (moveChoice == 1){ //attack
                if (hero.getWeapon() == null) {
                    System.out.println(hero.getName() + "does not currently have a weapon equipped, hopefully you're strong enough to slay these monsters with your bare hands");
                }
                Monster selectedMonster = selectMonster();
                hero.useWeapon(selectedMonster);
                if(selectedMonster.is_dead()){
                    this.monsters.remove(selectedMonster.getName());
                    this.monstersRemaining--;
                }
                break;
            }else if (moveChoice == 2){ // chose spell
                Item spell = null;
                Monster selectedMonster = selectMonster();
                boolean reselect = true;
                boolean failed = false;
                System.out.println("Your available spells are: ");
                if (!hero.displayAvailableItem(ItemType.SPL)){
                    failed = true;
                    reselect = false;
                    System.out.println("Please choose another type of move");
                    continue;
                }
                while(reselect){
                    failed  = false;

                    spell = Input.selectItem(hero); // get spell
                    // need to check for null pointer
                    if (spell.getItemType() != ItemType.SPL){
                        System.out.println("This item is not a spell, please choose again");
                        failed = true;
                    }else if(spell.uses_left() == 0){
                        System.out.println("This spell is out of uses, please choose again");
                        failed = true;
                    }else if(!hero.useSpell((Spell)spell, selectedMonster)){
                        System.out.println("You do not have enough mana for this spell.");
                        failed = true;
                    }
                    if (failed){
                        reselect = Input.reselectMove();
                    }else{
                        reselect = false;
                    }
                }
                if(selectedMonster.is_dead()){
                    this.monsters.remove(selectedMonster.getName());
                    this.monstersRemaining--;
                }
                if (!failed){
                    break;
                }
            }else if (moveChoice == 3){ // use a potion
                Item potion;
                boolean reselect = true;
                boolean failed = false;

                System.out.println("Your available potions are: ");
                if (!hero.displayAvailableItem(ItemType.POT)){
                    failed = true;
                    reselect = false;
                    System.out.println("Please choose another type of move");
                    continue;
                }
                while(reselect){
                    failed = false;
                    potion = Input.selectItem(hero);
                    if(potion.getItemType() != ItemType.POT){
                        System.out.println("This item is not a potion, please choose again");
                        failed = true;
                    }
                    if(potion.uses_left() == 0){
                        System.out.println("This potion is out of uses, please choose again");
                        failed = true;
                    }
                    if (failed){
                        reselect = Input.reselectMove();
                    }else{
                        hero.usePotion((Potion)potion);
                        reselect = false;
                    }
                }
                if(!failed){
                    break;
                }
            }else{
                Item equipment;
                boolean reselect = true;
                boolean failed = false;
                System.out.println("Your available equipments to equip are: ");

                boolean armAvailable = hero.displayAvailableItem(ItemType.ARM);
                boolean wepAvailable = hero.displayAvailableItem(ItemType.WEP);

                if (!armAvailable && !wepAvailable) {
                    failed = true;
                    reselect = false;
                    System.out.println("Please choose another type of move");
                }

                while(reselect){
                    failed = false;
                    System.out.println("Please select a piece of armor or weapon you would like to equip");
                    equipment = Input.selectItem(hero);
                    boolean isWep = equipment.getItemType() != ItemType.WEP;
                    boolean isArm = equipment.getItemType() != ItemType.ARM;
                    if (!isWep && !isArm){
                        System.out.println("This is not an item you can equip, please choose again");
                        failed = true;
                    }
                    if (failed || equipment.uses_left() == 0){
                        System.out.println("This equipment out of uses, please choose again");
                        failed = true;
                    }
                    if (equipment.getItemType() == ItemType.WEP){
                        hero.equipItem((Equipable) equipment, hero.getWeapon());
                    }else{
                        hero.equipItem((Equipable) equipment, hero.getArmor());
                    }
                    if (failed){
                        reselect = Input.reselectMove();
                    }else{
                        reselect = false;
                    }
                }
                if(!failed){
                    break;
                }
            }
        }
    }

    private Monster selectMonster(){
        String monsterDisplay = displayMonsters();
        while(true) {
            int monsterSelected = Input.monsterSelection(monsterDisplay, this.party.getSize());
            int j = 1;
            for (Monster monster : this.monsters.values()) {
                if (j == monsterSelected) {
                    if (monster.is_dead()) {
                        System.out.println("This monster is already dead, please choose another monster");
                        continue;
                    }
                    return monster;
                }
                j++;
            }
        }
    }

    private int getGoldRewards(){
        return this.goldRewards;
    }

    private int getExperienceRewards(){
        return this.experienceRewards;
    }

    private int getHeroesRemaining() {
        return this.heroesRemaining;
    }
    private int getMonstersRemaining() {
        return this.monstersRemaining;
    }

    private boolean checkWin(){
        return this.monstersRemaining == 0;
    }

    private boolean checkLoss(){
        return this.heroesRemaining == 0;
    }
    private void reduceHeroes(String hero){
        this.heroesRemaining--;
    }
    private void reduceMonsters(){
        this.monstersRemaining--;
    }

    public String displayMonsters(){
        String output = "";
        int i = 1;
        for(Monster monster : this.monsters.values()){
            output += i + ". " + monster.toString() + "\n";
            i++;
        }
        return output;
    }






}
