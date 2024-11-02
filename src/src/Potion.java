public class Potion implements Item{
    private String name;
    private int price;
    private int level;
    private float effect_amount;
    private boolean in_stock;
    private PotionType type;

    public Potion(String name, int price, int level, float effect_amount, boolean in_stock, PotionType type){
        this.name = name;
        this.price = price;
        this.level = level;
        this.effect_amount = effect_amount;
        this.in_stock = in_stock;
        this.type = type;
    }
}
