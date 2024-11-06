import java.io.IOException;

public class GamePlay {
    private Party party;
    private GameMap gameMap;
    public GamePlay(Party party, GameMap gameMap) {
        this.party = party;
        this.gameMap = gameMap;
    }

    public boolean makeMove() throws IOException {
        Space newSpace = null;
        boolean quit = false;
        while (true){
            int direction = Input.makeMove();
            if (direction == -1) {
                quit = true;
                break;
            }
            if(this.gameMap.moveDirection(direction)){
                int pRow = this.party.coordinate.getRow();
                int pCol = this.party.coordinate.getCol();
                newSpace = this.gameMap.getSpace(pRow, pCol);
                break;
            }
        }
        if (quit) {
            System.out.println("Thanks for playing. Hope to see you soon.");
            return false;
        }
        switch(newSpace.getSpaceType()){
            case COM:
                if(Dice.meetMonster()){
                    Battle newBattle = new Battle(this.party);
                    System.out.println("You have encountered some monsters and must battle them!");
                    System.out.println(newBattle.displayMonsters());
                    if(!newBattle.battle()){
                        return false;
                    }
                }
                break;
            case MAR:
                Market market = (Market) newSpace;
                if(Input.enterMarket()){
                    for (Hero hero: this.party.getHeroes().values()){
                        System.out.println(hero.getName());
                        int option = Input.marketOption();
                        while(option != 3){
                            System.out.println(hero.getName() + ", you currently have: \n   Gold: " + hero.getGold() + "\n   Level: " + hero.getLevel());
                            int item;
                            switch (option){
                                case 1: // sell
                                    while(true){
                                        System.out.println(hero.displayInventory());
                                        item = Input.trade();
                                        hero.sellItem(item, market);
                                        if (!Input.again("Would you like to sell another item?")){
                                            break;
                                        }
                                    }
                                    break;
                                case 2: // buy
                                    while(true){
                                        market.viewMenu();
                                        item = Input.trade();
                                        hero.buyItem(item, market);
                                        if (!Input.again("Would you like to buy another item?")){
                                            break;
                                        }
                                        System.out.println(hero.getName() + ", you currently have: \n   Gold: " + hero.getGold() + "\n   Level: " + hero.getLevel());
                                    }
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    System.out.println(hero.displayInventory());
                                    break;
                                case 5:
                                    Item repairItem = Input.selectItem(hero);
                                    hero.repair(repairItem);
                                    break;
                            }
                            option = Input.marketOption();
                        }
                    }
                }
                break;
        }
        return true;
    }
}

