import java.util.HashMap;
import java.util.Map;

public class Battle {
    private Party party;
    private HashMap<String, Monster> monsters;
    private int heroesRemaining;
    private int monstersRemaining;


    public Battle(Party party) {
        this.party = party;
        this.monsters = initiateMonsters();
        this.heroesRemaining = party.getSize();
        this.monstersRemaining = party.getSize();
    }

    private HashMap<String, Monster> initiateMonsters() {

        /*
        Code to random choose size number of monster and initialize them and insert them into the
        hashmap to be returned
         */

        return null;
    }

    public void reduceHeroes(){
        this.heroesRemaining--;
    }

    public void reduceMonsters(){
        this.monstersRemaining--;
    }

    public int getHeroesRemaining() {
        return this.heroesRemaining;
    }

    public int getMonstersRemaining() {
        return this.monstersRemaining;
    }

    public boolean checkWin(){
        return this.monstersRemaining == 0;
    }

    private int highestLevel(){
        int highestLevel = 0;
        for(HashMap.Entry<String, Hero> hero : this.party.getHeroes().entrySet() ){
            highestLevel = Math.max(highestLevel, hero.getValue().getLevel());
        }
        return highestLevel;
    }

    private boolean checkLoss(){
        return this.heroesRemaining == 0;
    }






}
