package battleship;

import battleship.Map.MapSize;


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
