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
	private int nrHits = 0;
	private int maxNrShips = 0;
	private int nrRemainingShips = 0;
	private String alias;

	/**
	 *  Constructor
	 * @param type - TODO: vad g�r den
	 */
	public Player(String name) {
		alias = name;
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
	protected boolean checkAndPlace(Map map, int ship, Coordinate startCoordinate, int direction){

		//Check if square and direction is placable
		if(map.checkIfPlacable(startCoordinate, direction, ship)==false){
			return false;
		}
		
		//place the ship with the given parameters
		if(direction == 0){
			for (int i = 0; i < ship; i++) {
				//add it to fleet
				fleet.get(ship).add(
						new Coordinate(startCoordinate.getX()+i, startCoordinate.getY()));
				//put it on map
				map.setShipOnSquare(startCoordinate.getX()+i, startCoordinate.getY());
				maxNrShips++;
				nrRemainingShips++; //add it to the number of ships
			}
			return true;
		}
		if(direction == 1){
			for (int i = 0; i < ship; i++) {
				//add it to fleet
				fleet.get(ship).add(
						new Coordinate(startCoordinate.getX(), startCoordinate.getY()+i));
				//put it on map
				map.setShipOnSquare(startCoordinate.getX(),startCoordinate.getY()+i);
				maxNrShips++;
				nrRemainingShips++; //add it to the number of ships
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
	private boolean checkIfIsHit(Coordinate c){ //TODO: seems to be unused
		
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
	 * fires the shot basically. Calls upon functions to set the shot on the map and to remove the ship (or ship-part) from the fleet  
	 * @param c - the coordinate of the map
	 * @param map -the map that shall be fired upon
	 * @return true if a ship was hit
	 */
	private boolean setHit(Coordinate c, Map map){
		map.setHit(c);
		return setHit(c);
	}
	
	/**
	 * Compares the coordinates of the shot with all coordinates of the fleet. If it's a hit - remove the ship from the fleet.
	 * @param Coordinate c - the coordinate that has been shot 
	 * @return true if a ship was hit
	 */
	private boolean setHit(Coordinate c){
		Boolean isHit = false; //boolean that is used to check if the move was a hit 
		
		for (Integer ship : fleet.keySet()) { //for every ship in the fleet do:
			
			//Check if any coordinate in ship is the given coordinate
			for(Coordinate coordinate : fleet.get(ship)){
				if (c.isCoordinate(coordinate)){ //if coordinate in the ship, remove coordinate from ship
					isHit = true;
					nrHits++;
					nrRemainingShips--;
					fleet.get(ship).remove(coordinate);
					
					if (fleet.get(ship).isEmpty()) { //if the ship is empty - ship is sunk
						System.out.println("Skepp s�nkt");
						fleet.remove(ship);
					}
					return isHit;
					
				}
			}
		}
		
		return false; //if coordinate not in any ship in fleet.
	}

	/**
	 * "Fire a shot" at a coordinate on a given map, uses generateMove to get the coordinate
	 * @param map - the map, c - the coordinate on the map
	 * @return true if all ships has been sunk - used to decide if the game is over.
	 */
	public boolean makeMove(Map map){
		Coordinate c = generateMove(map);
		return setHit(c,map);
		
	}

	/**
	 * Generates the coordinate the player want to fire a shot at. Human and computer uses different methods of generating it.
	 * Also makes sure that the move is valid.
	 * @param map - the map that the player shall fire at.  
	 * @return the coordinate
	 */
	public abstract Coordinate generateMove(Map map);
	
	/**
	 * @return the number of shots that has hit an enemy 
	 */
	public int getNrHits(){
		return nrHits;
	}
	/**
	 * @return the number of losses the fleet has suffered, amount of hits the enemy has had. 
	 */
	public int getNrLosses(){		
		return maxNrShips-nrRemainingShips;
	}

	/**
	 * @return the alias of this player
	 */
	public String getAlias() {
		return alias;
	}

}
