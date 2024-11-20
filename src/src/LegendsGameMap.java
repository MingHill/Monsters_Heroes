import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LegendsGameMap extends GameMap {

    private Monsters monsters;

    public LegendsGameMap(int size, Party party, Monsters monsters) throws IOException {
        super(size, party);
	this.monsters = monsters;
	spawnMonsters(1);
	spawnMonsters(1);
	spawnMonsters(1);
    }

    private void spawnMonsters(int level) throws IOException {
	for (int i = 0; i <= 7 ; i += 3) {

	    Monster monster = Fetch.fetchMonster(level);

	    // spawn a monster in either the first or second space of each column
	    if (this.map[this.size - 1][i][2] == null) {

		monster.setCoordinate(new Coordinate(this.size - 1, i));
		this.map[this.size - 1][i][2] = monster;
		this.monsters.add(monster);

	    } else if (this.map[this.size - 1][i + 1][2] == null) {

		monster.setCoordinate(new Coordinate(this.size - 1, i + 1));
		this.map[this.size - 1][i + 1][2] = monster;
		this.monsters.add(monster);

	    }
	}
    }


    public void moveMonster(Monster monster) {
	int row = monster.getCoordinate().getRow();
	int col = monster.getCoordinate().getCol();

	monster.getCoordinate().setCoordinate(row - 1, col);
	this.map[row][col][2] = null;
	this.map[row - 1][col][2] = monster;
    }


    protected Space[][][] initializeBoard() throws IOException {
        Space[][][] new_map = new Space[size][size][3];
        int[][] space_markers = Dice.setRandomTiles(this.getSize());

        int hero_index = 0;
	List<Hero> heroList = new ArrayList<>(this.party.getHeroes().values());
        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                Coordinate coord = new Coordinate(r, c);
                if (c % 3 == 2){
                    // dividers
                    new_map[r][c][0] = new InaccessibleSpace(coord);
                    continue;
                }
                if (r == 0 || r == size - 1) {
                    // nexus and hero/monster initialization
                    if (r == 0 && c % 2 == 0){
                        Hero hero = heroList.get(hero_index);
                        hero.setCoordinate(coord);
                        new_map[r][c][1] = hero;
                        hero_index++;
                    }
                    // if (r == size - 1 && c % 2 == 0){
                    //     // need to complete this section to fetch a monster
                    //     // Monster monter = new Monster();
                    //     // monter.setCoordinate(coord);
                    // }
                    new_map[r][c][0] = new Nexus(coord);
                    continue;
                }
                // random spot generation
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
	String line = "+---";
        String fullLine = "";
        for(int r = 0; r < this.getSize(); r++){
            fullLine += line;
        }
        fullLine += "+";

        String output = "";
        for (int r = 0; r < this.getSize(); r++){
            output += fullLine + "\n";
            for (int c = 0; c < this.getSize(); c++){
                output += "|";
		output += this.map[r][c][0].toString();
                if (this.map[r][c][1] != null){
                    output += " H";
                }
		if (this.map[r][c][2] != null){
                    output += " M";
                }
            }
            output += "| \n";
        }
        output += fullLine + "\n";
        return output;
    }

}
