package battleship;

import java.util.HashMap;
import java.util.HashSet;

public abstract class Player {
	protected HashMap<Integer, HashSet<Coordinate>> fleet = new HashMap<Integer, HashSet<Coordinate>>();
	public static class FleetType {
		public static int CLASSIC = 5;
	}

	public Player(int type) {
	}
	private boolean checkIfHit(Coordinate c){
		for (Integer ship : fleet.keySet()) {
			return fleet.get(ship).contains(c);
		}
		return false;
	}
	private boolean setHit(Coordinate c){
		for (Integer ship : fleet.keySet()) {
			if(fleet.get(ship).contains(c)){
				fleet.get(ship).remove(c);
				return fleet.get(ship).isEmpty();
			}
		}
		return false;
	}

	public abstract void makeMove(Coordinate c);
	public abstract void placeShips(Map map);
}
