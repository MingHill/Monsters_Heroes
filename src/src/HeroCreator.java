
 /*
  * HeroCreator.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */

import java.io.IOException;
import java.util.List;

public interface HeroCreator {
    List<Hero> createHeroes() throws IOException;
}
