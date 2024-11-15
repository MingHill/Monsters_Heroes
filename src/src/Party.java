import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
}
