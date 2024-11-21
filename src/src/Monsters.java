import java.util.ArrayList;

public class Monsters {

    ArrayList<Monster> monsters;

    public Monsters() {
	this.monsters = new ArrayList<Monster>();
    }

    public void add(Monster monster) {
	this.monsters.add(monster);
    }

    public Monster get(int i) {
	return this.monsters.get(i);
    }

    public ArrayList<Monster> getAll() {
	return this.monsters;
    }

    public void remove(Monster monster) {
	this.monsters.remove(monster);
    }

}
