public class GameMap {
    private Space[][] map;
    private int size;

    public GameMap(int size) {
        this.size = size;
        this.map = initializeBoard();
    }

    private Space[][] initializeBoard(){

        int[][] space_markers = Dice.setRandomTiles(this.getSize());
        Space[][] new_map = new Space[this.getSize()][this.getSize()];

        for(int r = 0; r < this.getSize(); r++){
            for(int c = 0; c < this.getSize(); c++){
                Coordinate coord = new Coordinate(r, c);
                if (space_markers[r][c] <= 2){
                    new_map[r][c] = new InaccessibleSpace(coord);

                }else if (space_markers[r][c] <= 5){
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
        String lineBuilder = "+---------------+";
        String line = "";
        for (int i = 0; i < this.getSize(); i++){
            line += lineBuilder + "|";
        }
        String lineBuilder2 = "|               |";
        String line2 = "";
        for (int i = 0; i < this.getSize(); i++){
            lineBuilder2 += line2;
        }
        String output = "";
        for

    }


    
}
