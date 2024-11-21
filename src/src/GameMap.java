import java.io.IOException;

public abstract class GameMap {
    protected int size;
    protected Party party;
    protected Space[][][] map;

    public GameMap(int size, Party party) throws IOException {
        this.size = size;
	    this.party = party;
        this.map = initializeBoard();
    }

    protected abstract Space[][][] initializeBoard() throws IOException;

    public Space getSpace(int r, int c){
        return this.map[r][c][0];
    }

    public int getSize(){return this.size;}

    public boolean moveDirection(int direction){return false;}

    public abstract String toString();

    public Party getParty(){return this.party;}

    public boolean moveHero(int direction, Hero hero){
        return false;
    }

}
