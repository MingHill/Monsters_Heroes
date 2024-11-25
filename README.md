CS611 - Assignment 5

Legends of Valor

Names: Soong Ming Hill, Tanner Bangerter

Email: minghill@bu.edu, tanner@bu.edu

Student ID: U50983276, U96993176

Files
-
This section should be all of the source code files that have a .java extension. You should also include a brief description of what the class does.

Classes:
- AllHeroCreator
  - This is a concrete HeroCreator that creates all heroes present in the text data files.
- Announce
  - A class to make announcements such as when a hero is killed or when monster is killed
- Armor
  - Implements Item and Equipable. Reduces damage taken from a monster if equiped. Has a maximum use of 3 uses before needing to be repaired
- Battle
  - Battle class where heroes and monsters compete in battle. Main method is a switch based method where all heroes can choose which
  attack to use on which hero and monsters attack randomly.
- Bush
  -
- Cave
  -
- CommonSpace
  - Inherits the space class. Where a party can move to. Upon moving to a space tile, there is a 50% chance of meeting monsters
- Coordinate
  - Coordinate class that keeps track of where each space is. Used for all space types
- Dice
  - Used to randomly generate map spaces, meeting monters and dodge chance.
- Fetch
  - Fetches all data for monters, heroes and items
- GameFactory
  - This is an abstract factory that is used to generate party, gamemap, and gameplay classes that are compatible with one another and are used to play a given game. Concrete versions of this class are the LegendsGameFactory and the MonsterGameFactory
- GameMap
  - Map that game is played on. Each tile is represented by a space type (market, commonspace, inaccessible space, party). Party used the class to move around the board. Can be initialized to 6 - 12 gameboard size
- Hero
  - Main class for each hero. Is initialized from fetching data and players are able to choose which hero to play with. Important methods include buying/selling from the market, using items and atltering statistics
- InaccessibleSpace
  - Implements the Space interface. A space where a party of heroes can not access
- Input
  - Input class to gather all input information from user. Anytime a user types into a terminal, this class is called. All methods are static.
- Interface
  - Simple class to initiate party and gamemap and start the game.
- Koulou
  -
- LegendsGameFactory
  - This is a concrete Game Factory that handles creating a Party, LegendsGameMap, and LegendsGamePlay to be used in the Legends of Valor game.
- LegendsGameMap
  - This extends GameMap with a lot of functionality needed to play Legends of Valor. It largely handles functionality associated with world state: spawning and moving monsters, handling hero movement (including recalls and teleports), and creating and managing board spaces.
- LegendsGamePlay
  - Implements GamePlay to control the turn flow of Legends of Valor. The makeMove() function handles a single round of play, and it contains a variety of functions for the different hero actions, including shopping at the nexus spaces, attacking monsters, using items, etc. Also includes functionality for checking victory and loss conditions.
- Main
  - Prompts the user to select which game they would like to play, and gets an Interface for running that game.
- Market
  - Class that represents a market. Each one is randomly initialized with their own items, however have the same number of items for each itemType. Heros can interact with the market to purchase, sell or repair items.
- Monster
  - Monster class is similar to hero class but a lot more simple. Not as complex. Includes a attack method, receiveDamage method and includes all stats of a monster. Monster level and within +- 2 of highest level.
- MonsterGameFactory
  - This is a concrete Game Factory that handles creating a Party, MonsterGameMap, and MonsterGamePlay to be used in the Monsters and Heroes game.
- MonsterGameMap
  - Renamed from Assignment 4, this is the GameMap for the Monsters and Heroes game.
- MonsterGamePlay
  - Renamed from Assignment 4, this is the GamePlay for the Monsters and Heroes game.
- Nexus
  -
- Obstacle
  -
- Party
  - Class that represent a collection of heroes. If all members of the party faint, the game is over. Party can move about the gamemap and fight monster when they encounter them
- Potion
  - Class that implements the Item interface. Can be used once and depending on the potion, increases certain statistics of given hero.
- RandomItemCreator
  - Concrete implementation of ItemCreator, creates a random assortment of items of a given type.
- RandomMonsterCreator
  - Concrete implementation of MonsterCreator, creates a list of monsters below a certain level.
- Spell
  - Class that implements the Item interface. Can be used twice before needing repair. Different spells alter different statistic of receiving monster.
