import java.io.IOException;

public class Interface {
    private GameMap gameMap;
    private Party party;
    private int gameID;
    private int mapSize;


    public Interface(int mapSize, int numPlayers, int gameID) throws IOException {
        this.party = new Party(numPlayers);
        this.gameID = gameID;
        this.mapSize = mapSize;
        this.gameMap = selectGameMap();
    }

    public void startGame() throws IOException {
        GamePlay currentGame = null;
        switch(this.gameID){
            case 1:
                // start a monsters gameplay
                currentGame = new MonsterGamePlay(this.party, (MonsterGameMap) this.gameMap);
                break;
            case 2:
                currentGame = new LegendsGamePlay(this.party, (LegendsGameMap) this.gameMap);
                break;
        }
        while(true){
            System.out.println(gameMap);
            if(!currentGame.makeMove()){
                System.out.println("GAME OVER");
                return;
            }
        }
    }

    private GameMap selectGameMap() throws IOException {
        if (this.gameID == 1){
            // initialize a Monsters Gamemap
            return new MonsterGameMap(this.mapSize, this.party);
        }else{
            return new LegendsGameMap(8, this.party);
        }
    }
}
