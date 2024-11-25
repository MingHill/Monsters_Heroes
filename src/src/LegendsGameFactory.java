
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
       System.out.println("\n" +
               "    __                              __              ____   _    __      __              \n" +
               "   / /   ___  ____ ____  ____  ____/ /____   ____  / __/  | |  / /___ _/ /___  _____    \n" +
               "  / /   / _ \\/ __ `/ _ \\/ __ \\/ __  / ___/  / __ \\/ /_    | | / / __ `/ / __ \\/ ___/    \n" +
               " / /___/  __/ /_/ /  __/ / / / /_/ (__  )  / /_/ / __/    | |/ / /_/ / / /_/ / /        \n" +
               "/_____/\\___/\\__, /\\___/_/ /_/\\__,_/____/   \\____/_/       |___/\\__,_/_/\\____/_/         \n" +
               "           /____/                                                                       \n");

    }

    public void printRules() {

        System.out.println("Legends of Valor is a MOBA (multiplayer online battle arena)-like game. The player will control\n" +
                "a team of 3 heroes who will attempt to fight their way through to the monsters’ Nexus. The\n" +
                "heroes win if any of them reach the monsters’ Nexus. The heroes lose if any monster reaches\n" +
                "the heroes’ Nexus.");
        System.out.println("You will be able to interact with the map during each turn. \nThe spaces are marked as (N) Nexus, (C) common space" +
                "(O) Obstacle, (K) Koulou, (C) Cave, (B) Bush, and (~) Inaccesibile space. \nNexus, Cave, Bush and Koulou boost you heroes ability by a certain percent when in that given spot." +
                "You also have the ability to teleport to a different hero, or recall back to your NEXUS. In the chance you meet a monster, you have the ability to attack using spells or normal attack. " +
                "If you want to purchase any items, that can you done at your NEXUS using the gold that you have\n Have fun and good luck!!");

    }
}
