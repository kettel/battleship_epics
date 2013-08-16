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
	
	/**
	 * creates a Coordinate using input from the user 
	 * @param scanner - where we get the input from
	 * @return a coordinate 
	 * @throws Exception - if input is not valid
	 */
	public Coordinate makeCoordinateFromInput(Scanner scanner) throws Exception{
		try{
			//get input
			String input = scanner.nextLine();
			String[] start = input.split(",");
			
			//create and return coordinate
			return new Coordinate(Integer.parseInt(start[0]), Integer.parseInt(start[1]));
		} catch(Exception e){
			throw new Exception();
			}
	}
	
	@Override
	public void placeShips(Map map) {
		Scanner scanner = new Scanner(System.in);
		
		for (Integer ship : fleet.keySet()) {
			isPlaced=false;
			// while the ship is not placed in a valid way
			while(isPlaced == false){
				System.out.println("Placera ut ett skepp med längden " + ship);
				
				try{
				
				System.out.println("Ange startpunkt enligt formatet (x,y):");
				Coordinate startCoordinate = makeCoordinateFromInput(scanner); //get input & create the coordinate
				
				System.out.println("Ange riktning, ");
				System.out.println("Horisontal (åt höger från startpunkten) = 1, vertikal (placering nedåt från startpunkten) = 0:");
				
				String input = scanner.nextLine();
				input.split(",");
				
				isPlaced=checkAndPlace(map, ship, startCoordinate, Integer.parseInt(input));
				
				}catch(Exception e){
					isPlaced = false;		
					System.out.println("input error1 - please try again\n");
				}
			}
			map.drawSetupMap();
		}	
		scanner.close();
	}

	@Override
	public Coordinate generateMove(Map map) {
		
		Boolean isValidMove = false;
		Coordinate coord = null;
		Scanner scanner = new Scanner(System.in);
		// while a valid move has yet to be generated
		while(isValidMove==false || coord == null){
			//Show map
			map.drawGameMap();
			//Get input
			try {
				System.out.println("Ange var du vill skjuta enligt formatet (x,y):");
				coord = makeCoordinateFromInput(scanner);
			} catch (Exception e) {
				isValidMove = false;
				System.out.println("input error2 - please try again");
			}
			//Check if input is valid
			isValidMove = map.isSquareNotHit(coord);
		}
		System.out.println("ok move:" +coord.getX()+','+coord.getY());
		return coord;
	}
}