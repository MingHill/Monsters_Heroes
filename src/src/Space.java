public interface Space {
    boolean is_accessible();
    SpaceType getSpaceType();
    Coordinate getCoordinate();
}
