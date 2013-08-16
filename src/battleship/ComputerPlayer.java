
package battleship;

import java.util.HashSet;

import battleship.Player.FleetType;

/**
 * Computer player. Should mainly be concerned with generating coordinates for where to put ships and where to shoot. 
 * @author Victor, Wiktor
 *
 */
public class ComputerPlayer extends Player {
    /**
     * TODO: Implement a functional computer player
     *
     */
	private static int VERTICAL = 0;
	private static int HORIZONTAL = 1;
	
	boolean isPlaced;

	public ComputerPlayer() {
		super(FleetType.CLASSIC);
		
		fleet.put(2, new HashSet<Coordinate>()); // Patrol boat / Destroyer
		fleet.put(3, new HashSet<Coordinate>()); // Destroyer / Cruiser
		fleet.put(3, new HashSet<Coordinate>()); // Submarine
		fleet.put(4, new HashSet<Coordinate>()); // Battleship
		fleet.put(5, new HashSet<Coordinate>()); // Aircraft carrier
	}

	@Override
	public void placeShips(Map map) {
		for (Integer ship : fleet.keySet()) {
			isPlaced=false;
			while(isPlaced == false){
				int[] start = new int[2];
				
				// Random placement
				for (int i =0; i< 2; i++) {
					// Generate a random number in the range [0,map.getSize()-1]
					
					start[i] = 0 + (int)(Math.random()*map.getSize()-1);
				}
				Coordinate startCoordinate = new Coordinate(
						Integer.valueOf(start[0]), Integer.valueOf(start[1]));
				
				// Random orientation (vertical/horizontal)
				int orientation = 0 + (int)(Math.random()*2);
				
				isPlaced=checkAndPlace(map, ship, startCoordinate, orientation);
			}
		}
		map.drawMap();
	}


	@Override
	public Coordinate generateMove(Map map) {
		// TODO Auto-generated method stub
		return null;
	}

}
