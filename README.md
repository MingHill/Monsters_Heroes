CS611 - Assignment 5
-

Legends of Valor

Names: Soong Ming Hill, Tanner Bangerter

Email: minghill@bu.edu, tanner@bu.edu

Student ID: U50983276, U96993176

Files
-
This section should be all of the source code files that have a .java extension. You should also include a brief description of what the class does.

**Classes:**

- AllHeroCreator
  - This is a concrete HeroCreator that creates all heroes present in the text data files.
- Announce
  - A class to make announcements such as when a hero is killed or when monster is killed
- Armor
  - Implements Item and Equipable. Reduces damage taken from a monster if equiped. Has a maximum use of 3 uses before needing to be repaired
- Battle
  - Battle class where heroes and monsters compete in battle, used by the Monsters and Heroes game. Main method is a switch based method where all heroes can choose which
  attack to use on which hero and monsters attack randomly.
- Bush
  - Bush object that implements the SpaceBonus interface and extends from the commonspace. When a hero lands on it, will increase the heroes dexterity
- Cave
  - Cave object that implements the SpaceBonus interface and extends from the commonspace. When a hero lands on it, will increase the heroes agility
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
  - Implements the spacebonus interface and extends commonspace. When a hero lands on it, will increase the strength of a hero
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
  - Nexus represents the home base of the heroes and monster. Heroes can recall back to their respective nexus and the nexus acts as a marketplace for the heros.
- Obstacle
  - A object that extends the Commonplace class and implements the removeable interface. Players must remove it first before being able to access the spot.
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



**Interfaces:**

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

**Enums**

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


Notes
-
**Design Patterns:**
- Abstract Factory
  - GameFactory (LegendsGameFactory and MonsterGameFactory): we used an abstract factory to simplify initialization of each game. Since each game utilizes a lot of the same pieces (Party, GameMap, and GamePlay classes), but had different considerations for how those should be constructed and what concrete class they should be, we created a factory for each that would provide a single interface for creating all the pieces needed for each game. This meant that we could have variable party sizes in Monsters and Heroes, while having one hero for each lane in Legends of Valor and create the proper GameMap and GamePlay types in tandem with one another.
- Factory
  - HeroCreator, ItemCreator, MonsterCreator (AllHeroCreator, RandomItemCreator, RandomMonsterCreator): One adaption we made from our existing code for Monsters and Heroes was to utilize factories for creating objects from the provided files. While we just implemented a single Creator for each of the types (Heroes, Monsters, and Items), this design pattern would allow our code to scale easier to different sorts of games: for example, creating a new concrete MonsterCreator that only initializes dragons, or a HeroCreator that prompts the user to set their own statistics.
- Singleton
  - Interface: We adapted the Interface class to implement a Singleton design pattern to restrict the number of games that can be concurrently played to one. Nowhere in our code are we trying to initialize multiple games that could be run at the same time, but it future-proofs things by creating a layer of protection against initializing and playing multiple games, which would not function well with how we've implemented a text-based user input by writing to the console.


**Design Decisions:**
We chose Ming's implementation to use as a base, since it was more feature-complete and had some additional features that made extension easier, including the enumerations for space types and item types. While a hybrid approach would have been beneficial in some ways, the added work of grafting on advantageous code from Tanner's solution was not worth the added effort, though as we created our solution for Legends of Valor we focused on improving extensibility throughout the code base.

One of our focuses as we adapted the code from Assignment 4 was on designing a solution that could be as modular and extensible as possible. The design patterns we implemented in this assignment are all steps in that direction, particularly the Factories and AbstractFactories being used to handle initialization of many of the key pieces of the game. By requiring a GameFactory to create a new instance of the game, it's easy to guarantee that future games with similar (but incompatible) classes are created accurately and coherently; by defining Factories to handle hero, item, and monster creation, we created an explicit accounting of how we're currently handling those processes. New factories can easily be created if future games require different creation logic, without disrupting the performance of either Monsters and Heroes or Legends of Valor. While it's likely that there are still pieces of extensibility that we've overlooked, we still think extensibility is one of the strenghts of our code.



