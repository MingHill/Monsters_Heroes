
 /*
  * Monsters.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


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
