import java.util.Random;

public class Dice {

    public static int[][] setRandomTiles (int size){
        int[][] tiles = new int[size][size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 && j == 0){
                    tiles[i][j] = -1;
                    continue;
                }
                int randomNum = rand.nextInt(10) + 1;
                tiles[i][j] = randomNum;
            }
        }
        return tiles;
    }

    public static boolean dogde(float dodgeChance){
        Random rand = new Random();
        float randomNum = rand.nextFloat() * 10.0f;
        if (randomNum < dodgeChance){
            return true;
        }else{
            return false;
        }
    }

    // 50% chance of meeting a monster
    public static boolean meetMonster(){
        Random rand = new Random();
        int randomNum = rand.nextInt(10) + 1;
        return randomNum < 5;
    }
}
