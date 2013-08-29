package battleship;

/**
 * HighscoreEntry that implements Comaparable for easy ordering of Highscore
 * @author Victor, Wiktor
 *
 */
public class HighscoreEntry implements Comparable<HighscoreEntry>{
	// Variables
	private final Integer nofTurns;
	private final Long timestamp;
	private final String alias;
	
	// Constructor
	public HighscoreEntry(String alias, int nofTurns, long timestamp){
		this.alias = alias;
		this.nofTurns = nofTurns;
		this.timestamp = timestamp;
	}
	
	// Getters
	public String alias(){
		return alias;
	}
	public Integer nofTurns(){
		return nofTurns;
	}
	public Long timestamp(){
		return timestamp;
	}

	@Override
	public int compareTo(HighscoreEntry o) {
		// First, look at nofTurns
		int lastCmp = nofTurns.compareTo(o.nofTurns);
		
		// If nofTurns are equal, look at timestamp. Ensures that older entry gets 
		// higher position than newer, in case of the same nofTurns
		return (lastCmp != 0 ? lastCmp : timestamp.compareTo(o.timestamp));
	}
	
	public boolean equals(Object o) {
        if (!(o instanceof HighscoreEntry))
            return false;
        HighscoreEntry n = (HighscoreEntry) o;
        return n.timestamp.equals(timestamp) && n.nofTurns.equals(nofTurns);
    }

    public int hashCode() {
        return 31*timestamp.hashCode() + nofTurns.hashCode() + alias.hashCode();
    }
}
