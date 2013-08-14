package battleship;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Human player. Should mainly have methods for taking input from player and printing the effects of moves n' stuff.
 * @author Victor, Wiktor
 *
 */
public class HumanPlayer extends Player {
	private static int VERTICAL = 0;
	private static int HORIZONTAL = 1;
	boolean isPlaced;



	public HumanPlayer() {
		//TODO: should this be in Player? ('Cause I presume its the same for all players)
		super(FleetType.CLASSIC);
		
		fleet.put(2, new HashSet<Coordinate>()); // Patrol boat / Destroyer
		fleet.put(3, new HashSet<Coordinate>()); // Destroyer / Cruiser
		//fleet.put(3, new HashSet<Coordinate>()); // Submarine
		//fleet.put(4, new HashSet<Coordinate>()); // Battleship
		//fleet.put(5, new HashSet<Coordinate>()); // Aircraft carrier
	}

	@Override
	public void makeMove(Coordinate c, Map map) {
		
	}
	
	
	@Override
	public void placeShips(Map map) {
		Scanner scanner = new Scanner(System.in);
		
		for (Integer ship : fleet.keySet()) {
			isPlaced=false;
			//
			while(isPlaced == false){
				System.out.println("Placera ut ett skepp med längden " + ship);
				try{
				System.out.println("Ange startpunkt enligt formatet (x,y): ");
				String input = scanner.nextLine();
				String[] start = input.split(",");
				
				Coordinate startCoordinate = new Coordinate(
						Integer.parseInt(start[0]), Integer.parseInt(start[1]));
				System.out.println("Ange riktning, ");
				System.out.println("Horisontal (åt höger från startpunkten) = 1, vertikal (placering nedåt från startpunkten) = 0:");
				
				input = scanner.nextLine();
				input.split(",");
				
				isPlaced=checkAndPlace(map, ship, startCoordinate, Integer.parseInt(input));
				
				}catch(Exception e){
					isPlaced = false;		
					System.out.println("error1");
				}
			}
			
			map.drawMap(map.getMap(), getCoordinates());
		}
				
		scanner.close();
	}
}