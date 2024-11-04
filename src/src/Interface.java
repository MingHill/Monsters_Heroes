public class Interface {

    public void startGame(){
        System.out.println("Welcome to Monsters are Heroes! You are about to embark on an unforgettable journey!!!");
        System.out.println("How many heroes would you like to embark? Please choose between 1 - 4");
        Input.getHeroesCount();



    }

    // Need to finish explaining the rules
    public void explainRules(){
        System.out.println("The objective of the game is fairly simple \n _______________________________________________________________ ");
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
