public class GameMap {
    private Space[][] map;
    private int size;

    public GameMap(int size, Party party) {
        this.size = size;
        this.map = initializeBoard(party);
    }

    private Space[][] initializeBoard(Party party) {
        int[][] space_markers = Dice.setRandomTiles(this.getSize());
        Space[][] new_map = new Space[this.getSize()][this.getSize()];

        for(int r = 0; r < this.getSize(); r++){
            for(int c = 0; c < this.getSize(); c++){
                if (r == 0 && c == 0){
                    new_map[r][c] = party;
                    continue;
                }
                Coordinate coord = new Coordinate(r, c);
                if (space_markers[r][c] <= 2){
                    new_map[r][c] = new InaccessibleSpace(coord);

                }else if (space_markers[r][c] <= 4){
                    new_map[r][c] = new Market(coord);

                }else{
                    new_map[r][c] = new CommonSpace(coord);
                }
            }
        }
        return new_map;
    }

    public int getSize(){
        return this.size;
    }

    public String toString(){
        String line = "+-------------------------------+";

        String output = "";
        for (int r = 0; r < this.getSize(); r++){
            output += line + "\n";
            for (int c = 0; c < this.getSize(); c++){
                output += "|" + map[r][c].toString();
            }
            output += "| \n";
        }
        output += line + "\n";
        return output;
    }


    
}
