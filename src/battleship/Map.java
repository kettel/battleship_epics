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
	
	/**
	 * draws the map in the console
	 * @param map
	 * @param fleetCoordinates
	 */
	public void drawMap(char[][] map,HashSet<Coordinate> fleetCoordinates) {
		
		//TODO: For bugcheck - prints the coordinates that's in the fleet
		for (Coordinate coordinate : fleetCoordinates) {
					System.out.println(coordinate.getX() +","+ coordinate.getY());
				}
		
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
	 * Set a square on the map to be taken (as #). Should not be called upon without checking weather the squares are occupied before.
	 */
	public void setSquareTaken(int i, int j){
		
		map[i][j] = '#';
		
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
	private void setNeigbour(int i, int j){
		if (map[i][j] != '#'){
			map[i][j] = 'o';
		}
	}
}