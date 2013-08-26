package battleship;

import java.util.HashMap;
import java.util.HashSet;

import battleship.Map.MapSize;

/*
 * Minimum:
 * TODO: Comment. Oh THE COMMENT!
 *  
 * TODO: Make the game follow teh rulez
 * [X] boats at least placed 1 square away from each other - fixed for human and computer alike
 * 	[X] Create a way to compare two coordinates
 * 		*** Created the isCoordinate (better name?) functions in coordinate ***
 * 
 * TODO: super: Player
 * [X] draw board for players
 * [X] MakeMove - a super one - should probably be the same for both human and computer
 * 		***works for the human player***
 * 		***computerplayer got a randomized version***
 * 
 * 
 * 
 * TODO: Human Player:
 *	[X] Draw player entered ship on board.
 * 	[X]: Make a move
  	* 	[X] Handle collisions (if we with collision meant "the same player hitting on the same square twice" or something like that)
 * 		[X] Basic textinput (x,y)
 * 			[X] incorrect input should be fixed
 * 			(TODO: Advanced placement w keyarrows)
 * 	  	TODO: Make support for another human player
 * 
 * TODO: Computer player.
 * 	[X] Place ships (random)
 * 
 * 	TODO: Make a move (pure random, peeking or something with memory?)
 * 		[X] random
 * 
 * TODO: Create a functioning game-loop ((preferably one that can be used both locally and over intewebs))
 * 		***should probably not be a static thing. Alpha down below in main.
 * 		***should it be it's own object/class or something?
 * 
 * TODO: Determine winner.
 * TODO: Count misses
 * TODO: Count hits.
 * 	(Keep track of number of # hits and # turns? )
 * 
 * TODO: Design
 *	TODO: Make UML
 * 
 * 
 * If time allows for it:
 * TODO: more rules? OwO
 * 		alternatives:
 * 		[ ] you may fire once per ship in your fleet that has yet to be damaged, or once if all ship has been damaged
 * 		[ ] may fire once per unsunk ship
 * 		[ ] may fire three times per turn, but may not know what shot hit what (like "you fired at (0,0),(1,1),(2,2). You had 2 hits and 1 miss")
 * 		[ ] if you sink a ship you may fire again
 * 
 * TODO: Add support for more shiptypes <- begun
 * TODO: Network-play !!! Will probably have to rewrite a lot !!!
 * TODO: Highscore
 * 	TODO: Store permanently on file.
 * 		TODO: Encryption
 * 			TODO: Store on server. SSL. Mums!
 * TODO: Graphics
 * 	TODO: Tweak the drawing of game board for better placements.

 */


/**
 * Battleship, the game based on the movie with the same name.
 * 
 * @author Victor,Wiktor
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map humanMap=new Map(MapSize.LARGE);
		Map computerMap=new Map(MapSize.LARGE);
		
		//TODO: Do we need a PLAYER object in the player object...
		//		seems kinda redundant. Or should we just create the players as
		//		humans or computers? (like below)
		//TODO: fixed?
		//HumanPlayer humanPlayer=new HumanPlayer();
		//humanPlayer.placeShips(humanMap);
		
		Player BMO = new ComputerPlayer(MapSize.LARGE);
		BMO.placeShips(computerMap);

		Player FINN = new HumanPlayer(MapSize.LARGE);
		//FINN.placeShips(humanMap);
		//computerMap.setForPlay();
		//computerMap.drawSetupMap();
		//computerMap.drawGameMap();
		
		//HumanPlayer human = new HumanPlayer();
		
		//switchFleet(human,computer);
		switchFleet(BMO, FINN);

		
		int turn = 0;
		Player ActivePlayer = FINN;
		
		//TODO: simple gameloop alpha version.
		
		/*while(!BMO.fleet.isEmpty() && !HAL.fleet.isEmpty() && turn<=100){ //while(true) should work as well. or while(turn <=100)
			turn++;
			ActivePlayer = player1;
			if(player1.makeMove(humanMap)){
				break;
			}
			humanMap.drawGameMap();
			
			ActivePlayer = player2;
			if(player2.makeMove(computerMap)){
				break;
			}
			computerMap.drawGameMap();
		}*/

		System.out.println(ActivePlayer.toString() +" vann!");
		System.out.println("Det tog "+turn+" omgångar");
			//computerMap.drawGameMap();
		/*human.placeShips(humanMap);
		humanMap.setForPlay();
		humanMap.drawSetupMap();
		*/
	}
	/**
	 * Function used to switch the fleets of the players so that both players more easily can fire and make checks on the fleet the opponent placed.
	 * TODO: as of now not viable solution for games over the internet.
	 * TODO: should this be in player? 
	 * @param Player p1 
	 * @param Player p2
	 */
	private static void switchFleet(Player p1, Player p2){
		HashMap<Integer, HashSet<Coordinate>> buffer = p1.fleet;
		p1.fleet = p2.fleet;
		p2.fleet = buffer;
		buffer = null;
	}
	
	

}
