public class Potion implements Item{
    private String name;
    private int price;
    private int level;
    private float effect_amount;
    private boolean in_stock;
    private PotionType type;
    private ItemType itemType;

    public Potion(String name, int price, int level, float effect_amount, PotionType type){
        this.name = name;
        this.price = price;
        this.level = level;
        this.effect_amount = effect_amount;
        this.in_stock = true;
        this.type = type;
        this.itemType = ItemType.POT;
    }

    public String getName() {
        return this.name;
    }

    public String toString(){
        String output = "";
        output += "Potion(\n Name: " + this.name + "Potion Type: " + this.type.getName() + "\n Price: " + this.price + " \n Level: " + this.level + " \n Effect Amount: " + this.effect_amount + " )\n";

        return output;
    }
    public int getLevel(){
        return this.level;
    }
    public int getPrice(){return this.price;}
    public ItemType getItemType(){
         return this.itemType;
    }
}
