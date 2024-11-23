public class InaccessibleSpace implements Space{
    private boolean is_accessible;
    private Coordinate location;
    private final SpaceType space_type;

    public InaccessibleSpace(Coordinate coord){
        this.is_accessible = false;
        this.location = coord;
        this.space_type = SpaceType.IN;
    }

    public boolean is_accessible(){
        return this.is_accessible;
    }

    public String toString(){
        return "~";
    }

    public SpaceType getSpaceType(){
        return this.space_type;
    }

    public Coordinate getCoordinate(){
        return this.location;
    }
}
