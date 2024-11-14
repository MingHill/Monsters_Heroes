import java.io.IOException;

public class MonsterGameMap extends GameMap {
    private Space[][][] map;
    private int size;
    private Party party;

    public MonsterGameMap(int size, Party party) throws IOException {
        super(size, party);
    }

    protected Space[][][] initializeBoard(Party party) throws IOException {
        int[][] space_markers = Dice.setRandomTiles(this.getSize());
        Space[][][] new_map = new Space[this.getSize()][this.getSize()][2];

        for(int r = 0; r < this.getSize(); r++){
            for(int c = 0; c < this.getSize(); c++){
                Coordinate coord = new Coordinate(r, c);
                if (r == 0 && c == 0){
                    new_map[r][c][1] = party;
                    new_map[r][c][0] = new CommonSpace(coord);
                    continue;
                }
                if (space_markers[r][c] <= 1){
                    new_map[r][c][0] = new InaccessibleSpace(coord);
                    new_map[r][c][1] = null;

                }else if (space_markers[r][c] <= 3){
                    new_map[r][c][0] = new Market(coord);
                    new_map[r][c][1] = null;

                }else{
                    new_map[r][c][0] = new CommonSpace(coord);
                    new_map[r][c][1] = null;
                }
            }
        }
        return new_map;
    }

    public Space getSpace(int r, int c){
        return this.map[r][c][0];
    }


    public boolean moveDirection(int direction){ // need to fix based on movement options
        int row = this.party.coordinate.getRow();
        int col = this.party.coordinate.getCol();
        int newRow = 0;
        int newCol = 0;
        Coordinate newCoord;

        switch (direction){
            case 0: // left (A)
                newRow = row;
                newCol = col - 1;
                break;
            case 1: // right(D)
                newRow = row;
                newCol = col + 1;
                break;
            case 2: // up (W)
                newRow = row - 1;
                newCol = col;
                break;
            case 3: // down (S)
                newRow = row + 1;
                newCol = col;
                break;
        }

        if (newRow < 0 || newRow >= this.getSize() || newCol < 0 || newCol >= this.getSize()){
            System.out.println("Invalid Coordinate on a map");
            return false;
        }

        if(!this.map[newRow][newCol][0].is_accessible()){
            System.out.println("You can't enter this space, it is a body of water ~~~~");
            System.out.println("Please choose another direction");
            return false;
        }
        this.map[row][col][1] = null;
        this.party.coordinate.setCoordinate(newRow, newCol);
        this.map[newRow][newCol][1] = party;
        return true;
    }

    public String toString(){
        String line = "+---";
        String fullLine = "";
        for(int r = 0; r < this.getSize(); r++){
            fullLine += line;
        }
        fullLine += "+";

        String output = "";
        for (int r = 0; r < this.getSize(); r++){
            output += fullLine + "\n";
            for (int c = 0; c < this.getSize(); c++){
                output += "|";
                if (this.map[r][c][1] != null){
                    output += this.map[r][c][1].toString();
                }else{
                    output += this.map[r][c][0].toString();
                }
            }
            output += "| \n";
        }
        output += fullLine + "\n";
        return output;
    }

//    public void setSpace(Space space){
//        int row = space.getCoordinate().getRow();
//        int col = space.getCoordinate().getCol();
//        this.map[row][col] = space;
//    }

}
