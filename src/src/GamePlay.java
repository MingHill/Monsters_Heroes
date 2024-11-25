
 /*
  * GamePlay.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * This interface is flexible enough to handle any sort of turn-based game, as
  * it only requires a makeMove() method, which handles a single turn of play
  *
  * Credits: All code is our own.
  */


import java.io.IOException;

public interface GamePlay {
    boolean makeMove() throws IOException;
}
