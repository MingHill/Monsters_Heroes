
 /*
  * Interface.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * The Interface class represents the game currently being played. It is
  * created using a Singleton design pattern to restrict usage to a single
  * game being played at any point in time. A GameFactory is utilized to make
  * sure that the generated Party, GameMap, and GamePlay objects are compatible
  * with one another.
  *
  * Credits: All code is our own.
  */


import java.io.IOException;

public class Interface {

    private GameMap gameMap;
    private GamePlay gamePlay;
    private static Interface instance;

    private Interface(GameFactory gameFactory) throws IOException {
	gameFactory.printTitle();
	gameFactory.printRules();
	Party party = gameFactory.createParty();
	this.gameMap = gameFactory.createGameMap(party);
	this.gamePlay = gameFactory.createGamePlay(party, this.gameMap);
    }

    public static Interface getInstance() {
	return instance;
    }

    public static Interface getInstance(GameFactory gameFactory) throws IOException {
	if (instance == null) {
	    instance = new Interface(gameFactory);
	}

	return instance;
    }

    public void runGame() throws IOException {
        while(true){
            System.out.println(gameMap);
            if(!this.gamePlay.makeMove()){
                System.out.println("GAME OVER");
                return;
            }
        }
    }
}
