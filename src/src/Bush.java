public class Bush extends CommonSpace implements SpaceBonus{
    public Bush(Coordinate coord){
        super(coord);
    }
    public void applyBonus(Hero hero){

        float amount = (hero.getDexterity() * 1.1f) - (hero.getDexterity());
        hero.increaseDexterity(amount);
    }

    public void removeBonus(Hero hero){
        float amount = (hero.getDexterity() * 0.1f) / 1.1f;
        hero.increaseDexterity(-amount);
    }

    public String toString(){
        // need to add logic
        return "Bush";
    }
}
