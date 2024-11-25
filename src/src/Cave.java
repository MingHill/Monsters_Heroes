
 /*
  * Cave.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


public class Cave extends CommonSpace implements SpaceBonus{
    public Cave(Coordinate Coord){
        super(Coord);
        changeSpaceType(SpaceType.CAVE);
    }


    public void applyBonus(Hero hero){
        // increase agility by 10%
        float amount = (hero.getAgility() * 1.1f) - (hero.getAgility());
        hero.increaseAgility(amount);
    }

    public void removeBonus(Hero hero){
        // gets rid of bonus
        float amount = (hero.getAgility() * 0.1f) / 1.1f;
        hero.increaseAgility(-amount);
    }

    public String toString(){
        // add toString
        return "C";
    }
}
