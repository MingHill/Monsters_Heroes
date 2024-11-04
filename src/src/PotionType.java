public enum PotionType {
    HP("Health Potion", 50),
    MP("Mana Potion", 10),
    STR("Strength Potion", 20),
    DEX("Dexterity Potion", 10),
    AGI("Agility Potion", 10),
    MER("Mermaid Potion", 10),
    AMB("Ambrosia Potion", 10);


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
