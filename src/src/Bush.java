
 /*
  * Bush.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


public class Bush extends CommonSpace implements SpaceBonus{
    public Bush(Coordinate coord){
        super(coord);
        changeSpaceType(SpaceType.BUSH);

    }
    public void applyBonus(Hero hero){
        // increase dexterity by 10%
        float amount = (hero.getDexterity() * 1.1f) - (hero.getDexterity());
        hero.increaseDexterity(amount);
    }

    public void removeBonus(Hero hero){
        // gets rid of bonus
        float amount = (hero.getDexterity() * 0.1f) / 1.1f;
        hero.increaseDexterity(-amount);
    }

    public String toString(){
        // need to add logic
        return "B";
    }
}
