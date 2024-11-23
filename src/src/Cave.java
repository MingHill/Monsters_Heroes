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
