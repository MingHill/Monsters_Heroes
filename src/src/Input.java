import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Input {

    // need to validate check
    public static Item selectItem(Hero hero){
        while(true){
            System.out.println("Please select which item you would like.");
            System.out.println(hero.displayInventory());
            Scanner scanner = new Scanner(System.in);
            int item = scanner.nextInt();
            int i = 1;
            for (HashMap.Entry<ItemType, List<Item>> entry: hero.getInventroy().entrySet()){
                List<Item> value = entry.getValue();
                for (Item  temp: value){
                    if (i == item){
                        return temp;
                    }
                    i++;
                }
            }
            System.out.println("Item does not exist, please try again");
        }
    }

    // need to validate checking -
    public static int selectHero() {
        Scanner scanner = new Scanner(System.in);
        int heroNumber;

        while (true) {
            System.out.println("To select the hero, please enter a number between 1 and 18:");
            if (scanner.hasNextInt()) {
                heroNumber = scanner.nextInt();
                if (heroNumber >= 1 && heroNumber <= 18) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 18.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }

        return heroNumber;
    }
    // Need to validate checking -
    public static boolean viewHeroInventory() {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println("Would you like to view the current hero's inventory:\n  1. Yes  ||  Any other value: No");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return input == 1;
    }

    // Need to validate checking -
    public static int heroMoveChoice() {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println("Which move would you like to make:\n    1. Attack a monster\n    2. Cast a spell\n    3. Use a potion\n    4. Equip armor or weapon\n Please input your choice (1 - 4):");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 1 && input <= 4) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return input;
    }


    public static int getInt(int nChoices) {
	Scanner scanner = new Scanner(System.in);
	int input;
	while (true) {
	    System.out.println("Enter a number between 1 and " + nChoices + ".");
	    if (scanner.hasNextInt()) {
		input = scanner.nextInt();
		if (input >= 1 && input <= nChoices) {
		    break;
		} else {
		    System.out.println("Invalid choice. Please enter a number between 1 and "+ nChoices + ".");
		}
	    } else {
		System.out.println("Invalid input. Please enter an integer.");
		scanner.next();
	    }
	}
	return input;
    }


    // Need to validate checking -
    public static int monsterSelection(String monsterDisplay, int partyLength) {
        System.out.println(monsterDisplay);
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println("Which monster would you like to attack? Please select a monster that is still alive (1 - " + partyLength + "):");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 1 && input <= partyLength) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + partyLength + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return input;
    }

    // -
    public static boolean reselectMove() {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println("Would you like to choose a different item or use a different move?");
            System.out.println("Input:\n 1 for new item \n Any other integer: Different move");

            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                break;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return input == 1;
    }

    // -
    public static int getNumHeroes() {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println("How many heroes would you like to use (1 - 3):");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 1 && input <= 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return input;
    }

    // -
    public static int getBoardSize() {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println("How big would you like the game map to be (6 - 12):");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 6 && input <= 12) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 6 and 12.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return input;
    }

    // -
    public static int makeMove() {
        Scanner scanner = new Scanner(System.in);
        int direction;
        while (true) {
            System.out.println("Make a move: \n W (up) \n S (down) \n A (left) \n D (right) \n Q (quit)");
            String input = scanner.nextLine().toUpperCase();
            switch (input) {
                case "W":
                    direction = 2;
                    return direction;
                case "A":
                    direction = 0;
                    return direction;
                case "S":
                    direction = 3;
                    return direction;
                case "D":
                    direction = 1;
                    return direction;
                case "Q":
                    return -1;
                default:
                    System.out.println("Invalid input. Please enter W, A, S, D, or Q.");
            }
        }
    }

    // -
    public static boolean enterMarket() {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println("You have entered a market, would you like to purchase any items?");
            System.out.println("Input 1 to view the menu or 2 to leave the market:");

            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input == 1 || input == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 to view the menu or 2 to leave the market.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return input == 1;
    }

    // -
    public static int trade(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("To select the item, please enter the number displayed next to the item or input any other integer to exit out");
        return scanner.nextInt();
    }

    // -
    public static int marketOption() {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println("Would you like to:\n 1. Sell \n 2. Buy \n 3. Leave \n 4. View Inventory \n 5. Repair Item \n Please enter a number between 1 and 5.");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= 1 && input <= 5) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return input;
    }

    // -
    public static boolean again(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println(prompt);
            System.out.println(" 1. Yes \n 2. No");
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input == 1 || input == 2) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter 1 for Yes or 2 for No.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next();
            }
        }
        return input == 1;
    }

    public static Hero getTeleport(Hero hero, Party party) {
        // returns the hero that is selected
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int index = 1;
            // display heroes
            for (Hero temp_hero : party.getHeroes().values()) {
                System.out.println(index + ". " + temp_hero.getName());
                index++;
            }
            System.out.println("Which hero would you like to teleport to? Please enter the integer: ");
            int selected = scanner.nextInt();
            // check if its their own hero
            if (selected == hero.getHeroID()) {
                System.out.println("You have selected your own hero. Please select again.");
                continue;
            }
            index = 1;
            // find selected one
            for (Hero temp_hero : party.getHeroes().values()) {
                if (selected == index) {
                    return temp_hero;
                }
                index++;
            }
            // Index out of range
            System.out.println("Invalid selection. Please try again.");
        }
    }

    public static Coordinate chooseTele(Coordinate coord, Coordinate sidecoord){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Where would you like to teleport to: \n 1. Next to hero \n 2. Behind hero");
        int selected = scanner.nextInt();
        if (selected == 1) {
            return sidecoord;
        }else{
            int row = coord.getRow();
            int col = coord.getCol();
            return new Coordinate(row,col - 1);
        }
    }

}
