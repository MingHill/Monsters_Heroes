import java.io.IOException;

public abstract class GameMap {
    protected int size;
    protected Party party;
    protected Space[][][] map;

    public GameMap(int size, Party party) throws IOException {
        this.size = size;
        this.party = party;
        this.map = initializeBoard(party);
    }

    protected abstract Space[][][] initializeBoard(Party party) throws IOException;

    public Space getSpace(int r, int c){
        return this.map[r][c][0];
    }

    public int getSize(){return this.size;}

    public abstract String toString();
}
