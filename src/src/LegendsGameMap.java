import java.io.IOException;

public class LegendsGameMap extends GameMap {
    private Space[][][] map;
    private int size;
    private Party party;
    public LegendsGameMap(int size, Party party) throws IOException {
        super(size, party);
    }

    protected Space[][][] initializeBoard(Party party) throws IOException {
        Space[][][] new_map = new Space[size][size][2];
        return new_map;
    }

    public String toString(){
        return "";
    }

}
