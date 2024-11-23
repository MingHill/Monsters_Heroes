public class Koulou extends CommonSpace implements SpaceBonus{
    public Koulou (Coordinate coord){
        super(coord);
        changeSpaceType(SpaceType.KOU);
    }

    public void applyBonus(Hero hero){
        // increase strength by 10%
        float amount = (hero.getStrength() * 1.1f) - (hero.getStrength());
        hero.increaseStrength(amount);
    }

    public void removeBonus(Hero hero){
        // removes bonus
        float amount = (hero.getStrength() * 0.1f) / 1.1f;
        hero.increaseStrength(-amount);
    }

    public String toString(){
        // return logic for toString
        return "Koulou";
    }



}
