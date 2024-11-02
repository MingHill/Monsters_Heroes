public class CommonSpace implements Space{
    private boolean is_accesible;
    private Coordinate location;

    public CommonSpace(Coordinate coord){
        this.is_accesible = true;
        this.location = coord;
    }

    public boolean is_accesible(){
        return this.is_accesible;
    }

    public String toString(){
        return "CommonSpace";
    }
}
