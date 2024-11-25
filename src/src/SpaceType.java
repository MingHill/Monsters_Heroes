
 /*
  * SpaceType.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


public enum SpaceType {
    IN("Inaccesible Space"),
    MAR("Market Space"),
    COM("Common Space"),
    PAR("Party Space"),
    BUSH("Bush Space"),
    KOU("Koulou Space"),
    CAVE("Cave Space"),
    OBS("Obstacle Space"),
    HERO("Hero Space"),
    MON("Monster Space");

    private final String name;
    SpaceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
