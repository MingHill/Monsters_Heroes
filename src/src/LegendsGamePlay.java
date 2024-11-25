
 /*
  * LegendsGamePlay.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * This class works in tandem with the LegendsGameMap class to implement Legends of Valor.
  * The main gameplay loop is created here in makeMove(), which corresponds to a single round
  * of play.
  *
  * Credits: All code is our own.
  */


import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;

public class LegendsGamePlay implements GamePlay {

    private LegendsGameMap gameMap;
    private Party party;
    private Monsters monsters;

    private int roundNum;


    public LegendsGamePlay(Party party, LegendsGameMap gameMap) throws IOException {
		this.party = party;
		this.monsters = new Monsters();
		this.gameMap = gameMap;
		this.gameMap.setMonsters(this.monsters);
		this.roundNum = 0;
    }


    // checks if coordinates c1 and c2 are in range to attack each other
    private boolean inRange(Coordinate c1, Coordinate c2) {
		return (Math.abs(c1.getCol() - c2.getCol()) <= 1)
			&& (Math.abs(c1.getRow() - c2.getRow()) <= 1);
    }


    // checks which heroes are in range for an attack from coordinate c
    private ArrayList<Hero> getHeroesInRange(Coordinate c) {
		ArrayList<Hero> heroes = new ArrayList<Hero>();
		for (Hero hero : this.party.getOrderedHeroes()) {
			if (inRange(c, hero.getCoordinate())) {
			heroes.add(hero);
			}
		}
		return heroes;
    }


    // iterates over all of the monsters, attacking if there is a hero in range
    // or moving if there isn't
    private void monstersTurn() {
	System.out.println("The monsters advance!");
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
	return this.gameMap.moveHero(Input.makeMove(), hero);
    }

    // removes a dead monster from the game and clears its space on the map
    private void removeDeadMonster(Monster monster) {
	this.monsters.remove(monster);
	this.gameMap.removeDeadMonster(monster);
    }

    // select a monster to attack from the list of monsters in range
    private Monster selectTargetMonster(ArrayList<Monster> targets) {
	System.out.println("Which monster would you like to attack?");
	for (int i = 0; i < targets.size(); i++) {
	    System.out.println((i + 1)+ " " + targets.get(i).getMonsterID() + " " + targets.get(i).getName());
	}

	int input = Input.getInt(targets.size());

	return targets.get(input - 1);
    }


    // hero performs the attack action
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


    // hero takes the drink a potion action
    private boolean potionAction(Hero hero) {

	Potion potion = (Potion) Input.selectItemOfTypes(hero, Arrays.asList(ItemType.POT));

	if (potion == null) {
	    return false;
	}

	hero.usePotion(potion);
	return true;
    }


    // hero takes the equip item action
    private boolean equipAction(Hero hero) {
	Item equipment = Input.selectItemOfTypes(hero, Arrays.asList(ItemType.ARM, ItemType.WEP));

	if (equipment == null) {
	    return false;
	}

	if (equipment.getItemType() == ItemType.WEP) {
	    hero.equipItem((Equipable) equipment, hero.getWeapon());
	} else {
	    hero.equipItem((Equipable) equipment, hero.getArmor());
	}

	return true;
    }


    // hero takes the cast a spell action
    private boolean spellAction(Hero hero) {
	ArrayList<Monster> monstersInRange = getMonstersInRange(hero.getCoordinate());

	if (monstersInRange.size() > 0) {

	    Spell spell = (Spell) Input.selectItemOfTypes(hero, Arrays.asList(ItemType.SPL));

	    if (spell == null) {
		return false;
	    }

	    if (!hero.hasEnoughMana(spell)) {
		System.out.println("You do not have enough mana for that spell!");
	    }

	    Monster target = selectTargetMonster(monstersInRange);

	    // this checks that hero has enough mana / levels for the spell; if not, the action isn't consumed
	    if (!hero.useSpell(spell, target)) {
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


    // only available on Nexus spaces, hero can buy/sell/repair items without consuming an action
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


    // prompts the user to select which action they would like to take
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

	boolean tookAction = false;
	int input = Input.getInt(8);

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

	// allow additional actions if the action did not consume the hero's turn
	// (by buying / selling an item or by the action failing)
	if (!tookAction) {
	    heroTurn(hero);
	}
	System.out.println(this.gameMap);

    }


    // iterates over the heroes, prompting the user to select an action for each
    private void heroesTurn() {
	for (Hero hero : this.party.getOrderedHeroes()) {
	    System.out.println("It is now Hero " + hero.getHeroID() + ", " + hero.getName() + "'s, turn");
	    System.out.println("Stats: \n " + hero);
	    this.heroTurn(hero);
	}
    }


    // checks if the heroes have won the game by reaching the monsters' nexus
    private boolean checkWin() {
	for (Hero hero : this.party.getOrderedHeroes()) {
	    if (hero.getCoordinate().getRow() == (this.gameMap.getSize() - 1)) {
		return true;
	    }
	}

	return false;
    }


    // checks if the heroes have lost the game by having a monster reach their nexus
    private boolean checkLose() {
	for (Monster monster : this.monsters.getAll()) {
	    if (monster.getCoordinate().getRow() == 0) {
		return true;
	    }
	}

	return false;
    }


    // performs end of round regeneration: dead heroes are revived, living heroes
    // have health and mana restored.
    private void roundRegenAndRevive() {
	for (Hero hero : this.party.getOrderedHeroes()) {
	    if (hero.isFainted()) {
		this.gameMap.recallHero(hero);
		hero.respawn();
	    } else {
		hero.roundRegen();
	    }
	}
    }


    // represents a single round of the game.
    public boolean makeMove() throws IOException {
	this.roundNum++;

	this.heroesTurn();
	if (this.checkWin()) {
	    System.out.println("You reached the monsters' nexus! Congratulations, you win!");
	    return false;
	}

	this.monstersTurn();
	if (this.checkLose()) {
	    System.out.println("A monster has reached you nexus! You lose!");
	    return false;
	}

	this.roundRegenAndRevive();

	if (this.roundNum % 6 == 0) {
	    System.out.println("A new wave of monsters has spawned!");
	    this.gameMap.spawnMonsters(party.highestLevel());
	}

	return true;
    }

}
