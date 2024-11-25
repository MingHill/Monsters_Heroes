
 /*
  * GameFactory.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
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
