import java.util.HashMap;

/*
Class that represents the state of a party of heroes
 */
public class Party implements Space{
    private int size;
    private HashMap<String, Hero> heroes = new HashMap<>();
    Coordinate coordinate;

    public Party (int size, HashMap<String, Hero> heroes) {
        this.size = size;
        this.heroes = heroes;
        this.coordinate = new Coordinate(0,0);
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

    public void makeMove(int direction){ // need to fix based on movement options
        int row = this.coordinate.getRow();
        int col = this.coordinate.getCol();

        int newRow = 0;
        int newCol = 0;

        switch (direction){
            case 0: // left (A)
                newRow = row;
                newCol = col - 1;
                break;
            case 1: // right(D)
                newRow = row;
                newCol = col + 1;
                break;
            case 2: // up (W)
                newRow = row - 1;
                newCol = col;
                break;
            case 3: // down (S)
                newRow = row + 1;
                newCol = col;
                break;
        }
        this.coordinate.setCoordinate(newRow, newCol);
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

}
