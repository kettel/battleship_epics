package battleship;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
	
	int turn = 0;

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
		System.out.println(winner.getAlias() + " vann!");
		System.out.println("Det tog "+ turn+" omgångar!");
		
		
		// If winner is human, print to highscore	
		if(winner.isHuman()){
			try {
				PrintWriter writer;
				writer = new PrintWriter(new BufferedWriter(new FileWriter("highscore.txt", true)));
				writer.append(winner.getAlias() + ";" + turn + ";" + System.currentTimeMillis() + "\n");
				writer.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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
		HashMap<Integer[], HashSet<Coordinate>> buffer = player1.fleet;
		player1.fleet = player2.fleet;
		player2.fleet = buffer;
		buffer = null;
	}
	/**
	 * The gameloop itself. lets the players take turns to fire at each others fleets. When no more ships remain, return the winner.
	 * @return the victor
	 */
	private Player playGame(int size) {
		turn = 0; //the current turn
		int maxTurns = size*size;  //max possible amount of turns.
		Player activePlayer = null;
		//Loopetyloop
		while (turn<=maxTurns) {
			//start turn
			turn++; //next turn
			System.out.println("Omg�ng "+turn);
			System.out.println("Statistik");
			displayStatistics(); //print hit- & loss percentage for both players
			//Player1 make its move
			activePlayer = player1;
			if (player1.makeMove(map2)) { //if move hits
				System.out.println("Tr�ff!");
				if(player1.fleet.isEmpty()){ //if all ships has been sunk
					break;
				}
			}
			//Player2 make its move
			activePlayer = player2;
			if(player2.makeMove(map1)){ //if move hits
				System.out.println("Tr�ff!");
				if(player2.fleet.isEmpty()){ //if all ships has been sunk
					break;
				}
			}
		}
		if(turn>maxTurns){//should not happen... probably
			activePlayer = null;
		}
		return activePlayer; //the last active player is the winner
		
	}
	
	/**
	 * function that displays the hit- & loss percentage (/turn) of the players
	 */
	private void displayStatistics() {
		//player1
		System.out.println(player1.getAlias());
		System.out.println("tr�ffprocent: "+(float)player1.getNrHits()*100/turn);
		System.out.println("f�rlustprocent: "+(float)player2.getNrLosses()*100/turn);
		//player2
		System.out.println(player2.getAlias());
		System.out.println("tr�ffprocent: "+(float)player2.getNrHits()*100/turn);
		System.out.println("f�rlustprocent: "+(float)player1.getNrLosses()*100/turn);
	}
}
