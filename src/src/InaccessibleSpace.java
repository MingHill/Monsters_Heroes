public class InaccessibleSpace implements Space{
    private boolean is_accessible;
    private Coordinate location;

    public InaccessibleSpace(Coordinate coord){
        this.is_accessible = false;
        this.location = coord;
    }

    public boolean is_accessible(){
        return this.is_accessible;
    }

    public String toString(){
        return "Inaccessible Space";
    }
}
