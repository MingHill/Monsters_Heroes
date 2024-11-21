import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LegendsGameMap extends GameMap {

    private Monsters monsters;

    public LegendsGameMap(int size, Party party, Monsters monsters) throws IOException {
        super(size, party);
        this.monsters = monsters;
        spawnMonsters(1);
    }

    public void spawnMonsters(int level) throws IOException {
        for (int i = 0; i < this.getSize() ; i += 3) {
            Monster monster = Fetch.fetchMonster(level); // gets single monster

	    // spawns a monster in each lane if there isn't already a monster
	    // in that space
	    if (this.map[this.getSize() - 1][i][2] == null) {

                monster.setCoordinate(new Coordinate(this.getSize() - 1, i));
                this.map[this.size - 1][i][2] = monster;
                this.monsters.add(monster);
	    }
	}

    }


    public void moveMonster(Monster monster) {
        // moves a given monster down 1 row
        int row = monster.getCoordinate().getRow();
        int col = monster.getCoordinate().getCol();

        monster.getCoordinate().setCoordinate(row - 1, col);
        this.map[row][col][2] = null;
        this.map[row - 1][col][2] = monster;
    }

    public void moveAllMonsters() {
        // moves all monsters
        for (Monster monster: this.monsters.getAll()){
            moveMonster(monster);
        }
    }


    protected Space[][][] initializeBoard() throws IOException {
        Space[][][] new_map = new Space[this.getSize()][this.getSize()][3];
        int[][] space_markers = Dice.setRandomLegends(this.getSize());

        int hero_index = 0;

        // get list of heroes
	    List<Hero> heroList = new ArrayList<>(this.party.getHeroes().values());

        for(int r = 0; r < size; r++) {
            for(int c = 0; c < size; c++) {
                Coordinate coord = new Coordinate(r, c);
                if (c % 3 == 2){
                    // inaccesible spots
                    new_map[r][c][0] = new InaccessibleSpace(coord);
                    continue;
                }
                if (r == 0 || r == size - 1) {
                    // nexus and hero/monster initialization
                    if (r == 0 && c % 2 == 0){
                        // set hero
                        Hero hero = heroList.get(hero_index);
                        hero.setCoordinate(coord);
                        new_map[r][c][1] = hero;
                        hero_index++;
                    }
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
        // need to fix toString
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

    public boolean moveHero(int direction, Hero hero){
        // moves a given hero any direction
        int row = hero.getCoordinate().getRow();
        int col = hero.getCoordinate().getCol();
        int newRow = 0;
        int newCol = 0;
        Coordinate newCoord;

        switch (direction){
            case 0: // left (A)
                newRow = row;
                newCol = col - 1;
                break;
            case 1: // right(D)
                newRow = row;
                newCol = col + 1;
                break;
            case 2: // up (W)
                newRow = row - 1;
                newCol = col;
                break;
            case 3: // down (S)
                newRow = row + 1;
                newCol = col;
                break;
        }

        if (newRow < 0 || newRow >= this.getSize() || newCol < 0 || newCol >= this.getSize()){
            // checks if the selected position is out of bounds
            System.out.println("Invalid Coordinate on a map");
            return false;
        }

        if(!this.map[newRow][newCol][0].is_accessible()){
            // checks if the selected position is accessible or not
            System.out.println("You can't enter this space, it is a body of water ~~~~");
            System.out.println("Please choose another direction");
            return false;
        }

        if(this.map[newRow][newCol][0].getSpaceType() == SpaceType.OBS){
            // if selected spot is of type obstacle, obstacle will be removed and set as commonSpace
            Obstacle ob = (Obstacle)this.getSpace(newRow, newCol);
            this.map[newRow][newCol][0] = ob.remove();
            System.out.println(hero.getName() + " has removed a obstacle");
            return true;
        }

        // moves hero to new position
        this.map[row][col][1] = null;
        hero.getCoordinate().setCoordinate(newRow, newCol);
        this.map[newRow][newCol][1] = hero;
        return true;
    }
}
