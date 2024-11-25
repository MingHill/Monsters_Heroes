import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

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


    // prompts the user to select an item of the given itemTypes from the hero's inventory
    // only items with remaining uses are available to select
    public static Item selectItemOfTypes(Hero hero, List<ItemType> itemTypes) {
	List<Item> itemList = new ArrayList<Item>();

	// add all items of a type in itemTypes
	for (ItemType itemType : itemTypes) {
	    if (hero.getInventroy().get(itemType) != null) {
		itemList.addAll(hero.getInventroy().get(itemType));
	    }
	}

	// remove items with no remaining uses
	List<Item> toRemove = new ArrayList<Item>();
	for (Item item : itemList) {
	    if (item.uses_left() == 0) {
		toRemove.add(item);
	    }
	}
	itemList.removeAll(toRemove);

	// if there are no items with remaining uses in inventory, return null
        if (itemList.isEmpty()){
            System.out.println("No available items.");
            return null;
        }

	// prompt the user to select an item
	System.out.println("Your available items are: ");
	for (int i = 0; i < itemList.size(); i++){
            System.out.println((i + 1) + ": " + itemList.get(i).toString());
        }

	System.out.println("Choose an item to use.");
	int choice = Input.getInt(itemList.size()) - 1;

	return itemList.get(choice);
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

    // validation done
    public static Hero getTeleport(Hero hero, Party party) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int index = 1;
            // Display available heroes
            System.out.println("Available heroes to teleport to:");
            for (Hero tempHero : party.getHeroes().values()) {
                System.out.println(index + ". " + tempHero.getName());
                index++;
            }
            System.out.println("Which hero would you like to teleport to? Enter the number:");

            // Validate user input
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume invalid input
                continue;
            }
            int selected = scanner.nextInt();
            // Check if the selected index is within range
            if (selected < 1 || selected > party.getHeroes().size()) {
                System.out.println("Invalid selection. Please try again.");
                continue;
            }
            // Retrieve the selected hero
            index = 1;
            for (Hero tempHero : party.getHeroes().values()) {
                if (index == selected) {
                    // Check if the selected hero is the current hero
                    if (tempHero.getHeroID() == hero.getHeroID()) {
                        System.out.println("You have selected your own hero. Please select another hero.");
                        break;
                    }
                    return tempHero; // Return the selected hero
                }
                index++;
            }
        }
    }

    // validation done
    public static Coordinate chooseTele(Coordinate coord, Coordinate sidecoord) {
        Scanner scanner = new Scanner(System.in);
        int selected = 0;

        while (true) {
            System.out.println("Where would you like to teleport to: \n 1. Next to hero \n 2. Behind hero");
            if (scanner.hasNextInt()) {
                selected = scanner.nextInt();
                if (selected == 1 || selected == 2) {
                    break;
                }
            } else {
                scanner.next();
            }
            System.out.println("Please enter a valid option (1 or 2).");
        }
        if (selected == 1) {
            return sidecoord;
        } else {
            int row = coord.getRow();
            int col = coord.getCol();
            return new Coordinate(row, col - 1);
        }
    }

    // validation done
    public static int chooseGame() {
        Scanner input = new Scanner(System.in);
        String game;
        System.out.println("Which game would you like to play: \n   1. Monsters and Heroes \n   2. Legends of Valor \n");
        while (true) {
            game = input.nextLine();
            if (game.equals("1") || game.equals("2")) {
                break;
            }
            System.out.println("Please enter a valid input (1 or 2):");
        }
        return Integer.parseInt(game);
    }

}
