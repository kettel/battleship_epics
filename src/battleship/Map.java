package battleship;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Map {
	
	private char[][] map;
	boolean squareIsHit;
	
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
	public void drawMap(char[][] map,HashSet<Coordinate> fleetCoordinates) {
		
		
		for (Coordinate coordinate : fleetCoordinates) {
					System.out.println(coordinate.getX() +","+ coordinate.getY());
				}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print("|"+map[i][j]);
			}
			System.out.println("|");
		}
	}

	private char[][] createMap(int size) {
		char[][] map = new char[10][10];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = '_';
			}
			
		}
		return map;
	}
	/**
	 * Set a square on the map to be either taken *
	 *
	 */
	public void setSquare(int i, int j){
		map[i][j] = '*';
	}
}