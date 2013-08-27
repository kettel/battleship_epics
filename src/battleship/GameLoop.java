package battleship;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Class with functions 
 * @author Victor,Wiktor
 *
 */
public class GameLoop {
	//Players
	Player player1;
	Player player2;
	//Maps
	Map map1; //map player2 shall fire upon
	Map map2; //map player1 shall fire upon

	/**
	 * Constructor for the gameloop. Takes in two players and then sets up everything necessary for the game. Then the game begins.
	 * @param size of the board
	 * @param Player one 
	 * @param Player two 
	 */
	public GameLoop(int size, Player player1, Player player2){
		//Setup
		
		this.player1 = player1;
		this.player2 = player2;
		//For each player: create and setup the map.
		map1 = setupMap(size, player1);
		map2 = setupMap(size, player2);
		//Switch fleets
		switchFleet(player1, player2);
		
		//Begin gameloop (as in main)
		Player winner = playGame(size);
		//Keep track of turns and score
		//After game: highscore time :D
	}
	/**
	 * Sets up the map for a player - create it, place the ships etc. 
	 * @param size of the map
	 * @param the player
	 * @return the map 
	 */
	
	private Map setupMap(int size, Player player){
		Map map = new Map(size);
		player.placeShips(map);
		return map;
	}
	
	/**
	 * Function used to switch the fleets of the players so that both players more easily can fire and make checks on the fleet the opponent placed.
	 * @param Player1
	 * @param Player2
	 */

	private void switchFleet(Player player1, Player player2){
		HashMap<Integer, HashSet<Coordinate>> buffer = player1.fleet;
		player1.fleet = player2.fleet;
		player2.fleet = buffer;
		buffer = null;
	}
	/**
	 * The gameloop itself. lets the players take turns to fire at eachothers fleets. When no more ships remain, return the winner.
	 * @param size of the map - used to calculate the maxamount of turns needed 
	 * @return the victor
	 */
	private Player playGame(int size) {
		int turn = 0; //the current turn
		
		int maxTurns = Math.max(map1.getSize(),map2.getSize())^2;  //max possible amount of turns.
		
		
		
		
		while (turn<=maxTurns) {
			turn++;
		}
		
		return player1;
		
	}
}
