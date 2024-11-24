import java.util.ArrayList;
import java.io.IOException;

public class LegendsGamePlay implements GamePlay {

    private LegendsGameMap gameMap;
    private Party party;
    private Monsters monsters;

    private int roundNum;


    public LegendsGamePlay(Party party, LegendsGameMap gameMap) throws IOException {
		this.party = party;
		this.monsters = new Monsters();
		this.gameMap = gameMap;
		// --- calling the setMonsters for the gamemap
		this.gameMap.setMonsters(this.monsters);

		// --- passing in gameMap instead of initiating it here
		this.roundNum = 0;
    }


    // checks if c1 and c2 are in range to attack each other
    private boolean inRange(Coordinate c1, Coordinate c2) {
		return (Math.abs(c1.getCol() - c2.getCol()) <= 1)
			&& (Math.abs(c1.getRow() - c2.getRow()) <= 1);
    }


    // checks which heroes are in range for an attack from coordinate c
    private ArrayList<Hero> getHeroesInRange(Coordinate c) {
		ArrayList<Hero> heroes = new ArrayList<Hero>();
		for (Hero hero : this.party.getHeroes().values()) {
			if (inRange(c, hero.getCoordinate())) {
			heroes.add(hero);
			}
		}
		return heroes;
    }


    // iterates over all of the monsters, attacking if there is a hero in range
    // or moving if there isn't
    private void monstersTurn() {
		for (Monster monster : this.monsters.getAll()) {
			ArrayList<Hero> heroes = getHeroesInRange(monster.getCoordinate());

			if (heroes.size() > 0) { // attack
			Hero hero = heroes.get(0);
			int damage = monster.getDamage();
			float damage_taken = hero.receiveDamage(damage);
			Announce.monsterAttackDamage(hero, damage_taken, monster);
			if (hero.isFainted()) {
				Announce.heroFainted(hero, monster);
			}

			} else { // move
			this.gameMap.moveMonster(monster);
			}
		}
    }


    // checks which monsters on the board are in range of an attack from coordinate c
    private ArrayList<Monster> getMonstersInRange(Coordinate c) {
	ArrayList<Monster> monstersInRange = new ArrayList<Monster>();

	for (Monster monster : this.monsters.getAll()) {
	    if (inRange(c, monster.getCoordinate())) {
		monstersInRange.add(monster);
	    }
	}

	return monstersInRange;
    }


    private boolean moveAction(Hero hero) {
	System.out.println("Which direction would you like to move?");
	System.out.println("    1) Left");
	System.out.println("    2) Right");
	System.out.println("    3) Up");
	System.out.println("    4) Down");

	int input = Input.getInt(4);

	return this.gameMap.moveHero(input, hero);
    }


    private void removeDeadMonster(Monster monster) {
	this.monsters.remove(monster);
	this.gameMap.removeDeadMonster(monster);
    }


    private Monster selectTargetMonster(ArrayList<Monster> targets) {
	System.out.println("Which monster would you like to attack?");
	for (int i = 0; i < targets.size(); i++) {
	    System.out.println((i + 1)+ " " + targets.get(i).getMonsterID() + " " + targets.get(i).getName());
	}

	int input = Input.getInt(targets.size());

	return targets.get(input - 1);
    }


    private boolean attackAction(Hero hero) {
	ArrayList<Monster> monstersInRange = getMonstersInRange(hero.getCoordinate());

	if (monstersInRange.size() > 0) {
	    Monster target = selectTargetMonster(monstersInRange);
	    hero.useWeapon(target);

	    if (target.is_dead()) {
		removeDeadMonster(target);
	    }
	    return true;
	} else {
	    System.out.println("No monsters in range! Please choose a different action.");
	    return false;
	}
    }


    private boolean potionAction(Hero hero) {
	Item potion;
	boolean reselect = true;
	boolean failed = false;

	System.out.println("Your available potions are: ");
	if (!hero.displayAvailableItem(ItemType.POT)){
	    System.out.println("No potions available! Please choose a different action.");
	    return false;
	}

	while (reselect) {

	    failed = false;
	    potion = Input.selectItem(hero);

	    if (potion.getItemType() != ItemType.POT) {
		System.out.println("This item is not a potion, please choose again");
		failed = true;
	    }
	    if (potion.uses_left() == 0) {
		System.out.println("This potion is out of uses, please choose again");
	    }
	    if (failed) {
		reselect = Input.reselectMove();
	    } else {
		hero.usePotion((Potion) potion);
		reselect = false;
	    }
	}

	return !failed;
    }


    private boolean equipAction(Hero hero) {
	Item equipment;
	boolean reselect = true;
	boolean failed = false;

	System.out.println("Your available equipments are: ");
	boolean armAvailable = hero.displayAvailableItem(ItemType.ARM);
	boolean wepAvailable = hero.displayAvailableItem(ItemType.WEP);

	if (!armAvailable && !wepAvailable) {
	    System.out.println("No equipment available! Please choose a different action.");
	    return false;
	}

	while (reselect) {
	    failed = false;
	    System.out.println("Please select a piece of armor or weapon you would like to equip");

	    equipment = Input.selectItem(hero);
	    boolean isWep = equipment.getItemType() == ItemType.WEP;
	    boolean isArm = equipment.getItemType() == ItemType.ARM;

	    if (!isWep && !isArm) {
		System.out.println("That item is not equipment, please choose again.");
		failed = true;
	    }

	    if (equipment.uses_left() == 0) {
		System.out.println("This equipment out of uses, please choose again.");
	    }

	    if (isWep) {
		hero.equipItem((Equipable) equipment, hero.getWeapon());
	    } else {
		hero.equipItem((Equipable) equipment, hero.getArmor());
	    }

	    if (failed) {
		reselect = Input.reselectMove();
	    } else {
		reselect = false;
	    }
	}

	return !failed;
    }


