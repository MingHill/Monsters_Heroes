
 /*
  * LegendsGameFactory.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Concrete GameFactory for the Legends of Valor game. Creates a party of 3 heroes,
  * a Legends Game Map, and initializes the GamePlay object that runs the game.
  *
  * Credits: All code is our own.
  */


import java.io.IOException;

public class LegendsGameFactory extends GameFactory {

    public LegendsGameFactory() {};

    public Party createParty() throws IOException {
	return new Party(3);
    }

    public GameMap createGameMap(Party party) throws IOException {
	return new LegendsGameMap(8, party);
    }

    public GamePlay createGamePlay(Party party, GameMap gameMap) throws IOException {
	return new LegendsGamePlay(party, (LegendsGameMap) gameMap);
    }

    public void printTitle() {
	System.out.println("Need to implement");
    }

    public void printRules() {
	System.out.println("Need to implement");
    }
}
