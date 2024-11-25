
 /*
  * Party.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

/*
Class that represents the state of a party of heroes
 */
public class Party implements Space{
    private int size;
    private HashMap<String, Hero> heroes = new HashMap<>();
    private Coordinate coordinate;
    private boolean is_accessible;
    private SpaceType spaceType;

    public Party (int size) throws IOException {
        this.size = size;
        this.heroes = initializeHeroes();
        this.coordinate = new Coordinate(0,0);
        this.is_accessible = false;
        this.spaceType = SpaceType.PAR;
    }

    private HashMap<String, Hero> initializeHeroes() throws IOException {
        HashMap<String, Hero> heroes = new HashMap<>();
        List<Hero> heroesList = Fetch.fetchHeroes();
        for (int i = 0;i < this.getSize(); i++){
            int index = Input.selectHero();
            Hero hero = heroesList.get(index - 1);
	    hero.setHeroID();
            heroes.put(hero.getName(), hero);
            System.out.println("You have selected " + hero.getName());
        }
        return heroes;
    }

    public int getSize() {
        return this.size;
    }

    public String toString() {
        return " P ";
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public boolean heroExist(String name){
        return this.heroes.containsKey(name);
    }

    public HashMap<String, Hero> getHeroes(){
        return this.heroes;
    }

    public List<Hero> getOrderedHeroes() {
	List<Hero> heroesList = new ArrayList<Hero>(this.getHeroes().values());
	Collections.sort(heroesList, (o1, o2) -> Integer.compare(o1.getHeroID(), o2.getHeroID()));
	return heroesList;
    }

    public Hero selectHero(String name){
        if (heroExist(name)){
            return this.heroes.get(name);
        }else{
            System.out.println("Hero does not exist");
            return null;
        }
    }

    // Displays all heroes and their stats
    public String displayParty(){
        String output = "";
        for (Hero hero : this.heroes.values()){
            output += hero.toString() + "\n";
        }
        return output;
    }

    public boolean is_accessible(){
        return this.is_accessible;
    }

    public SpaceType getSpaceType(){
        return this.spaceType;
    }

    public int highestLevel(){
        int highestLevel = 0;
        for(HashMap.Entry<String, Hero> hero : this.getHeroes().entrySet() ){
            highestLevel = Math.max(highestLevel, hero.getValue().getLevel());
        }
        return highestLevel;
    }
}
