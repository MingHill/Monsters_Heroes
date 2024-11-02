public enum HeroType {
    WAR("Warrior", new float[]{1.05f, 1.05f, 1.0f}),
    PAL("Paladin", new float[]{1.05f, 1.0f, 1.05f}),
    SOR("Sorcerer", new float[]{1.0f, 1.05f, 1.05f});
    // {strength, agility, dexterity}


    private final String name;
    private final float[] favors;
    HeroType(String name, float[] favors) {
        this.name = name;
        this.favors = favors;
    }

    public String getName() {
        return name;
    }

    public float[] getFavors() {
        return favors;
    }
}
