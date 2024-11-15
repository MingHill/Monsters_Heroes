import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LegendsGameMap extends GameMap {
    private Space[][][] map;
    private int size;
    private Party party;
    public LegendsGameMap(int size, Party party) throws IOException {
        super(size, party);
    }

    protected Space[][][] initializeBoard(Party party) throws IOException {
        Space[][][] new_map = new Space[size][size][2];
        int[][] space_markers = Dice.setRandomTiles(this.getSize());
        List<Hero> heroList = new ArrayList<>(this.party.getHeroes().values());
        int hero_index = 0;
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                Coordinate coord = new Coordinate(r, c);
                if (c % 3 == 2){
                    new_map[r][c][0] = new InaccessibleSpace(coord);
                    continue;
                }
                if (r == 0 || r == size - 1) {
                    if (r == 0 && c % 2 == 0){
                        Hero hero = heroList.get(hero_index);
                        hero.setCoordinate(coord);
                        new_map[r][c][1] = hero;
                        hero_index++;
                    }
                    if (r == size - 1 && c % 2 == 0){
                        // need to complete this section
                        Monster monster
                    }
                    new_map[r][c][0] = new Nexus(coord);
                    continue;
                }
                if (space_markers[r][c] < 2){
                    new_map[r][c][0] = new Bush(coord);
                }else if(space_markers[r][c] < 4){
                    new_map[r][c][0] = new Cave(coord);
                }else if(space_markers[r][c] < 6){
                    new_map[r][c][0] = new Koulou(coord);
                } else if (space_markers[r][c] < 7) {
                    new_map[r][c][0] = new Obstacle(coord);
                }else{
                    new_map[r][c][0] = new CommonSpace(coord);
                }
            }
        }
        return new_map;
    }

    public String toString(){
        return "";
    }

}
