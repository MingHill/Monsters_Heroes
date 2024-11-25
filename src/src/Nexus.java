
 /*
  * Nexus.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


import java.io.IOException;

public class Nexus extends Market {
    public Nexus(Coordinate coord) throws IOException {
        super(coord);
    }

    public String toString(){
        return "N";
    }
}
