public enum PotionType {
    HP("Health Potion", 200),
    MP("Mana Potion", 200),
    STR("Strength Potion", 200),
    DEX("Dexterity Potion", 200),
    AGI("Agility Potion", 200),
    MER("Mermaid Potion", 100),
    AMB("Ambrosia Potion", 50);


    private final String name;
    private final int effectAmount;
    PotionType(String name, int effectAmount) {
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
