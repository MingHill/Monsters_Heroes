
 /*
  * GameFactory.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * This is an abstract GameFactory class that utilizes the Abstract Factory design pattern to
  * create Party, GameMap, and GamePlay objects that are compatible with one another.
  * It defines several methods that must be implemented to set up either type of game, including:
  * initializing a party, creating a game map, and creating a gameplay object
  * Also includes a pair of helper functions that display the title of the game and the rules of
  * the game.
  *
  * Credits: All code is our own.
  */


import java.io.IOException;

public abstract class GameFactory {

    public abstract Party createParty() throws IOException;
    public abstract GameMap createGameMap(Party party) throws IOException;
    public abstract GamePlay createGamePlay(Party party, GameMap gameMap) throws IOException;
    public abstract void printTitle();
    public abstract void printRules();

}