- Weapon
  - Class that implments the Item interface. Can be used 4 times before needing repair. Equiping a sword adds additional damage on attacks.



Interfaces
-
- Equipable
  - Classes that implement these are Weapon and Armor. Has a setEquip function to equip and certain piece of armor or weapon
- GamePlay
  - Interface that requires a makeMove() method to represent a round of gameplay. Implemented by the MonsterGamePlay and LegendsGamePlay classes.
- HeroCreator
  - This is a factory for creating lists of available heroes. It is implemented by the AllHeroCreator class, alternative implementations could prompt a user to create their own hero's attributes, or return only level 1 heroes.
- Item
  - All 4 item types (Weapon, armor, spell, potion) implement this. Given methods are getItemType, hashcode, getLevel, getPrice, uses_left, repair_item
- ItemCreator
  - This is a factory for creating items of a given type. Implemented by the RandomItemCreator class. Alternative implementations could return defined lists of items or utilize different item files for creation.
- MonsterCreator
  - This is a factory for creating a list of monsters. Implemented by the RandomMonsterCreator class. Alternative implementations could return only monsters of a given level (not also below) or only monsters of a certain type.
- Removable
  - Interface implemented by the Obstacle space: used to represent an object that can in some way be cleared.
- Space
  - Interface that is implemented by CommonSpace, Market, InaccessibleSpace and Party. Represent a single tile on the game board. Methods are getSpaceType, is_accessible, and getCoordinate
- SpaceBonus
  - This interface is implemented by Legends of Valor space objects that modify a hero's stats when they enter/leave the space. Methods are applyBonus and removeBonus.

Enums
-
- SpellType
  - Enums for each type of SpellType()
- SpaceType
  - Enum for each type of SpaceType
- PotionType
  - Enum for each type of PotionType
- ItemType
  - Enum for each type of Item
- HeroType
  - Enum for each type of hero. Used to alter stats when leveling up.


**Notes**
Design Patterns:
- Abstract Factory
  - GameFactory (LegendsGameFactory and MonsterGameFactory): we used an abstract factory to simplify initialization of each game. Since each game utilizes a lot of the same pieces (Party, GameMap, and GamePlay classes), but had different considerations for how those should be constructed and what concrete class they should be, we created a factory for each that would provide a single interface for creating all the pieces needed for each game. This meant that we could have variable party sizes in Monsters and Heroes, while having one hero for each lane in Legends of Valor and create the proper GameMap and GamePlay types in tandem with one another.
- Factory
  - HeroCreator, ItemCreator, MonsterCreator (AllHeroCreator, RandomItemCreator, RandomMonsterCreator): One adaption we made from our existing code for Monsters and Heroes was to utilize factories for creating objects from the provided files. While we just implemented a single Creator for each of the types (Heroes, Monsters, and Items), this design pattern would allow our code to scale easier to different sorts of games: for example, creating a new concrete MonsterCreator that only initializes dragons, or a HeroCreator that prompts the user to set their own statistics.
- Singleton
  - Interface: We adapted the Interface class to implement a Singleton design pattern to restrict the number of games that can be concurrently played to one. Nowhere in our code are we trying to initialize multiple games that could be run at the same time, but it future-proofs things by creating a layer of protection against initializing and playing multiple games, which would not function well with how we've implemented a text-based user input by writing to the console.


Design Decisions:
One of our focuses as we adapted the code from Assignment 4 was on designing a solution that could be as modular and extensible as possible. The design patterns we implemented in this assignment are all steps in that direction, particularly the Factories and AbstractFactories being used to handle initialization of many of the key pieces of the game. By requiring a GameFactory to create a new instance of the game, it's easy to guarantee that future games with similar (but incompatible) classes are created accurately and coherently; by defining Factories to handle hero, item, and monster creation, we created an explicit accounting of how we're currently handling those processes. New factories can easily be created if future games require different creation logic, without disrupting the performance of either Monsters and Heroes or Legends of Valor. While it's likely that there are still pieces of extensibility that we've overlooked, we still think extensibility is one of the strenghts of our code.



Unzip the file and cd into the folder src file:

