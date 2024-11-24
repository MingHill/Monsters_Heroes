import java.io.IOException;

public class MonsterGameFactory extends GameFactory {

    public MonsterGameFactory() {};

    public Party createParty() throws IOException {
	int numHeroes = Input.getNumHeroes();
	return new Party(numHeroes);
    }

    public GameMap createGameMap(Party party) throws IOException {
	int mapSize = Input.getBoardSize();
	return new MonsterGameMap(mapSize, party);
    }

    public GamePlay createGamePlay(Party party, GameMap gameMap) throws IOException{
	return new MonsterGamePlay(party, (MonsterGameMap) gameMap);
    }

    public void printTitle() {
	System.out.println("█▀▄▀█ ████▄    ▄      ▄▄▄▄▄      ▄▄▄▄▀ ▄███▄   █▄▄▄▄   ▄▄▄▄▄       ██      ▄   ██▄        ▄  █ ▄███▄   █▄▄▄▄ ████▄ ▄███▄     ▄▄▄▄▄");
        System.out.println("█ █ █ █   █     █    █     ▀▄ ▀▀▀ █    █▀   ▀  █  ▄▀  █     ▀▄     █ █      █  █  █      █   █ █▀   ▀  █  ▄▀ █   █ █▀   ▀   █     ▀▄ ");
        System.out.println("█ ▄ █ █   █ ██   █ ▄  ▀▀▀▀▄       █    ██▄▄    █▀▀▌ ▄  ▀▀▀▀▄       █▄▄█ ██   █ █   █     ██▀▀█ ██▄▄    █▀▀▌  █   █ ██▄▄   ▄  ▀▀▀▀▄   ");
        System.out.println("█   █ ▀████ █ █  █  ▀▄▄▄▄▀       █     █▄   ▄▀ █  █  ▀▄▄▄▄▀        █  █ █ █  █ █  █      █   █ █▄   ▄▀ █  █  ▀████ █▄   ▄▀ ▀▄▄▄▄▀    ");
        System.out.println("   █        █  █ █              ▀      ▀███▀     █                    █ █  █ █ ███▀         █  ▀███▀     █         ▀███▀             ");
        System.out.println("  ▀         █   ██                              ▀                    █  █   ██             ▀            ▀                            ");
        System.out.println("                                                                    ▀");
    }

    public void printRules() {
	System.out.println("The rules of the game are fairly simple.");
        System.out.println("The monsters and heroes live in a fictional world. They do not get along and therefore fight each other.\n" +
                "Every time the heroes win, they gain experience and money. Heroes use the money to buy a variety of\n" +
                "items to aid them in their battles with the monsters. When they accumulate enough experience, they\n" +
                "level up, which improves their skills. The goal of the game is for the heroes to defeat monsters and level\n" +
                "up indefinitely.");
        System.out.println("You will be able to interact with map and move around the world. Market spots are denoted as 'M', blocked spaces are denoted with '~', and common spaces are denoted with ' '. ");
        System.out.println("When moving on a common space, you may encounter monsters in which you must battle against.");
        System.out.println("During each move you have the option to: ");
        System.out.println("• Attack, using the hero’s equipped weapon\n" +
                "• Cast a spell from the hero’s inventory\n" +
                "• Use a potion from the hero’s inventory\n" +
                "• Equip a weapon or piece of armor");
    }
}
