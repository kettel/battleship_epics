package battleship;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Human player. Should mainly have methods for taking input from player and printing the effects of moves n' stuff.
 * @author Victor
 *
 */
public class HumanPlayer extends Player {
	private static int VERTICAL = 0;
	private static int HORIZONTAL = 1;


	public HumanPlayer() {
		//TODO: should this be in Player? (Cause I presume its the same for all players)
		super(FleetType.CLASSIC);
		fleet.put(2, new HashSet<Coordinate>());
		
		fleet.put(3, new HashSet<Coordinate>());
	}

	@Override
	public void makeMove(Coordinate c) {
		
	}
	
	

	@Override
	public void placeShips(Map map) {
		Scanner scanner = new Scanner(System.in);
		for (Integer ship : fleet.keySet()) {
			System.out.println("Placera ut ett skäpp med länden " + ship);
			System.out.println("Ange startpunkt enligt formatet (x,y): ");
			String input = scanner.nextLine();
			String[] start = input.split(",");
			Coordinate startCoordinate = new Coordinate(
					Integer.parseInt(start[0]), Integer.parseInt(start[1]));
			System.out.println("Ange riktning, ");
			System.out.println("Horisontal = 1, vertikal = 0:");
			input = scanner.nextLine();
			input.split(",");
			if (Integer.parseInt(input) == HORIZONTAL) {
				for (int i = 0; i < ship; i++) {
					fleet.get(ship).add(
							new Coordinate(i, startCoordinate.getY()));
					map.setSquareTaken(i, startCoordinate.getY());
				}
			} else if (Integer.parseInt(input) == VERTICAL) {
				for (int i = 0; i < ship; i++) {
					fleet.get(ship).add(
							new Coordinate(startCoordinate.getX(), i));
					map.setSquareTaken(startCoordinate.getX(),i);
				}
			} else {
				System.out.println("unknown input");
			}
			map.drawMap(map.getMap(), getCoordinates());
		}
		
		map.drawMap(map.getMap(), getCoordinates());
		
		scanner.close();
	}

}