javac -d out src/src/*.java

java -cp out Main

Input/Output Example
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:
'''
output:
█▀▄▀█ ████▄    ▄      ▄▄▄▄▄      ▄▄▄▄▀ ▄███▄   █▄▄▄▄   ▄▄▄▄▄       ██      ▄   ██▄        ▄  █ ▄███▄   █▄▄▄▄ ████▄ ▄███▄     ▄▄▄▄▄
█ █ █ █   █     █    █     ▀▄ ▀▀▀ █    █▀   ▀  █  ▄▀  █     ▀▄     █ █      █  █  █      █   █ █▀   ▀  █  ▄▀ █   █ █▀   ▀   █     ▀▄
█ ▄ █ █   █ ██   █ ▄  ▀▀▀▀▄       █    ██▄▄    █▀▀▌ ▄  ▀▀▀▀▄       █▄▄█ ██   █ █   █     ██▀▀█ ██▄▄    █▀▀▌  █   █ ██▄▄   ▄  ▀▀▀▀▄
█   █ ▀████ █ █  █  ▀▄▄▄▄▀       █     █▄   ▄▀ █  █  ▀▄▄▄▄▀        █  █ █ █  █ █  █      █   █ █▄   ▄▀ █  █  ▀████ █▄   ▄▀ ▀▄▄▄▄▀
█        █  █ █              ▀      ▀███▀     █                    █ █  █ █ ███▀         █  ▀███▀     █         ▀███▀
▀         █   ██                              ▀                    █  █   ██             ▀            ▀
▀
The rules of the game are fairly simple.
The monsters and heroes live in a fictional world. They do not get along and therefore fight each other.
Every time the heroes win, they gain experience and money. Heroes use the money to buy a variety of
items to aid them in their battles with the monsters. When they accumulate enough experience, they
level up, which improves their skills. The goal of the game is for the heroes to defeat monsters and level
up indefinitely.
You will be able to interact with map and move around the world. Market spots are denoted as 'M', blocked spaces are denoted with '~', and common spaces are denoted with ' '.
When moving on a common space, you may encounter monsters in which you must battle against.
During each move you have the option to:
• Attack, using the hero’s equipped weapon
• Cast a spell from the hero’s inventory
• Use a potion from the hero’s inventory
• Equip a weapon or piece of armor
How many heroes would you like to use (1 - 3):

Input:
1

Output:
How big would you like the game map to be (6 - 12):

Input:
6

Output:
1: Name: Parzival
Hero Type: PAL
Level: 7
Health: 700.0
Mana: 300.0
Strength: 750.0
Agility: 650.0
Dexterity: 700.0

2: Name: Sehanine_Moonbow
Hero Type: PAL
Level: 7
Health: 700.0
Mana: 300.0
Strength: 750.0
Agility: 700.0
Dexterity: 700.0

3: Name: Skoraeus_Stonebones
Hero Type: PAL
Level: 4
Health: 400.0
Mana: 250.0
Strength: 650.0
Agility: 600.0
Dexterity: 350.0

4: Name: Garl_Glittergold
Hero Type: PAL
Level: 5
Health: 500.0
Mana: 100.0
Strength: 600.0
Agility: 500.0
Dexterity: 400.0

5: Name: Amaryllis_Astra
Hero Type: PAL
Level: 5
Health: 500.0
Mana: 500.0
Strength: 500.0
Agility: 500.0
Dexterity: 500.0

6: Name: Caliber_Heist
Hero Type: PAL
Level: 8
Health: 800.0
Mana: 400.0
Strength: 400.0
Agility: 400.0
Dexterity: 400.0

7: Name: Gaerdal_Ironhand
Hero Type: WAR
Level: 7
Health: 700.0
Mana: 100.0
Strength: 700.0
Agility: 500.0
Dexterity: 600.0

8: Name: Sehanine_Monnbow
Hero Type: WAR
Level: 8
Health: 800.0
Mana: 600.0
Strength: 700.0
Agility: 800.0
Dexterity: 500.0

9: Name: Muamman_Duathall
Hero Type: WAR
Level: 6
Health: 600.0
Mana: 300.0
Strength: 900.0
Agility: 500.0
Dexterity: 750.0

10: Name: Flandal_Steelskin
Hero Type: WAR
Level: 7
Health: 700.0
Mana: 200.0
Strength: 750.0
Agility: 650.0
Dexterity: 700.0

11: Name: Undefeated_Yoj
Hero Type: WAR
Level: 7
Health: 700.0
Mana: 400.0
Strength: 800.0
Agility: 400.0
Dexterity: 700.0

12: Name: Eunoia_Cyn
Hero Type: WAR
Level: 6
Health: 600.0
Mana: 400.0
Strength: 700.0
Agility: 800.0
Dexterity: 600.0

13: Name: Rillifane_Rallathil
Hero Type: SOR
Level: 9
Health: 900.0
Mana: 1300.0
Strength: 750.0
Agility: 450.0
Dexterity: 500.0

14: Name: Segojan_Earthcaller
Hero Type: SOR
Level: 5
Health: 500.0
Mana: 900.0
Strength: 800.0
Agility: 500.0
Dexterity: 650.0

15: Name: Reign_Havoc
Hero Type: SOR
Level: 8
Health: 800.0
Mana: 800.0
Strength: 800.0
Agility: 800.0
Dexterity: 800.0

16: Name: Reverie_Ashels
Hero Type: SOR
Level: 7
Health: 700.0
Mana: 900.0
Strength: 800.0
Agility: 700.0
Dexterity: 400.0

17: Name: Kalabar
Hero Type: SOR
Level: 6
Health: 600.0
Mana: 800.0
Strength: 850.0
Agility: 400.0
Dexterity: 600.0

18: Name: Skye_Soar
Hero Type: SOR
Level: 5
Health: 500.0
Mana: 1000.0
Strength: 700.0
Agility: 400.0
Dexterity: 500.0

To select the hero, please enter a number between 1 and 18 (inclusive):

Input:
1

Output:
You have selected Parzival
```
+---+---+---+---+---+---+
| P | ~ |   | ~ | ~ |   |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
|   | M |   |   |   |   |
+---+---+---+---+---+---+
|   |   |   | M |   | M |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
| ~ | ~ | M |   | M |   |
+---+---+---+---+---+---+
```

Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
You have encountered some monsters and must battle them!
1. Monster name: Ereshkigall
   Level: 6
   Health: 600
   Damage: 950
   Dodgechange:   35
   Defense:   450


It is now Parzival's turn
Stats:
Name: Parzival
Hero Type: PAL
Level: 7
Health: 700.0
Mana: 300.0
Strength: 750.0
Agility: 650.0
Dexterity: 700.0
Would you like to view the current hero's inventory:
1. Yes  ||  Any other value: No

Input:
1

Output:
   Parzival's Inventory:

Which move would you like to make:
1. Attack a monster
2. Cast a spell
3. Use a potion
4. Equip armor or weapon
Please input your choice (1 - 4):

Input:
1

Output:
Parzivaldoes not currently have a weapon equipped, hopefully you're strong enough to slay these monsters with your bare hands
1. Monster name: Ereshkigall
   Level: 6
   Health: 600
   Damage: 950
   Dodgechange:   35
   Defense:   450


Which monster would you like to attack? Please select a monster that is still alive (1 - 1):

Input:
1

Output:
Parzival attacked Ereshkigall for 750.0 damage
Parzival has killed Ereshkigall
Congratulations! Your party has slain all the monsters!!
Parzival has gained 600 gold and 3 exp.
Parzival's total are now:
Gold: 600
Experience: 3
```
+---+---+---+---+---+---+
|   | ~ |   | ~ | ~ |   |
+---+---+---+---+---+---+
| P |   |   |   |   |   |
+---+---+---+---+---+---+
|   | M |   |   |   |   |
+---+---+---+---+---+---+
|   |   |   | M |   | M |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
| ~ | ~ | M |   | M |   |
+---+---+---+---+---+---+
```

Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
```
+---+---+---+---+---+---+
|   | ~ |   | ~ | ~ |   |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
| P | M |   |   |   |   |
+---+---+---+---+---+---+
|   |   |   | M |   | M |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
| ~ | ~ | M |   | M |   |
+---+---+---+---+---+---+
```
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
d

Output:
You have entered a market, would you like to purchase any items?
Input 1 to view the menu or 2 to leave the market:

Input:
1

Output:
Parzival
Would you like to:
1. Sell
2. Buy
3. Leave
4. View Inventory
5. Repair Item
Please enter a number between 1 and 5.

Input:
2

Output:
   Parzival, you currently have:
   Gold: 3100
   Level: 7
   Item Type: Weapon Item
-----------------------------------------------------------------
1. Weapon:
   Name: Sword
   Price: 500
   Level: 1
   Damage: 800
   Number of Hands Required: 1
   Equiped: false
   Uses Left: 4

2. Weapon:
   Name: TSwords
   Price: 1400
   Level: 8
   Damage: 1600
   Number of Hands Required: 2
   Equiped: false
   Uses Left: 4

3. Weapon:
   Name: Scythe
   Price: 1000
   Level: 6
   Damage: 1100
   Number of Hands Required: 2
   Equiped: false
   Uses Left: 4

Item Type: Potion Item
-----------------------------------------------------------------
4. Potion
   Name: Ambrosia
   Potion Type: Ambrosia Potion
   Price: 1000
   Level: 8
   Effect Amount: 150.0

5. Potion
   Name: Healing_Potion
   Potion Type: Health Potion
   Price: 250
   Level: 1
   Effect Amount: 100.0

6. Potion
   Name: Luck_Elixir
   Potion Type: Agility Potion
   Price: 500
   Level: 4
   Effect Amount: 65.0

Item Type: Armor Item
-----------------------------------------------------------------
7. Armor
   Name: Wizard_Shield
   Price: 1200
   Level: 10
   Damage Reduction: 1500.0
   Equiped: false
   Uses Left: 3
8. Armor
   Name: Full_Body_Armor
   Price: 1000
   Level: 8
   Damage Reduction: 1100.0
   Equiped: false
   Uses Left: 3
   Item Type: Spell Item
-----------------------------------------------------------------
9. Spell
   Name: Spark_Needles
   Spell Type: Lighting Spell
   Price: 500
   Level: 2
   Damage: 600.0

10. Spell
    Name: Thunder_Blast
    Spell Type: Lighting Spell
    Price: 750
    Level: 4
    Damage: 950.0

11. Spell
    Name: Flame_Tornado
    Spell Type: Fire Spell
    Price: 700
    Level: 4
    Damage: 850.0

12. Spell
    Name: Breath_of_Fire
    Spell Type: Fire Spell
    Price: 350
    Level: 1
    Damage: 450.0

13. Spell
    Name: Ice_Blade
    Spell Type: Ice Spell
    Price: 250
    Level: 1
    Damage: 450.0

14. Spell
    Name: Frost_Blizzard
    Spell Type: Ice Spell
    Price: 750
    Level: 5
    Damage: 850.0

To select the item, please enter the number displayed next to the item or input any other integer to exit out

Input:
13

Output:
Parzival has been purchased Ice_Blade for 250
Parzival now has 2850 gold
Would you like to buy another item?
1. Yes
2. No

Input:
1

Output:
   Parzival, you currently have:
   Gold: 2850
   Level: 7
   Item Type: Weapon Item
-----------------------------------------------------------------
1. Weapon:
   Name: Sword
   Price: 500
   Level: 1
   Damage: 800
   Number of Hands Required: 1
   Equiped: false
   Uses Left: 4

2. Weapon:
   Name: TSwords
   Price: 1400
   Level: 8
   Damage: 1600
   Number of Hands Required: 2
   Equiped: false
   Uses Left: 4

3. Weapon:
   Name: Scythe
   Price: 1000
   Level: 6
   Damage: 1100
   Number of Hands Required: 2
   Equiped: false
   Uses Left: 4

Item Type: Potion Item
-----------------------------------------------------------------
4. Potion
   Name: Ambrosia
   Potion Type: Ambrosia Potion
   Price: 1000
   Level: 8
   Effect Amount: 150.0

5. Potion
   Name: Healing_Potion
   Potion Type: Health Potion
   Price: 250
   Level: 1
   Effect Amount: 100.0

6. Potion
   Name: Luck_Elixir
   Potion Type: Agility Potion
   Price: 500
   Level: 4
   Effect Amount: 65.0

Item Type: Armor Item
-----------------------------------------------------------------
7. Armor
   Name: Wizard_Shield
   Price: 1200
   Level: 10
   Damage Reduction: 1500.0
   Equiped: false
   Uses Left: 3
8. Armor
   Name: Full_Body_Armor
   Price: 1000
   Level: 8
   Damage Reduction: 1100.0
   Equiped: false
   Uses Left: 3
   Item Type: Spell Item
-----------------------------------------------------------------
9. Spell
   Name: Spark_Needles
   Spell Type: Lighting Spell
   Price: 500
   Level: 2
   Damage: 600.0

10. Spell
    Name: Thunder_Blast
    Spell Type: Lighting Spell
    Price: 750
    Level: 4
    Damage: 950.0

11. Spell
    Name: Flame_Tornado
    Spell Type: Fire Spell
    Price: 700
    Level: 4
    Damage: 850.0

12. Spell
    Name: Breath_of_Fire
    Spell Type: Fire Spell
    Price: 350
    Level: 1
    Damage: 450.0

13. Spell
    Name: Frost_Blizzard
    Spell Type: Ice Spell
    Price: 750
    Level: 5
    Damage: 850.0

To select the item, please enter the number displayed next to the item or input any other integer to exit out

Input:
7

Output:
You're level is not high enough to purchase this item
Would you like to buy another item?
1. Yes
2. No

Input:
1

Output:
   Parzival, you currently have:
   Gold: 2850
   Level: 7
   Item Type: Weapon Item
-----------------------------------------------------------------
1. Weapon:
   Name: Sword
   Price: 500
   Level: 1
   Damage: 800
   Number of Hands Required: 1
   Equiped: false
   Uses Left: 4

2. Weapon:
   Name: TSwords
   Price: 1400
   Level: 8
   Damage: 1600
   Number of Hands Required: 2
   Equiped: false
   Uses Left: 4

3. Weapon:
   Name: Scythe
   Price: 1000
   Level: 6
   Damage: 1100
   Number of Hands Required: 2
   Equiped: false
   Uses Left: 4

Item Type: Potion Item
-----------------------------------------------------------------
4. Potion
   Name: Ambrosia
   Potion Type: Ambrosia Potion
   Price: 1000
   Level: 8
   Effect Amount: 150.0

5. Potion
   Name: Healing_Potion
   Potion Type: Health Potion
   Price: 250
   Level: 1
   Effect Amount: 100.0

6. Potion
   Name: Luck_Elixir
   Potion Type: Agility Potion
   Price: 500
   Level: 4
   Effect Amount: 65.0

Item Type: Armor Item
-----------------------------------------------------------------
7. Armor
   Name: Wizard_Shield
   Price: 1200
   Level: 10
   Damage Reduction: 1500.0
   Equiped: false
   Uses Left: 3
8. Armor
   Name: Full_Body_Armor
   Price: 1000
   Level: 8
   Damage Reduction: 1100.0
   Equiped: false
   Uses Left: 3
   Item Type: Spell Item
-----------------------------------------------------------------
9. Spell
   Name: Spark_Needles
   Spell Type: Lighting Spell
   Price: 500
   Level: 2
   Damage: 600.0

10. Spell
    Name: Thunder_Blast
    Spell Type: Lighting Spell
    Price: 750
    Level: 4
    Damage: 950.0

11. Spell
    Name: Flame_Tornado
    Spell Type: Fire Spell
    Price: 700
    Level: 4
    Damage: 850.0

12. Spell
    Name: Breath_of_Fire
    Spell Type: Fire Spell
    Price: 350
    Level: 1
    Damage: 450.0

13. Spell
    Name: Frost_Blizzard
    Spell Type: Ice Spell
    Price: 750
    Level: 5
    Damage: 850.0

To select the item, please enter the number displayed next to the item or input any other integer to exit out

Input:
5

Output:
Parzival has been purchased Healing_Potion for 250
Parzival now has 2600 gold
Would you like to buy another item?
1. Yes
2. No

Input:
   1

Output:
   Parzival, you currently have:
   Gold: 2600
   Level: 7
   Item Type: Weapon Item
-----------------------------------------------------------------
1. Weapon:
   Name: Sword
   Price: 500
   Level: 1
   Damage: 800
   Number of Hands Required: 1
   Equiped: false
   Uses Left: 4

2. Weapon:
   Name: TSwords
   Price: 1400
   Level: 8
   Damage: 1600
   Number of Hands Required: 2
   Equiped: false
   Uses Left: 4

3. Weapon:
   Name: Scythe
   Price: 1000
   Level: 6
   Damage: 1100
   Number of Hands Required: 2
   Equiped: false
   Uses Left: 4

Item Type: Potion Item
-----------------------------------------------------------------
4. Potion
   Name: Ambrosia
   Potion Type: Ambrosia Potion
   Price: 1000
   Level: 8
   Effect Amount: 150.0

5. Potion
   Name: Luck_Elixir
   Potion Type: Agility Potion
   Price: 500
   Level: 4
   Effect Amount: 65.0

Item Type: Armor Item
-----------------------------------------------------------------
6. Armor
   Name: Wizard_Shield
   Price: 1200
   Level: 10
   Damage Reduction: 1500.0
   Equiped: false
   Uses Left: 3
7. Armor
   Name: Full_Body_Armor
   Price: 1000
   Level: 8
   Damage Reduction: 1100.0
   Equiped: false
   Uses Left: 3
   Item Type: Spell Item
-----------------------------------------------------------------
8. Spell
   Name: Spark_Needles
   Spell Type: Lighting Spell
   Price: 500
   Level: 2
   Damage: 600.0

9. Spell
   Name: Thunder_Blast
   Spell Type: Lighting Spell
   Price: 750
   Level: 4
   Damage: 950.0

10. Spell
    Name: Flame_Tornado
    Spell Type: Fire Spell
    Price: 700
    Level: 4
    Damage: 850.0

11. Spell
    Name: Breath_of_Fire
    Spell Type: Fire Spell
    Price: 350
    Level: 1
    Damage: 450.0

12. Spell
    Name: Frost_Blizzard
    Spell Type: Ice Spell
    Price: 750
    Level: 5
    Damage: 850.0

To select the item, please enter the number displayed next to the item or input any other integer to exit out

Input:
3

Output:
Parzival has been purchased Scythe for 1000
Parzival now has 1600 gold
Would you like to buy another item?
1. Yes
2. No

Input:
   2

Output:
   Would you like to:
1. Sell
2. Buy
3. Leave
4. View Inventory
5. Repair Item
   Please enter a number between 1 and 5.

Input:
3
```
   +---+---+---+---+---+---+
   |   | ~ |   | ~ | ~ |   |
   +---+---+---+---+---+---+
   |   |   |   |   |   |   |
   +---+---+---+---+---+---+
   |   | P |   |   |   |   |
   +---+---+---+---+---+---+
   |   |   |   | M |   | M |
   +---+---+---+---+---+---+
   |   |   |   |   |   |   |
   +---+---+---+---+---+---+
   | ~ | ~ | M |   | M |   |
   +---+---+---+---+---+---+
```

Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
You have encountered some monsters and must battle them!
1. Monster name: DocOck
   Level: 6
   Health: 600
   Damage: 600
   Dodgechange:   55
   Defense:   600


It is now Parzival's turn
Stats:
Name: Parzival
Hero Type: PAL
Level: 7
Health: 700.0
Mana: 300.0
Strength: 750.0
Agility: 650.0
Dexterity: 700.0
Would you like to view the current hero's inventory:
1. Yes  ||  Any other value: No

Input:
1

Output:
   Parzival's Inventory:
   Item Type: Weapon Item
-----------------------------------------------------------------
1. Weapon:
   Name: Scythe
   Price: 1000
   Level: 6
   Damage: 1100
   Number of Hands Required: 2
   Equiped: false
   Uses Left: 4

Item Type: Potion Item
-----------------------------------------------------------------
2. Potion
   Name: Healing_Potion
   Potion Type: Health Potion
   Price: 250
   Level: 1
   Effect Amount: 100.0

Item Type: Spell Item
-----------------------------------------------------------------
3. Spell
   Name: Ice_Blade
   Spell Type: Ice Spell
   Price: 250
   Level: 1
   Damage: 450.0


Which move would you like to make:
1. Attack a monster
2. Cast a spell
3. Use a potion
4. Equip armor or weapon
Please input your choice (1 - 4):

Input:
1

Output:
Parzivaldoes not currently have a weapon equipped, hopefully you're strong enough to slay these monsters with your bare hands
1. Monster name: DocOck
   Level: 6
   Health: 600
   Damage: 600
   Dodgechange:   55
   Defense:   600


Which monster would you like to attack? Please select a monster that is still alive (1 - 1):

Input:
1

Output:
Parzival attacked DocOck for 750.0 damage
Parzival has killed DocOck
Congratulations! Your party has slain all the monsters!!
Parzival has gained 600 gold and 3 exp.
Parzival's total are now:
Gold: 600
Experience: 3
```
+---+---+---+---+---+---+
|   | ~ |   | ~ | ~ |   |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
|   | M |   |   |   |   |
+---+---+---+---+---+---+
|   | P |   | M |   | M |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
| ~ | ~ | M |   | M |   |
+---+---+---+---+---+---+
```

Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
```
+---+---+---+---+---+---+
|   | ~ |   | ~ | ~ |   |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
|   | M |   |   |   |   |
+---+---+---+---+---+---+
|   |   |   | M |   | M |
+---+---+---+---+---+---+
|   | P |   |   |   |   |
+---+---+---+---+---+---+
| ~ | ~ | M |   | M |   |
+---+---+---+---+---+---+
```

Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
a

Output:
```
+---+---+---+---+---+---+
|   | ~ |   | ~ | ~ |   |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
|   | M |   |   |   |   |
+---+---+---+---+---+---+
|   |   |   | M |   | M |
+---+---+---+---+---+---+
| P |   |   |   |   |   |
+---+---+---+---+---+---+
| ~ | ~ | M |   | M |   |
+---+---+---+---+---+---+
```
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
d

Output:
You have encountered some monsters and must battle them!
1. Monster name: TheWeatherbe
   Level: 8
   Health: 800
   Damage: 800
   Dodgechange:   80
   Defense:   900


It is now Parzival's turn
Stats:
Name: Parzival
Hero Type: PAL
Level: 7
Health: 700.0
Mana: 300.0
Strength: 750.0
Agility: 650.0
Dexterity: 700.0
Would you like to view the current hero's inventory:
1. Yes  ||  Any other value: No

Input:
1

Output:
   Parzival's Inventory:
   Item Type: Weapon Item
-----------------------------------------------------------------
1. Weapon:
   Name: Scythe
   Price: 1000
   Level: 6
   Damage: 1100
   Number of Hands Required: 2
   Equiped: false
   Uses Left: 4

Item Type: Potion Item
-----------------------------------------------------------------
2. Potion
   Name: Healing_Potion
   Potion Type: Health Potion
   Price: 250
   Level: 1
   Effect Amount: 100.0

Item Type: Spell Item
-----------------------------------------------------------------
3. Spell
   Name: Ice_Blade
   Spell Type: Ice Spell
   Price: 250
   Level: 1
   Damage: 450.0


Which move would you like to make:
1. Attack a monster
2. Cast a spell
3. Use a potion
4. Equip armor or weapon
Please input your choice (1 - 4):

Input:
4

Output:
Your available equipments to equip are:
No available Armor Item's
Weapon:
Name: Scythe
Price: 1000
Level: 6
Damage: 1100
Number of Hands Required: 2
Equiped: false
Uses Left: 4

Please select a piece of armor or weapon you would like to equip
Please select which item you would like.
Parzival's Inventory:
Item Type: Weapon Item
-----------------------------------------------------------------
1. Weapon:
   Name: Scythe
   Price: 1000
   Level: 6
   Damage: 1100
   Number of Hands Required: 2
   Equiped: false
   Uses Left: 4

Item Type: Potion Item
-----------------------------------------------------------------
2. Potion
   Name: Healing_Potion
   Potion Type: Health Potion
   Price: 250
   Level: 1
   Effect Amount: 100.0

Item Type: Spell Item
-----------------------------------------------------------------
3. Spell
   Name: Ice_Blade
   Spell Type: Ice Spell
   Price: 250
   Level: 1
   Damage: 450.0

Input:
1

Output:
Parzival has equiped Scythe
It is now TheWeatherbe's turn
Stats:
Monster name: TheWeatherbe
Level: 8
Health: 800
Damage: 800
Dodgechange:   80
Defense:   900

TheWeatherbe attacked Parzival for 799.05 damage
Parzival has fainted from TheWeatherbe's attack!
All your heroes are dead!
GAME OVER
Would you like to play again
1. Yes
2. No

Input:
2

Process finished with exit code 0
