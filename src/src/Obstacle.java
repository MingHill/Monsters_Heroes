public class Obstacle extends CommonSpace implements Removable{
    public Obstacle(Coordinate coordinate) {
        super(coordinate);
    }

    public CommonSpace remove(){
        // changes Obstacle to Commonspace
        return new CommonSpace(this.getCoordinate());
    }

    public String toString(){
        // need to add logic to this later
        return "O";
    }
}
