
 /*
  * CommonSpace.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


public class CommonSpace implements Space{
    private boolean is_accesible;
    private Coordinate location;
    protected SpaceType spaceType;

    public CommonSpace(Coordinate coord){
        this.is_accesible = true;
        this.location = coord;
        this.spaceType = SpaceType.COM;
    }

    public boolean is_accessible(){
        return this.is_accesible;
    }

    public String toString(){
        return "C";
    }

    public SpaceType getSpaceType(){
        return this.spaceType;
    }

    public Coordinate getCoordinate() {
        return this.location;
    }

    protected void changeSpaceType(SpaceType newSpaceType){
        this.spaceType = newSpaceType;
    }


}
