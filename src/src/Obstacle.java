public class Obstacle extends CommonSpace implements Removable{
    public Obstacle(Coordinate coordinate) {
        super(coordinate);
    }

    public CommonSpace remove(){
        // need to add logic to this --> return a commonspace
        return new CommonSpace(this.getCoordinate());
    }

    public String toString(){
        // need to add logic to this later
        return "";
    }
}
