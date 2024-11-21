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
    public void monstersTurn() {
	for (Monster monster : this.monsters.getAll()) {
	    ArrayList<Hero> heroes = getHeroesInRange(monster.getCoordinate());
	    System.out.println("Monster turn:");

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


    public void heroTurn(Hero hero) {
	System.out.println("It is now " + hero.getName() + "'s turn");
	System.out.println("Stats: \n " + hero);

    }


    public void heroesTurn() {
	for (Hero hero : this.party.getHeroes().values()) {
	    this.heroTurn(hero);
	}
    }


    public void round() {
	this.roundNum++;

	System.out.println(this.gameMap.toString());
	this.heroesTurn();
	this.monstersTurn();

	if (this.roundNum % 6 == 0) {
	    System.out.println("A new wave of monsters has spawned!");
	    this.gameMap.spawnMonsters();
	}
    }

}
