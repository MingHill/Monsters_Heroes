
 /*
  * MonsterCreator.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */

import java.io.IOException;
import java.util.List;

public interface MonsterCreator {
    List<Monster> createMonsters(int numMonsters, int maxLevel) throws IOException;
}
