package battleship;

public class HighscoreEntry implements Comparable<HighscoreEntry>{
	private final Integer nofTurns;
	private final Long timestamp;
	private final String alias;
	
	public HighscoreEntry(String alias, int nofTurns, long timestamp){
		this.alias = alias;
		this.nofTurns = nofTurns;
		this.timestamp = timestamp;
	}
	
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
		int lastCmp = nofTurns.compareTo(o.nofTurns);
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

    public String toString() {
	return nofTurns + "<- " + alias + ": " + timestamp;
    }
	
}
