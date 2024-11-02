import java.util.Random;

public class Dice {

    public static int[][] setRandomTiles (int size){
        int[][] tiles = new int[size][size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int randomNum = rand.nextInt(10) + 1;
                tiles[i][j] = randomNum;
            }
        }
        return tiles;
    }

}
