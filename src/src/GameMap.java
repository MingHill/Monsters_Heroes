import java.io.IOException;

public abstract class GameMap {
    protected int size;
    protected Space[][][] map;

    public GameMap(int size) throws IOException {
        this.size = size;
        this.map = initializeBoard();
    }

    protected abstract Space[][][] initializeBoard() throws IOException;

    public Space getSpace(int r, int c){
        return this.map[r][c][0];
    }

    public int getSize(){return this.size;}

    public abstract String toString();
}
