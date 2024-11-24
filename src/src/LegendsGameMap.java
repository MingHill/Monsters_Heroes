import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LegendsGameMap extends GameMap {

    private Monsters monsters;

    public LegendsGameMap(int size, Party party) throws IOException {
        super(size, party);
    }

    public void setMonsters(Monsters monsters) throws IOException {
        // instead of having this in the constructor, just calling in on the initialization of GamePlay
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
		    monster.setMonsterID();
                    this.map[this.size - 1][i][2] = monster;
                    this.monsters.add(monster);
            }
	}
    }


    public void moveMonster(Monster monster) {
        // moves a given monster down 1 row
        int row = monster.getCoordinate().getRow();
        int col = monster.getCoordinate().getCol();

	int newRow = row;
	int newCol = col;

	if (this.canMonsterMoveInto(row - 1, col)) {
	    newRow = row - 1;
	} else if (col % 3 == 0 && this.canMonsterMoveInto(row, col + 1)) {
	    newCol = col + 1;
	} else if (col % 3 == 1 && this.canMonsterMoveInto(row, col - 1)) {
	    newCol = col - 1;
	}

        monster.getCoordinate().setCoordinate(newRow, newCol);
        this.map[row][col][2] = null;
        this.map[newRow][newCol][2] = monster;
    }

    private boolean canMonsterMoveInto(int row, int col) {
	return !this.includesMonster(row, col)
	    && !(this.map[row][col][0].getSpaceType() == SpaceType.OBS);
    }

    public void moveAllMonsters() {
        // moves all monsters
        for (Monster monster: this.monsters.getAll()){
            moveMonster(monster);
        }
    }


    public void removeDeadMonster(Monster monster) {
	this.map[monster.getCoordinate().getRow()][monster.getCoordinate().getCol()][2] = null;
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
                    // nexus and hero initialization
                    if (r == 0 && c % 3 == 1){
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
        String output = "";
        for (int r = 0; r < this.getSize(); r++){
            output += printRow(r);
            output += "\n";
        }
        return output;
    }

    private String printRow(int r ){
        String line = "";
        for (int i = 0; i < 3; i++){
            if (i != 1){
                for (int c = 0; c < this.getSize(); c++){
                    line += this.map[r][c][0].toString() + " - " + this.map[r][c][0].toString() + " - " + this.map[r][c][0].toString();
                    line += "  ";
                }
            }else{
                for (int c = 0; c < this.getSize(); c++){
                    // TODO add logic to show monsters/heroes and inaccess spot
                    line += "|";
                    if (c % 3 == 2){
                        line += " XXXXXX | ";
                        continue;
                    }

		    if (this.includesMonster(r, c)) {
			line += " M" + this.getMonster(r, c).getMonsterID() + " ";
		    } else {
			line += "    ";
		    }

		    if (this.includesHero(r, c)) {
			line += "H" + this.getHero(r, c).getHeroID() + " ";
		    } else {
			line += "   ";
		    }

                    line += "|";
                    line += "  ";
                }
            }
            line += "\n";
        }
        return line;
    }

    private Hero getHero(int r, int c){
        return (Hero)this.map[r][c][1];
    }

    private Monster getMonster(int r, int c){
        return (Monster)this.map[r][c][2];
    }

    public boolean includesHero(int r, int c){
        return this.map[r][c][1] != null;
    }

    public boolean includesMonster(int r, int c){
        return this.map[r][c][2] != null;
    }

    public boolean moveHero(int direction, Hero hero){
        // moves a given hero any direction
        int row = hero.getCoordinate().getRow();
        int col = hero.getCoordinate().getCol();
        int newRow = 0;
        int newCol = 0;
        Coordinate newCoord;

        switch (direction){
            case 1: // left (A)
                newRow = row;
                newCol = col - 1;
                break;
            case 2: // right(D)
                newRow = row;
                newCol = col + 1;
                break;
            case 3: // up (W)
                newRow = row - 1;
                newCol = col;
                break;
            case 4: // down (S)
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
	    System.out.println("You can't enter that space!");
            System.out.println("Please choose another action.");
            return false;
        }

	if (this.includesHero(newRow, newCol)) {
	    System.out.println("There's already a hero in that space!");
	    System.out.println("Please choose another action.");
	    return false;
	}

        // TODO make sure hero doesn't go past monster
        if(direction == 4 && !pastMonster(row, col)){
            System.out.println("You can't go past a monster!");
            return false;
        }
        if(this.map[newRow][newCol][0].getSpaceType() == SpaceType.OBS){
            // if selected spot is of type obstacle, obstacle will be removed and set as commonSpace
            Obstacle ob = (Obstacle)this.getSpace(newRow, newCol);
            this.map[newRow][newCol][0] = ob.remove();
            System.out.println(hero.getName() + " has removed a obstacle");
            return true;
        }

        if(this.getSpace(row, col) instanceof SpaceBonus){
            // removes the current bonus
            removeBonus(hero, (SpaceBonus)this.getSpace(row, col));
        }
        // moves hero to new position
        this.map[row][col][1] = null;
        hero.getCoordinate().setCoordinate(newRow, newCol);
        this.map[newRow][newCol][1] = hero;

        if(this.getSpace(newRow, newCol) instanceof SpaceBonus){
            // removes the current bonus
            applyBonus(hero, (SpaceBonus)this.getSpace(newRow, newCol));
        }

        return true;
    }

    private boolean pastMonster(int row, int col){
        // checks if the current heroes coordinate is valid to pass a monster
        if (col % 3 == 0){
            if (this.map[row][col][2] == null && this.map[row][col + 1][2] == null) {
                return true;
            }
        }else if (col % 3 == 1) {
                if(this.map[row][col][2] == null && this.map[row][col - 1][2] == null){
                    return true;
                }
        }
        return false;
    }

    public void recallHero(Hero hero){
        //TODO need to implemenet, call teleport() method
        // Teleports given hero back to its NEXUS
        int heroID = hero.getHeroID(); // 1, 2, 3
        switch (heroID){
            case 1:
                teleport(hero, new Coordinate(0, 0));
                break;
            case 2:
                teleport(hero, new Coordinate(0, 3));
                break;
            case 3:
                teleport(hero, new Coordinate(0, 6));
                break;
        }
    }

    public boolean teleportHero(Hero hero){
        // TODO: need to checking on if coordinate is valid
        // METHOD To select where a hero should teleport to
        Hero selectHero = Input.getTeleport(hero, this.party); // gets hero you want to teleport to

        Coordinate selectedCoord = selectHero.getCoordinate();
        int row = selectedCoord.getRow();
        int col = selectedCoord.getCol();
        Coordinate newCoord;
        if (col % 3 == 0){
            newCoord = new Coordinate(selectedCoord.getRow(), selectedCoord.getCol() + 1);

        }else{
            newCoord = new Coordinate(selectedCoord.getRow(), selectedCoord.getCol() - 1);
        }
        boolean side = validSide(newCoord);
        boolean back = validBehind(selectedCoord);
        Coordinate teleportCoord = null;
        if (side && back){
            // both side and back are available to teleport to
            teleportCoord = Input.chooseTele(selectedCoord, newCoord);
        }else if (side){
            // just side is valid
            teleportCoord = newCoord;
        }else if(back){
            // just back is valid
            teleportCoord = selectedCoord;
        }
        // none is valid
        if (teleportCoord != null){
            teleport(hero, teleportCoord);
            return true;
        }
        return false;

    }

    private boolean validSide(Coordinate coord){
        int row = coord.getRow();
        int col = coord.getCol();
        return this.map[row][col][1] == null && this.map[row][col][0].getSpaceType() != SpaceType.OBS;
    }

    private boolean validBehind(Coordinate coord1){
        int row = coord1.getRow();
        int col = coord1.getCol();
        if (this.map[row - 1][col][1] == null && this.map[row - 1][col][0].getSpaceType() != SpaceType.OBS){
            return true;
        }
        return false;
    }

    private void teleport(Hero hero, Coordinate teleportCoord){
        //TODO need to implement
        // given a hero and coordinate, will teleport a hero to that given coordinate
        Coordinate currentHeroCoord = hero.getCoordinate();
        int row = currentHeroCoord.getRow();
        int col = currentHeroCoord.getCol();

        if(this.getSpace(row, col) instanceof SpaceBonus){
            // removes the current bonus
            removeBonus(hero, (SpaceBonus)this.getSpace(row, col));
        }

        this.map[row][col][1] = null;

        int newRow = teleportCoord.getRow();
        int newCol = teleportCoord.getCol();

        this.map[newRow][newCol][1] = hero;

        if(this.getSpace(newRow, newCol) instanceof SpaceBonus){
            // removes the current bonus
            applyBonus(hero, (SpaceBonus)this.getSpace(row, col));
        }

        hero.setCoordinate(teleportCoord);
    }

    private void applyBonus(Hero hero, SpaceBonus bonus){
        bonus.applyBonus(hero);
    }

    private void removeBonus(Hero hero, SpaceBonus bonus){
        bonus.removeBonus(hero);
    }

}
