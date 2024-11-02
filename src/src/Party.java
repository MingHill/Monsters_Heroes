/*
Class that represents the state of a party of heroes
 */
public class Party {
    private int size;
    private Hero[] heroes;

    public Party (int size, Hero[] heroes) {
        this.size = size;
        this.heroes = heroes;
    }

    public int getSize() {
        return size;
    }

}
