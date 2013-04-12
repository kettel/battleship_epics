package battleship;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Map {
	
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
	public void drawMap(char[][] map,HashSet<Coordinate> fleetCoordinates) {
		
		
		for (Coordinate coordinate : fleetCoordinates) {
					System.out.println(coordinate.getX() +","+ coordinate.getY());
				}
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				
				
				if (fleetCoordinates.contains(new Coordinate(i, j))) {
					System.out.print("|*");
				} else {
					System.out.print("|"+map[i][j]);
				}
				
			}
			System.out.println("|");
		}
	}
//	public void drawMap(Player p){
//		Collection<HashSet<Coordinate>> fleetCoords = p.getCoordset();
//		
//		
//		for (int i = 0; i < map.length; i++) {
//			for (int j = 0; j < map.length; j++) {
//				System.out.print("|");
//				//ifsats... typ
//				if(fleetCoords.getCoordinate(i,j)==true){
//					
//				}
//				else{
//					
//				}
//				//slut
//			}
//			System.out.println("|");
//		}
//	}

	private char[][] createMap(int size) {
		char[][] map = new char[10][10];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = '_';
			}
			
		}
		return map;
	}
}
