
 /*
  * Coordinate.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


import java.util.Objects;
// src.Coordinate class which represents location on the board
public class Coordinate {
    private int row;
    private int col;
    public Coordinate(int r, int c){
        setCoordinate(r, c);
    }

    public Coordinate(int tileNumber, int boardCols, int StartIndex){
        tileNumber -= StartIndex;
        int r = tileNumber / boardCols;
        int c = tileNumber % boardCols;
        setCoordinate(r, c);
    }

    public String toString(){
        return "Row: " + this.row + " Col: " + this.col;
    }


    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        Coordinate other = (Coordinate) obj;// Check if they are the same reference
        return this.row == other.row && this.col == other.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    public void setCoordinate(int r, int c){
        this.row = r;
        this.col = c;
    }

    // processes a coordinate string and returns a coordinate object
    // May not need
    public static Coordinate processCoordinate(String coord){
        String[] parts = coord.split(",");
        int r = Integer.parseInt(parts[0]) - 1;
        int c = Integer.parseInt(parts[1]) - 1;
        return new Coordinate(r, c);
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;
    }

}
