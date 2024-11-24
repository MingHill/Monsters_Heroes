import java.io.IOException;

public class Interface {

    private GameMap gameMap;
    private GamePlay gamePlay;

    public Interface(GameFactory gameFactory) throws IOException {
	gameFactory.printTitle();
	gameFactory.printRules();
	Party party = gameFactory.createParty();
	this.gameMap = gameFactory.createGameMap(party);
	this.gamePlay = gameFactory.createGamePlay(party, this.gameMap);
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
