public class CommonSpace implements Space{
    private boolean is_accesible;
    private Coordinate location;
    private final SpaceType spaceType;

    public CommonSpace(Coordinate coord){
        this.is_accesible = true;
        this.location = coord;
        this.spaceType = SpaceType.COM;
    }

    public boolean is_accessible(){
        return this.is_accesible;
    }

    public String toString(){
        return "   ";
    }

    public SpaceType getSpaceType(){
        return this.spaceType;
    }


    public Coordinate getCoordinate() {
        return this.location;
    }
}
