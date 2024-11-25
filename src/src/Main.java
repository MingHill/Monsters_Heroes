import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Ming and Tanner's Island");
        int gameID = Input.chooseGame();

        GameFactory gameFactory;

        switch (gameID) {
            case 1:
                gameFactory = new MonsterGameFactory();
                break;

            default:
		        gameFactory = new LegendsGameFactory();
		        break;
        }

	Interface game = new Interface(gameFactory);
	game.runGame();
    }
}
