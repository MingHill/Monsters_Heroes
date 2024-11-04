public class Announce {
    public static void heroAttack(String name, float damage, Monster monster){
        System.out.println(name + " attacked the" + monster.getName() + "for " + damage + " damage");
        if (monster.is_dead()){
            System.out.println(monster.getName() + " is dead");
        }
    }
}
