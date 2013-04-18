package battleship;

import battleship.Map.MapSize;

/**
 * Minimum:
 * TODO: Comment. Oh THE COMMENT!
 *  
 * TODO: Make the game follow teh rulez (boats at least placed 1 square away from each other)
 * [X] Create a way to compare two coordinates
 * 		*** Created the isCoordinate functions in coordinate ***
 * 
 * TODO: Human Player:
 *	[X] Draw player entered ship on board. Kinda started.
 * 	TODO: Make a move
  	* 	TODO: Handle collisions..
 * 		TODO: Basic textinput 
 * 			TODO: Advanced placement w keyarrows
 * 	  	TODO: Make support for another human player
 * 
 * TODO: Computer player.
 * 	TODO: Place ships.
 * 	TODO: Make a move
 * 
 * TODO: Create a functioning game-loop ((preferably one that can be used both locally and over intewebs))
 * 
 * TODO: Determine winner.
 * TODO: Count misses
 * TODO: Count hits.
 * 
 * TODO: Design
 *	TODO: Make UML
 * 
 * 
 * If time allows for it:
 * TODO: Add support for more shiptypes
 * TODO: Network-play
 * TODO: Highscore
 * 	TODO: Store permanently.
 * 		TODO: Encryption
 * 			TODO: Store on server. SSL. Mums!
 * TODO: Graphics
 * 	TODO: Tweak the drawing of game board for better placements.

 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map map=new Map(MapSize.LARGE);
		
		//TODO: Do we need a PLAYER object in the player object...
		//		seems kinda redundant. Or should we just create the players as
		//		humans or computers? (like below)
		HumanPlayer humanPlayer=new HumanPlayer();
		humanPlayer.placeShips(map);
	}

}
