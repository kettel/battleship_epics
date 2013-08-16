package battleship;

import java.util.HashMap;
import java.util.HashSet;

/**
 * The super player-class. Should be concerned with "behind the scenes"-stuff.
 * Check if placement is valid, check if a ship is hit or not, independent of where the coordinates came from.
 *  
 * @author Victor, Wiktor
 *
 */
public abstract class Player {
	protected HashMap<Integer, HashSet<Coordinate>> fleet = new HashMap<Integer, HashSet<Coordinate>>();
	public static class FleetType {
		public static int CLASSIC = 5;
	}

	/**
	 *  Constructor
	 * @param type - TODO: vad g�r den
	 */
	public Player(int type) {		
	}
	
	public HashSet<Coordinate> getCoordinates(){
		HashSet<Coordinate> returnSet = new HashSet<Coordinate>();
		for (Integer ship : fleet.keySet()) {
			returnSet.addAll(fleet.get(ship));
		}
		return returnSet;
	}
	
	
	/**
	 * Places the ships on the map. Human and computer uses different methods of doing so.
	 * @param map
	 */
	public abstract void placeShips(Map map);
	
	/**
	 * function that checks if a ship can be placed at a specified point, in a specific direction, and if it can - place it there.
	 * Returns false if the ship can't be placed there and true if it can (and has been). 
	 * @param map - the map of the player
	 * @param ship - the length of the ship
	 * @param startCoordinate - starting point
	 * @param direction - 0=vertical, 1=horizontal
	 * @return true if the ship was placed, false if it could not be placed.
	 */
	public boolean checkAndPlace(Map map, int ship, Coordinate startCoordinate, int direction){

		//Check if square and direction is placable
		if(map.checkIfPlacable(startCoordinate, direction, ship)==false){
			return false;
		}
		
		//place the ship with the given parameters
		if(direction == 0){
			for (int i = 0; i < ship; i++) {
				fleet.get(ship).add(
						new Coordinate(startCoordinate.getX()+i, startCoordinate.getY()));
				map.setShipOnSquare(startCoordinate.getX()+i, startCoordinate.getY());
			}
			return true;
		}
		if(direction == 1){
			for (int i = 0; i < ship; i++) {
				fleet.get(ship).add(
						new Coordinate(startCoordinate.getX(), startCoordinate.getY()+i));
				map.setShipOnSquare(startCoordinate.getX(),startCoordinate.getY()+i);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Compares the coordinate of the shot with the coordinates of all ships.
	 * @param Coordinate c - the coordinate 
	 * @return weather the shot hit a ship or not.
	 */
	private boolean checkIfHit(Coordinate c){
		
		//For all ships in fleet
		for (Integer ship : fleet.keySet()) {
			//Check if any coordinate in ship is the given coordinate
			for(Coordinate coordinate : fleet.get(ship)){
				if(c.isCoordinate(coordinate)){
					return true;
				}
			}
			
			/*
			if (fleet.get(ship).contains(c)){
				return true;
			}
			*/
			
		}
		//If the coordinate is not in any ship in the fleet
		return false;
	}
	/**
	 * Compares the coordinates of the shot with all coordinates of the fleet. If it's a hit - remove the ship from the fleet.
	 * @param c
	 * @return true if the fleet is empty - all ships has been sunk and the game is over.
	 */
	private boolean setHit(Coordinate c){
		for (Integer ship : fleet.keySet()) {
			if(fleet.get(ship).contains(c)){
				fleet.get(ship).remove(c);
				return fleet.get(ship).isEmpty();
			}
		}
		return false;
	}

	/**
	 * "Fire a shot" at a given coordinate. Human and computer uses different methods of generating the coordinate.
	 * @param c, map
	 */
	public abstract void makeMove(Coordinate c, Map map);

	
}
