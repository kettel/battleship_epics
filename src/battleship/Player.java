package battleship;

import java.util.HashMap;
import java.util.HashSet;

/**
 * The super player-class. Should be concerned with "behind the scenes"-stuff.
 * Check if placement is valid, check if a ship is hit or not, independent of where the coordinates came from.
 *  
 * @author Victor
 *
 */
public abstract class Player {
	protected HashMap<Integer, HashSet<Coordinate>> fleet = new HashMap<Integer, HashSet<Coordinate>>();
	public static class FleetType {
		public static int CLASSIC = 5;
	}

	/**
	 *  
	 * @param type - TODO: vad gör den?
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
	 * Compares the coordinate of the shot with the coordinates of all ships.
	 * @param c
	 * @return weather the shot hit a ship or not.
	 */
	private boolean checkIfHit(Coordinate c){
		for (Integer ship : fleet.keySet()) {
			if (fleet.get(ship).contains(c)){
				return true;
			}
			
		}
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
	 * Fire a shot at a given coordinate. Human and computer uses different methods of doing so.
	 * @param c
	 */
	public abstract void makeMove(Coordinate c, Map map);
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
	 * @return
	 */
	public boolean checkAndPlace(Map map, int ship, Coordinate startCoordinate, int direction){

		if(map.checkIfPlacable(startCoordinate, direction, ship)==false){
			return false;
		}
		
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

	
}
