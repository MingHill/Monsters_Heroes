public enum SpellType {
    ICE("Ice Spell", 50),
    FIR("Fire Spell", 10),
    LGT("Lighting Spell", 20);


    private final String name;
    private final int effectAmount;
    SpellType(String name, int effectAmount) {
        this.name = name;
        this.effectAmount = effectAmount;
    }

    public String getName() {
        return name;
    }

    public int getEffectAmount() {
        return effectAmount;
    }
}
