package battleship;
/**
 * The Coordinates of the map, used to store the coordinates of ships
 * @author Victor,Wiktor
 *
 */
public class Coordinate {
	private int x;
	private int y;

	//constructor
	public Coordinate(int x,int y) {
		this.x=x;
		this.y=y;
	}
	
	//getters and setters
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Compares a set of two ints to the x and y coordinates of this coordinte
	 * @param a
	 * @param b
	 * @return true if x = a and y = b.
	 */
	public boolean isCoordinate(int a,int b){
		return(this.getX() == a && this.getY()==b);
		
	}

	/**
	 * Compares another coordinate with this one
	 * @param Coordinate c
	 * @return true if they are the same, false if they're not.
	 */
	public boolean isCoordinate(Coordinate c){
		return(isCoordinate(c.getX(),c.getY()));
	}
}
