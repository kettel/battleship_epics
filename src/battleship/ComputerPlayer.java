
package battleship;

import java.util.HashSet;
import java.util.Scanner;

/**
 * Computer player. Should mainly be concerned with generating coordinates for where to put ships and where to shoot. 
 * @author Victor
 *
 */
public class ComputerPlayer extends Player {
    /**
     * TODO: Implement a functional computer player
     *
     */
	private static int VERTICAL = 0;
	private static int HORIZONTAL = 1;

	public ComputerPlayer() {
		super(2);
	}

	@Override
	public void makeMove(Coordinate c) {

	}

	@Override
	public void placeShips(Map map) {
	}

}
