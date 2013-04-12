package battleship;

import battleship.Map.MapSize;

/**
 * Minimum:
 * TODO: Human Player:
 *	TODO: Draw player entered ship on board.
 * 	TODO: Make a move
 * 	* 	TODO: Handle collisions..
 * 		TODO: Basic textinput 
 * 			TODO: Advanced placement w keyarrows
 * 	  	TODO: Make support for another human player
 * TODO: Computer player.
 * 	TODO: Place ships.
 * 	TODO: Make a move
 * TODO: Determine winner.
 * TODO: Count misses
 * TODO: Count hits.
 * TODO: Add support for more shiptypes
 * TODO: Highscore
 * 	TODO: Store permanently.
 * 		TODO: Encryption
 * 			TODO: Store on server. SSL. Mums!
 * TODO: Comment. Oh THE COMMENT!
 * TODO: Design
 * 	TODO: Make UML
 * TODO: Graphics
 * 	TODO: Tweak the drawing of game board for better placements.
 *
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map map=new Map(MapSize.LARGE);
		HumanPlayer humanPlayer=new HumanPlayer();
		humanPlayer.placeShips(map);
	}

}
