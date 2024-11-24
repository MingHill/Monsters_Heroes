import java.io.IOException;

public abstract class GameFactory {

    public abstract Party createParty() throws IOException;
    public abstract GameMap createGameMap(Party party) throws IOException;
    public abstract GamePlay createGamePlay(Party party, GameMap gameMap) throws IOException;
    public abstract void printTitle();
    public abstract void printRules();

}