Unzip the file and cd into the folder src file:

javac -d out src/src/*.java

java -cp out Main

**Input/Output Example**
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:
'''
/opt/homebrew/Cellar/openjdk/22.0.2/libexec/openjdk.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=58910:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Users/minghill/Desktop/BU/CS611/Assignments /Monsters_Heroes/out/production/src Main
Welcome to Ming and Tanner's Island
```
Which game would you like to play:
1. Monsters and Heroes
2. Legends of Valor

1


Output:
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

To select the hero, please enter a number between 1 and 18:

Input:
1

Output:
You have selected Parzival
How big would you like the game map to be (6 - 12):

Input:
6

Output:


+---+---+---+---+---+---+
| P | M | M |   |   | M |
+---+---+---+---+---+---+
|   | ~ | M |   |   | M |
+---+---+---+---+---+---+
| M |   |   |   |   | M |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
|   |   |   | ~ |   |   |
+---+---+---+---+---+---+
|   | M |   | M |   |   |
+---+---+---+---+---+---+


Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:

+---+---+---+---+---+---+
|   | M | M |   |   | M |
+---+---+---+---+---+---+
| P | ~ | M |   |   | M |
+---+---+---+---+---+---+
| M |   |   |   |   | M |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
|   |   |   | ~ |   |   |
+---+---+---+---+---+---+
|   | M |   | M |   |   |
+---+---+---+---+---+---+


Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
You have entered a market, would you like to purchase any items?
Input 1 to view the menu or 2 to leave the market:

Input:
2

Output:
+---+---+---+---+---+---+
|   | M | M |   |   | M |
+---+---+---+---+---+---+
|   | ~ | M |   |   | M |
+---+---+---+---+---+---+
| P |   |   |   |   | M |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
|   |   |   | ~ |   |   |
+---+---+---+---+---+---+
|   | M |   | M |   |   |
+---+---+---+---+---+---+

Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
d

Output:
+---+---+---+---+---+---+
|   | M | M |   |   | M |
+---+---+---+---+---+---+
|   | ~ | M |   |   | M |
+---+---+---+---+---+---+
| M | P |   |   |   | M |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
|   |   |   | ~ |   |   |
+---+---+---+---+---+---+
|   | M |   | M |   |   |
+---+---+---+---+---+---+

Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
d

Output:
+---+---+---+---+---+---+
|   | M | M |   |   | M |
+---+---+---+---+---+---+
|   | ~ | M |   |   | M |
+---+---+---+---+---+---+
| M |   | P |   |   | M |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
|   |   |   | ~ |   |   |
+---+---+---+---+---+---+
|   | M |   | M |   |   |
+---+---+---+---+---+---+

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
1. Monster name: Igneel
   Level: 6
   Health: 600
   Damage: 600
   Dodgechange:   60
   Defense:   400


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
1. Monster name: Igneel
   Level: 6
   Health: 600
   Damage: 600
   Dodgechange:   60
   Defense:   400


Which monster would you like to attack? Please select a monster that is still alive (1 - 1):

Input:
1

Output:
Parzival attacked Igneel for 750.0 damage
Parzival has killed Igneel
Congratulations! Your party has slain all the monsters!!
Parzival has gained 600 gold and 3 exp.
Parzival's total are now:
Gold: 600
Experience: 3
+---+---+---+---+---+---+
|   | M | M |   |   | M |
+---+---+---+---+---+---+
|   | ~ | M |   |   | M |
+---+---+---+---+---+---+
| M |   |   | P |   | M |
+---+---+---+---+---+---+
|   |   |   |   |   |   |
+---+---+---+---+---+---+
|   |   |   | ~ |   |   |
+---+---+---+---+---+---+
|   | M |   | M |   |   |
+---+---+---+---+---+---+

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
   2

Output:
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
1. Monster name: TheWeatherbe
   Level: 8
   Health: 800
   Damage: 800
   Dodgechange:   80
   Defense:   900


Which monster would you like to attack? Please select a monster that is still alive (1 - 1):

Input:
1

Output:
Parzival attacked TheWeatherbe for 750.0 damage
It is now TheWeatherbe's turn
Stats:
Monster name: TheWeatherbe
Level: 8
Health: 50
Damage: 800
Dodgechange:   80
Defense:   900

TheWeatherbe attacked Parzival for 799.05 damage
Parzival has fainted from TheWeatherbe's attack!
All your heroes are dead!
GAME OVER
Would you like to choose a new game?
1. Yes
2. No

Input:
   1

Output:
   Which game would you like to play:
1. Monsters and Heroes
2. Legends of Valor

Input:
2

    __                              __              ____   _    __      __
/ /   ___  ____ ____  ____  ____/ /____   ____  / __/  | |  / /___ _/ /___  _____
/ /   / _ \/ __ `/ _ \/ __ \/ __  / ___/  / __ \/ /_    | | / / __ `/ / __ \/ ___/
/ /___/  __/ /_/ /  __/ / / / /_/ (__  )  / /_/ / __/    | |/ / /_/ / / /_/ / /
/_____/\___/\__, /\___/_/ /_/\__,_/____/   \____/_/       |___/\__,_/_/\____/_/
/____/

Legends of Valor is a MOBA (multiplayer online battle arena)-like game. The player will control
a team of 3 heroes who will attempt to fight their way through to the monsters’ Nexus. The
heroes win if any of them reach the monsters’ Nexus. The heroes lose if any monster reaches
the heroes’ Nexus.
You will be able to interact with the map during each turn.
The spaces are marked as (N) Nexus, (C) common space(O) Obstacle, (K) Koulou, (C) Cave, (B) Bush, and (~) Inaccesibile space.
Nexus, Cave, Bush and Koulou boost you heroes ability by a certain percent when in that given spot.You also have the ability to teleport to a different hero, or recall back to your NEXUS. In the chance you meet a monster, you have the ability to attack using spells or normal attack. If you want to purchase any items, that can you done at your NEXUS using the gold that you have
Have fun and good luck!!
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

To select the hero, please enter a number between 1 and 18:

Input:
1

Output:
You have selected Parzival
To select the hero, please enter a number between 1 and 18:

Input:
2

Output:
You have selected Sehanine_Moonbow
To select the hero, please enter a number between 1 and 18:

Input:
3

Output:
You have selected Skoraeus_Stonebones
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |    H3 |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | | M3    |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 1, Parzival's, turn
Stats:
Name: Parzival
Hero Type: PAL
Level: 7
Health: 700.0
Mana: 300.0
Strength: 750.0
Agility: 650.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |    H3 |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | | M3    |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 2, Sehanine_Moonbow's, turn
Stats:
Name: Sehanine_Moonbow
Hero Type: PAL
Level: 7
Health: 700.0
Mana: 300.0
Strength: 750.0
Agility: 700.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | | M3    |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 3, Skoraeus_Stonebones's, turn
Stats:
Name: Skoraeus_Stonebones
Hero Type: PAL
Level: 4
Health: 400.0
Mana: 250.0
Strength: 650.0
Agility: 600.0
Dexterity: 350.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Skoraeus_Stonebones has increased agility to 660.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |    H3 |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | | M3    |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


The monsters advance!
Parzival has regenerated.
HP: 770.0
MANA: 330.0
Sehanine_Moonbow has regenerated.
HP: 770.0
MANA: 330.0
Skoraeus_Stonebones has regenerated.
HP: 440.0
MANA: 275.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |    H3 |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | | M3    |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 1, Parzival's, turn
Stats:
Name: Parzival
Hero Type: PAL
Level: 7
Health: 770.0
Mana: 330.0
Strength: 750.0
Agility: 650.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |    H3 |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |    H1 |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | | M3    |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 2, Sehanine_Moonbow's, turn
Stats:
Name: Sehanine_Moonbow
Hero Type: PAL
Level: 7
Health: 770.0
Mana: 330.0
Strength: 750.0
Agility: 700.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output;
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | | M3    |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 3, Skoraeus_Stonebones's, turn
Stats:
Name: Skoraeus_Stonebones
Hero Type: PAL
Level: 4
Health: 440.0
Mana: 275.0
Strength: 650.0
Agility: 660.0
Dexterity: 350.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
6

Output:
Available heroes to teleport to:
1. H2
2. H3
3. H1
   Which hero would you like to teleport to? Enter the number:

Input:
   1

Output:
   Where would you like to teleport to:
1. Next to hero
2. Behind hero

Input:
   1

Output:
   Skoraeus_Stonebones has increased agility to 600.0
   Skoraeus_Stonebones has increased dexterity to 385.0
   N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
   |       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
   N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |    H1 |  | XXXXXX | |    H3 |  |    H2 |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | | M3    |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


The monsters advance!
Parzival has regenerated.
HP: 847.0
MANA: 363.0
Sehanine_Moonbow has regenerated.
HP: 847.0
MANA: 363.0
Skoraeus_Stonebones has regenerated.
HP: 484.0
MANA: 302.5
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |    H1 |  | XXXXXX | |    H3 |  |    H2 |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 1, Parzival's, turn
Stats:
Name: Parzival
Hero Type: PAL
Level: 7
Health: 847.0
Mana: 363.0
Strength: 750.0
Agility: 650.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Parzival has increased strength to 825.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |    H3 |  |    H2 |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 2, Sehanine_Moonbow's, turn
Stats:
Name: Sehanine_Moonbow
Hero Type: PAL
Level: 7
Health: 847.0
Mana: 363.0
Strength: 750.0
Agility: 700.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Sehanine_Moonbow has increased dexterity to 770.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |    H3 |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  O - O - O  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 3, Skoraeus_Stonebones's, turn
Stats:
Name: Skoraeus_Stonebones
Hero Type: PAL
Level: 4
Health: 484.0
Mana: 302.5
Strength: 650.0
Agility: 600.0
Dexterity: 385.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Skoraeus_Stonebones has removed a obstacle
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |    H3 |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


The monsters advance!
Parzival has regenerated.
HP: 931.7
MANA: 399.30002
Sehanine_Moonbow has regenerated.
HP: 931.7
MANA: 399.30002
Skoraeus_Stonebones has regenerated.
HP: 532.4
MANA: 332.75
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |    H3 |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
| M1    |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 1, Parzival's, turn
Stats:
Name: Parzival
Hero Type: PAL
Level: 7
Health: 931.7
Mana: 399.30002
Strength: 825.0
Agility: 650.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
2

Output:
Which monster would you like to attack?
1: M1 Chronepsish
Enter a number between 1 and 1.

Input:
1

Output:
Parzival attacked Chronepsish for 825.0 damage
Parzival has killed Chronepsish
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |    H3 |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | | M2    |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 2, Sehanine_Moonbow's, turn
Stats:
Name: Sehanine_Moonbow
Hero Type: PAL
Level: 7
Health: 931.7
Mana: 399.30002
Strength: 750.0
Agility: 700.0
Dexterity: 770.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
2

Output:
Which monster would you like to attack?
1: M2 Cyrrollalee
Enter a number between 1 and 1.

Input:
1

Output:
Sehanine_Moonbow attacked Cyrrollalee for 750.0 damage
Sehanine_Moonbow has killed Cyrrollalee
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |    H3 |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 3, Skoraeus_Stonebones's, turn
Stats:
Name: Skoraeus_Stonebones
Hero Type: PAL
Level: 4
Health: 532.4
Mana: 332.75
Strength: 650.0
Agility: 600.0
Dexterity: 385.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
7

Output:
Skoraeus_Stonebones has increased dexterity to 350.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


The monsters advance!
Parzival has regenerated.
HP: 1024.87
MANA: 439.23004
Sehanine_Moonbow has regenerated.
HP: 1024.87
MANA: 439.23004
Skoraeus_Stonebones has regenerated.
HP: 585.64
MANA: 366.025
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 1, Parzival's, turn
Stats:
Name: Parzival
Hero Type: PAL
Level: 7
Health: 1024.87
Mana: 439.23004
Strength: 825.0
Agility: 650.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Parzival has increased strength to 750.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 2, Sehanine_Moonbow's, turn
Stats:
Name: Sehanine_Moonbow
Hero Type: PAL
Level: 7
Health: 1024.87
Mana: 439.23004
Strength: 750.0
Agility: 700.0
Dexterity: 770.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Sehanine_Moonbow has increased dexterity to 700.0
Sehanine_Moonbow has increased agility to 770.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  | M3    |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 3, Skoraeus_Stonebones's, turn
Stats:
Name: Skoraeus_Stonebones
Hero Type: PAL
Level: 4
Health: 585.64
Mana: 366.025
Strength: 650.0
Agility: 600.0
Dexterity: 350.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Skoraeus_Stonebones has increased agility to 660.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  | M3    |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


The monsters advance!
Parzival has regenerated.
HP: 1127.357
MANA: 483.15305
Sehanine_Moonbow has regenerated.
HP: 1127.357
MANA: 483.15305
Skoraeus_Stonebones has regenerated.
HP: 644.20404
MANA: 402.6275
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 1, Parzival's, turn
Stats:
Name: Parzival
Hero Type: PAL
Level: 7
Health: 1127.357
Mana: 483.15305
Strength: 750.0
Agility: 650.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 2, Sehanine_Moonbow's, turn
Stats:
Name: Sehanine_Moonbow
Hero Type: PAL
Level: 7
Health: 1127.357
Mana: 483.15305
Strength: 750.0
Agility: 770.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Sehanine_Moonbow has increased agility to 700.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 3, Skoraeus_Stonebones's, turn
Stats:
Name: Skoraeus_Stonebones
Hero Type: PAL
Level: 4
Health: 644.20404
Mana: 402.6275
Strength: 650.0
Agility: 660.0
Dexterity: 350.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Skoraeus_Stonebones has increased agility to 600.0
Skoraeus_Stonebones has increased strength to 715.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


The monsters advance!
DocOck attacked Skoraeus_Stonebones for 599.05 damage
Parzival has regenerated.
HP: 1240.0928
MANA: 531.4684
Sehanine_Moonbow has regenerated.
HP: 1240.0928
MANA: 531.4684
Skoraeus_Stonebones has regenerated.
HP: 49.66946
MANA: 442.89026
A new wave of monsters has spawned!
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  O - O - O  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
| M4    |  |       |  | XXXXXX | | M5    |  |       |  | XXXXXX | | M6    |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 1, Parzival's, turn
Stats:
Name: Parzival
Hero Type: PAL
Level: 7
Health: 1240.0928
Mana: 531.4684
Strength: 750.0
Agility: 650.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Parzival has removed a obstacle
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
| M4    |  |       |  | XXXXXX | | M5    |  |       |  | XXXXXX | | M6    |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 2, Sehanine_Moonbow's, turn
Stats:
Name: Sehanine_Moonbow
Hero Type: PAL
Level: 7
Health: 1240.0928
Mana: 531.4684
Strength: 750.0
Agility: 700.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
| M4    |  |       |  | XXXXXX | | M5    |  |       |  | XXXXXX | | M6    |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 3, Skoraeus_Stonebones's, turn
Stats:
Name: Skoraeus_Stonebones
Hero Type: PAL
Level: 4
Health: 49.66946
Mana: 442.89026
Strength: 715.0
Agility: 600.0
Dexterity: 350.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Skoraeus_Stonebones has increased strength to 650.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3 H3 |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
|       |  |       |  | XXXXXX | |       |  |    H2 |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
| M4    |  |       |  | XXXXXX | | M5    |  |       |  | XXXXXX | | M6    |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


The monsters advance!
DocOck attacked Skoraeus_Stonebones for 599.05 damage
Skoraeus_Stonebones has fainted from DocOck's attack!
Sehanine_Moonbowwas able to succesfully dodge the attack
Kiaransalee attacked Sehanine_Moonbow for 0.0 damage
Parzival has regenerated.
HP: 1364.102
MANA: 584.61523
Sehanine_Moonbow has regenerated.
HP: 1364.102
MANA: 584.61523
Skoraeus_Stonebones has increased health to -149.38055
Skoraeus_Stonebones has been respawned.
HP: -149.38055
MANA: 250.0
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
| M4    |  |       |  | XXXXXX | |       |  |    H2 |  | XXXXXX | | M6    |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | | M5    |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 1, Parzival's, turn
Stats:
Name: Parzival
Hero Type: PAL
Level: 7
Health: 1364.102
Mana: 584.61523
Strength: 750.0
Agility: 650.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
2

Output:
Which monster would you like to attack?
1: M4 Kiaransalee
Enter a number between 1 and 1.

Input:
1

Output:
Parzival attacked Kiaransalee for 750.0 damage
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
| M4    |  |       |  | XXXXXX | |       |  |    H2 |  | XXXXXX | | M6    |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | | M5    |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 2, Sehanine_Moonbow's, turn
Stats:
Name: Sehanine_Moonbow
Hero Type: PAL
Level: 7
Health: 1364.102
Mana: 584.61523
Strength: 750.0
Agility: 700.0
Dexterity: 700.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
| M4    |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | | M6    |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | | M5    |  |    H2 |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


It is now Hero 3, Skoraeus_Stonebones's, turn
Stats:
Name: Skoraeus_Stonebones
Hero Type: PAL
Level: 4
Health: -149.38055
Mana: 250.0
Strength: 650.0
Agility: 600.0
Dexterity: 350.0
What action would you like to take?
1) Move
2) Attack
3) Cast a spell
4) Equip an item
5) Use a potion
6) Teleport
7) Recall
8) Buy / Sell items
9) Exit
Enter a number between 1 and 9.

Input:
1

Output:
Make a move:
W (up)
S (down)
A (left)
D (right)
Q (quit)

Input:
s

Output:
Skoraeus_Stonebones has increased agility to 660.0


N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N

K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |    H3 |
K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  K - K - K  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  K - K - K  K - K - K

O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  | M3    |
O - O - O  K - K - K  ~ - ~ - ~  C - C - C  B - B - B  ~ - ~ - ~  C - C - C  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C
|       |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
C - C - C  C - C - C  ~ - ~ - ~  K - K - K  C - C - C  ~ - ~ - ~  C - C - C  C - C - C

B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C
|       |  |    H1 |  | XXXXXX | |       |  |       |  | XXXXXX | |       |  |       |
B - B - B  C - C - C  ~ - ~ - ~  C - C - C  C - C - C  ~ - ~ - ~  O - O - O  C - C - C

C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C
| M4    |  |       |  | XXXXXX | |       |  |       |  | XXXXXX | | M6    |  |       |
C - C - C  C - C - C  ~ - ~ - ~  B - B - B  C - C - C  ~ - ~ - ~  B - B - B  C - C - C

N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N
|       |  |       |  | XXXXXX | | M5    |  |    H2 |  | XXXXXX | |       |  |       |
N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N  ~ - ~ - ~  N - N - N  N - N - N


You reached the monsters' nexus! Congratulations, you win!
GAME OVER
Would you like to choose a new game?
1. Yes
2. No

Input:
   2
```
Process finished with exit code 0
