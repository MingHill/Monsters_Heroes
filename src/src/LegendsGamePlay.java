import java.util.ArrayList;
import java.io.IOException;

public class LegendsGamePlay {

    private LegendsGameMap gameMap;
    private Party party;
    private Monsters monsters;

    private int roundNum;


    public LegendsGamePlay(Party party) throws IOException {
	this.party = party;
	this.monsters = new Monsters();
	this.gameMap = new LegendsGameMap(8, party, this.monsters);

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


    private void removeDeadMonster(Monster monster) {
	this.monsters.remove(monster);
	// TODO: remove monster from gameMap space
    }


    private Monster selectTargetMonster(ArrayList<Monster> targets) {
	System.out.println("Which monster would you like to attack?");
	for (int i = 0; i < targets.size(); i++) {
	    System.out.println((i + 1)+ " " + targets.get(i).getName());
	}

	int input = Input.getInt(targets.size());

	return targets.get(input - 1);
    }


    private boolean attackAction(Hero hero) {
	ArrayList<Monster> monstersInRange = getMonstersInRange(hero.getCoordinate());

	if (monstersInRange.size() > 0) {
	    // Monster target = monstersInRange.get(0);
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


    private boolean spellAction(Hero hero) {
	ArrayList<Monster> monstersInRange = getMonstersInRange(hero.getCoordinate());

	if (monstersInRange.size() > 0) {
	    // Monster target = monstersInRange.get(0);
	    Monster target = selectTargetMonster(monstersInRange);

	    Item spell = null;
	    boolean reselect = true;
	    boolean failed = false;

	    System.out.println("Your available spells are: ");
	    if (!hero.displayAvailableItem(ItemType.SPL)) {
		failed = true;
		reselect = false;
		System.out.println("No spells available! Please choose a different action.");
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
	    tookAction = true;
	    break;
	case 2: // attack
	    tookAction = attackAction(hero);
	    break;
	case 3: // cast a spell
	    tookAction = spellAction(hero);
	    break;
	case 4: // equip an item
	    tookAction = true;
	    break;
	case 5: // use a potion
	    tookAction = true;
	    break;
	case 6: // teleport
	    tookAction = true;
	    break;
	case 7: // recall
	    tookAction = true;
	    break;
	case 8: // buy / sell items
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
	    System.out.println("It is now " + hero.getName() + "'s turn");
	    System.out.println("Stats: \n " + hero);
	    this.heroTurn(hero);
	}
    }


    public boolean round() throws IOException {
	this.roundNum++;

	System.out.println(this.gameMap.toString());
	this.heroesTurn();
	this.monstersTurn();

	if (this.roundNum % 6 == 0) {
	    System.out.println("A new wave of monsters has spawned!");
	    this.gameMap.spawnMonsters(1);
	}

	return false;
    }

}