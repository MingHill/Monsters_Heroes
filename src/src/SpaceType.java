public enum SpaceType {
    IN("Inaccesible Space"),
    MAR("Market Space"),
    COM("Common Space"),
    PAR("Party Space");

    private final String name;
    SpaceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
