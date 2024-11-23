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
        return "Bush";
    }
}
