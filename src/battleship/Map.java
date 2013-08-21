package battleship;

/**
 * The map-object. Contain a size^2 matrix with chars representing the objects on the map.
 * Lathund;
 * Empty/unexplored square = _
 * Ship = #
 * Square adjacent to ship = o (should be removed right before start - used for ship-placement 'n checks before the game)
 * Hit squares:
 * With ship/sunken ship = X
 * Empty square/miss = ~
 * 
 * @author Victor, Wiktor
 *
 */
public class Map {
	
	private int size; // for getting current mapsize
	
	
	// the drawable map, contain the symbols representing the object in the squares 
	private char[][] map;
	
	/**
	 * TODO: Is/Should this be used for something?
	 * @author Victor,Wiktor
	 *
	 */
	public static class MapSize {
		public static int LARGE = 10;
		public static int MEDIUM = 5;
		public static int SMALL = 2;
	}

	/**
	 * Constructor
	 * @param size
	 */
	public Map(int size) {
		super(); //<--TODO: needed? 
		map = createMap(size);
		
	}
	public char[][] getMap(){
		return map;
	}
	/**
	 * Draw the ascii representation of THIS map in the console. Same as "map.drawMap(map.getMap());"
	 */
	public void drawSetupMap(){
		drawSetupMap(this.map);
	}
	
	/**
	 * draws the ascii representation of a given map in the console
	 */
	public void drawSetupMap(char[][] map) {
		
		System.out.println("x\\y");
		System.out.print(" ");
		for (int i =0; i < map.length;i++) {
			System.out.print(" "+i);
		}
		System.out.println();
		
		for (int i = 0; i < map.length; i++) {
			System.out.print(i);
			for (int j = 0; j < map.length; j++) {
				System.out.print("|"+map[i][j]);
			}
			System.out.println("|");
		}
	}
	
	/**
	 * Draw the in-game ascii representation of this map as an opponent should see it.
	 */
	public void drawGameMap(){
		drawGameMap(this.map);
	}
	
	/**
	 * Draws the in-game ascii representation of the given map as an opponent should see it.
	 * @param map
	 */
	public void drawGameMap(char[][] map) {
	
		System.out.println("x\\y");
		System.out.print(" ");
		for (int i =0; i < map.length;i++) {
			System.out.print(" "+i);
		}
		System.out.println();
		
		for (int i = 0; i < map.length; i++) {
			System.out.print(i);
			for (int j = 0; j < map.length; j++) {
				System.out.print("|");
				switch (map[i][j]) {
				case '_': //if unexplored, empty square
					System.out.print('_');
					break;
				case '#': //if unexplored square with ship
					System.out.print('_');
					break;
				case  'o'://if unexplored, empty square adjacent to ship
					System.out.print('_');
					break;
				case '~': //if explored empty square
					System.out.print('~');
					break;
				case 'X': //if explored square with (sunken) ship
					System.out.print('X');
					break;
				default: //else... should not happen
					System.out.print(/*'E'*/map[i][j]);
					break;
				}
			}
			System.out.println("|");
		}
	}

	/**
	 * Create the map matrix with specific size. All fields are initially filled with '_':s
	 * @param size of the map (length of the sides)
	 * @return
	 */
	private char[][] createMap(int size) {
		char[][] map = new char[10][10];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = '_';
			}
		}
		this.size = size;
		return map;
	}
	
	/**
	 * Set a ship part on a square (as #) and calls setNeighbour for its neighbors. Should not be called upon without checking weather the squares are occupied beforehand.
	 */
	public void setShipOnSquare(int i, int j){
		//The square
		map[i][j] = '#';
		//The neighbors
		if(i>0){
			setNeigbour(i-1, j);
		}
		if(i<map.length){
			setNeigbour(i+1, j);
		}
		if(j>0){
			setNeigbour(i, j-1);
		}
		if(j<map.length){
			setNeigbour(i, j+1);
		}
	}
	
	/**
	 * Sets a (empty) square as 'o' (= a neighbor to a ship)
	 * @param the coordinate
	 */
	private void setNeigbour(int i, int j){
		if(i<map.length & i>=0){
			if(j<map.length & j>=0){
				if (map[i][j] != '#'){
				map[i][j] = 'o';
				}
			}
		}
	}
	
	/**
	 * Function that checks weather a ship of a given length can be placed on a square (squares not taken and ship is on the field) 
	 * @param Coordinate c - the starting point
	 * @param int direction - 0 = horizontal, 1 = vertical
	 * @param int length - the length of the ship that will be placed
	 * @return true if the ship can be placed
	 */
	public boolean checkIfPlacable(Coordinate c, int direction, int length){
		
		if(direction == 0){ /*if horizontal*/
			for (int i = 0; i < length; i++) {
				if (!ifSquareFree(c.getX()+i, c.getY())) {
					return false;
				}	
			}
			return true;
		}
		if(direction == 1){ /*if vertical*/
			for (int i = 0; i < length; i++) {
				if (!ifSquareFree(c.getX(), c.getY()+i)) {
					return false;
				}	
			}
			return true;
		}
		else{
			/*should not happen, but just to be safe - return false*/
			return false;
		}
	}
	/**
	 * returns true if map[x][y] is NOT a ship NOR a neighbor to a ship (neither # nor o) NOR outside the map
	 * @param x coordinate of the square
	 * @param y coordinate of the square
	 * @return
	 */
	private boolean ifSquareFree(int x, int y){
		if(x<0 || x>=map.length){//if x out of bounds
			return false;
		}
		if(y<0 || y>= map.length){//if y out of bounds
			return false;
		}
		if(map[x][y]=='o' || map[x][y]=='#'){//if (x,y) not free 
			return false;
		}
		//else
		return true; 
	}
	
	/**
	 * return true if (x,y) on the map and map[x][y] has not been hit before 
	 * @param x - the x-coordinate
	 * @param y - the y-coordinate
	 * @return true if move is valid
	 */
	private boolean isSquareNotHit(int x, int y){
		if(x<0 || x>=map.length){//if x out of bounds
			return false;
		}
		if(y<0 || y>= map.length){//if y out of bounds
			return false;
		}
		if(map[x][y]=='~' || map[x][y]=='X'){//if (x,y) has been hit already
			return false;
		}
		
		return true;
	}
	
	/**
	 * returns true if the give coordinate is on the board and not hit before.
	 * @param c - the coordinate
	 * @return true if the coordinate is a valid move and has not been hit before
	 */
	public boolean isSquareNotHit(Coordinate c){
		return isSquareNotHit(c.getX(), c.getY());
	}
	
	/**
	 * returns the size of current map
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Sets the map to be ready for the game by removing the "neighbor"-symbols (o:s) from the map 
	 */
	public void setForPlay(){
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map.length; y++) {
				if(map[x][y] == 'o'){
					map[x][y] = '_';
				}
			}
		}
	}
	
	/**
	 * set the c-coordinate of the map to hit. if there's a ship there, sink it, otherwise set it to a miss.
	 * @param c
	 */
	public void setHit(Coordinate c){
		int x = c.getX();
		int y = c.getY();
		System.out.println(map[x][y]);
		
		if(map[x][y] == '#'){ //if it's a square with a ship
			map[x][y]='X'; 
		}
		else if(map[x][y]=='_' || map[x][y]=='o'){ //if it's an empty square
			map[x][y] ='~';
		}		
		
		else
			map[x][y] = 'E';//TODO: should not happen... throw error?
	}
}