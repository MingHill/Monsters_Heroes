
 /*
  * Announce.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Helper class that outputs commonly-used game logs to the console.
  *
  * Credits: All code is our own.
  */


public class Announce {
    public static void heroAttackDamage(String name, float damage, Monster monster){
        System.out.println(name + " attacked " + monster.getName() + " for " + damage + " damage");
    }

    public static void killedMonster(String name, Monster monster){
        System.out.println(name + " has killed " + monster.getName());
    }

    public static void monsterAttackDamage(Hero hero, float damage, Monster monster){
        System.out.println(monster.getName() + " attacked " + hero.getName() + " for " + damage + " damage");
    }

    public static void heroFainted(Hero hero, Monster monster){
        System.out.println(hero.getName() + " has fainted from " + monster.getName() + "'s attack!");
    }

}
