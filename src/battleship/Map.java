package battleship;

import java.util.HashSet;

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
	
	public static class MapSize {
		public static int LARGE = 10;
		public static int MEDIUM = 5;
		public static int SMALL = 2;
	}

	public Map(int size) {
		super();
		map = createMap(size);
		
	}
	public char[][] getMap(){
		return map;
	}
	
	/**
	 * draws the ascii representation of the map in the console
	 * 
	 * TODO: Removed the in-parameters of this function. fleetCoordinates weren't used and the class already has access to its map.
	 * 		Leaved them there as comments if we wanted them later. 
	 */
	public void drawMap(/*char[][] map,HashSet<Coordinate> fleetCoordinates*/) {
		
		// For bugchecking - the loop-part below prints the coordinates that's in the fleet
		/*for (Coordinate coordinate : fleetCoordinates) {
					System.out.println(coordinate.getX() +","+ coordinate.getY());
				}*/
		
		
		System.out.println("y\\x");
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
	 * Create the map matrix with specific size. All fields are initially filled with '_'
	 * @param size of the map (lenght of the sides)
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
		//The neihgbours
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
	 * Sets a (empty square) as o = a neighbor to a ship
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
		
		if(direction == 0){ /*horizontal*/
			for (int i = 0; i < length; i++) {
				if (!ifSquareFree(c.getX()+i, c.getY())) {
					return false;
				}	
			}
			return true;
		}
		if(direction == 1){ /*vertical*/
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
	 * @param x
	 * @param y
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
}