    private boolean spellAction(Hero hero) {
	ArrayList<Monster> monstersInRange = getMonstersInRange(hero.getCoordinate());

	if (monstersInRange.size() > 0) {
	    Monster target = selectTargetMonster(monstersInRange);

	    Item spell = null;
	    boolean reselect = true;
	    boolean failed = false;

	    System.out.println("Your available spells are: ");
	    if (!hero.displayAvailableItem(ItemType.SPL)) {
		System.out.println("No spells available! Please choose a different action.");
		return false;
	    }

	    while (reselect) {
		failed = false;

		spell = Input.selectItem(hero);
		if (spell.getItemType() != ItemType.SPL) {
		    System.out.println("This item is not a spell, please choose again.");
		    failed = true;
		} else if(spell.uses_left() == 0) {
		    System.out.println("This spell is out of uses, please choose again.");
		    failed = true;
                }else if(!hero.useSpell((Spell) spell, target)){
		    System.out.println("You do not have enough mana for this spell.");
		    failed = true;
                }

		if (failed) {
		    reselect = Input.reselectMove();
		} else{
		    reselect = false;
		}
	    }

	    if (failed) {
		return false;
	    }

	    if (target.is_dead()) {
		removeDeadMonster(target);
	    }

	    return true;
	} else {
	    System.out.println("No monsters in range! Please choose a different action.");
	    return false;
	}
    }


    private void buySellAction(Hero hero) {
	Space space = this.gameMap.getSpace(hero.getCoordinate().getRow(),
					    hero.getCoordinate().getCol());
	if (space.getSpaceType() == SpaceType.MAR) {
	    Market market = (Market) space;

	    int option = Input.marketOption();
	    while (option != 3) {
		System.out.println(hero.getName() + ", you currently have: \n   Gold: " + hero.getGold() + "\n   Level: " + hero.getLevel());

		int item;

		switch (option) {
		case 1: // sell
		    while (true) {
			System.out.println(hero.displayInventory());
			item = Input.trade();
			hero.sellItem(item, market);
			if (!Input.again("Would you like to sell another item?")) {
			    break;
			}
		    }
		    break;

		case 2: // buy
		    while (true) {
			market.viewMenu();
			item = Input.trade();
			hero.buyItem(item, market);
			if (!Input.again("Would you like to buy another item?")) {
			    break;
			}
			System.out.println(hero.getName() + ", you currently have: \n   Gold: " + hero.getGold() + "\n   Level: " + hero.getLevel());
		    }
		    break;

		case 3:
		    break;

		case 4:
		    System.out.println(hero.displayInventory());
		    break;

		case 5:
		    Item repairItem = Input.selectItem(hero);
		    hero.repair(repairItem);
		    break;
		}
		option = Input.marketOption();
	    }

	} else {
	    System.out.println("You must be in a Nexus space to access the shop! Please choose a different action.");
	}
    }


    private void heroTurn(Hero hero) {
	System.out.println("What action would you like to take?");
	System.out.println("    1) Move");
	System.out.println("    2) Attack");
	System.out.println("    3) Cast a spell");
	System.out.println("    4) Equip an item");
	System.out.println("    5) Use a potion");
	System.out.println("    6) Teleport");
	System.out.println("    7) Recall");
	System.out.println("    8) Buy / Sell items");

	int input = Input.getInt(8);
	boolean tookAction = false;

	switch(input) {
	case 1: // move
	    tookAction = moveAction(hero);
	    break;
	case 2: // attack
	    tookAction = attackAction(hero);
	    break;
	case 3: // cast a spell
	    tookAction = spellAction(hero);
	    break;
	case 4: // equip an item
	    tookAction = equipAction(hero);
	    break;
	case 5: // use a potion
	    tookAction = potionAction(hero);
	    break;
	case 6: // teleport
	    tookAction = this.gameMap.teleportHero(hero);
	    break;
	case 7: // recall
	    tookAction = true;
	    this.gameMap.recallHero(hero);
	    break;
	case 8: // buy / sell items
	    buySellAction(hero);
	    break;
	}

	// recursively called if action was not consumed
	// (by buying / selling an item or by the action failing)
	if (!tookAction) {
	    heroTurn(hero);
	}

    }


    private void heroesTurn() {
	for (Hero hero : this.party.getHeroes().values()) {
	    System.out.println("It is now Hero " + hero.getHeroID() + ", " + hero.getName() + "'s, turn");
	    System.out.println("Stats: \n " + hero);
	    this.heroTurn(hero);
	}
    }


    public boolean makeMove() throws IOException {
	this.roundNum++;

	this.heroesTurn();
	this.monstersTurn();

	if (this.roundNum % 6 == 0) {
	    System.out.println("A new wave of monsters has spawned!");
	    this.gameMap.spawnMonsters(1);
	}

	return false;
    }

}
