
 /*
  * Dice.java
  * by Ming Hill (minghill@bu.edu) and Tanner Bangerter (tanner@bu.edu)
  * 11/25/2024
  *
  * Credits: All code is our own.
  */


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

    public static int[][] setRandomLegends(int size){
        int[][] legendTiles = new int[size][size];
        Random rand = new Random();

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (c % 3 == 2){
                    // Inaccesible spots
                    legendTiles[r][c] = -1;
                    continue;
                }
                if (r == 0 || r == size - 1){
                    // NEXUS spots so no need to have special tiles
                    legendTiles[r][c] = -1;
                    continue;
                }
                int randomNum = rand.nextInt(10) + 1;
                legendTiles[r][c] = randomNum;
            }
        }
        return legendTiles;
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
