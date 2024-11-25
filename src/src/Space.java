
 /*
  * Space.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


public interface Space {
    boolean is_accessible();
    SpaceType getSpaceType();
    Coordinate getCoordinate();
}
