import java.util.ArrayList;
import java.io.IOException;

public class LegendsGamePlay {

    private LegendsGameMap gameMap;
    private Party party;
    private Monsters monsters;


    public LegendsGamePlay(Party party) throws IOException {
	this.party = party;
	this.monsters = new Monsters();
	this.gameMap = new LegendsGameMap(8, party, this.monsters);

    }

    private boolean inRange(Coordinate c1, Coordinate c2) {
	return (Math.abs(c1.getCol() - c2.getCol()) <= 1)
	    && (Math.abs(c1.getRow() - c2.getRow()) <= 1);
    }


    private ArrayList<Hero> getHeroesInRange(Coordinate c) {
	ArrayList<Hero> heroes = new ArrayList<Hero>();

	for (Hero hero : this.party.getHeroes().values()) {
	    if (inRange(c, hero.getCoordinate())) {
		heroes.add(hero);
	    }
	}

	return heroes;
    }


    public void monstersTurn() {
	for (Monster monster : this.monsters.getAll()) {
	    ArrayList<Hero> heroes = getHeroesInRange(monster.getCoordinate());
	    System.out.println("Monster turn:");

	    if (heroes.size() > 0) { // attack
		System.out.println("Attack");
		Hero hero = heroes.get(0);
		int damage = monster.getDamage();
		float damage_taken = hero.receiveDamage(damage);
		Announce.monsterAttackDamage(hero, damage_taken, monster);
		if (hero.isFainted()) {
		    Announce.heroFainted(hero, monster);
		}

	    } else { // move
		// TODO: need to generalize movement / gamemap to track monsters as well
		System.out.println("Move");
		this.gameMap.moveMonster(monster);
	    }
	}
    }


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

    }


    public void heroesTurn() {
	for (Hero hero : this.party.getHeroes().values()) {
	    this.heroTurn(hero);
	}
    }


    public void round() {
	System.out.println(this.gameMap.toString());
	this.heroesTurn();
	this.monstersTurn();
    }

}
