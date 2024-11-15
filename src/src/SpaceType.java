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
