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
