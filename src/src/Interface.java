import java.io.IOException;

public class Interface {
    MonsterGameMap gameMap;
    Party party;

    public Interface(int mapSize, int numPlayers) throws IOException {
        this.party = new Party(numPlayers);
        this.gameMap = new MonsterGameMap(mapSize, this.party);
    }

    public void startGame() throws IOException {
        MonsterGamePlay gameplay = new MonsterGamePlay(this.party, this.gameMap);
        while(true){
            System.out.println(gameMap);
            if(!gameplay.makeMove()){
                System.out.println("GAME OVER");
                return;
            }
        }
    }







}
