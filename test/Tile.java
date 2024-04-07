package test;
import java.util.*;

public class Tile {

    final char letter;
    final int score;

    private Tile(char letter, int score) {
		this.letter = letter;
		this.score = score;
	}

	@Override
    public int hashCode() {
    	return Objects.hash(letter, score);
    }
    @Override
    public boolean equals(Object obj) {
    	if (this == obj)
    		return true;
    	if (obj == null)
    		return false;
    	if (getClass() != obj.getClass())
    		return false;
    	Tile other = (Tile) obj;
    	return letter == other.letter && score == other.score;
    }

	static class Bag {
		private static Bag bag;
    	int[] TilesInBeg;
    	final Tile[] tiles;
		int amountInBeg;
		Random r;
    	int max,min;
				
    	private Bag() {
			bag = null;
			this.TilesInBeg = new int[] {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
			this.tiles = new Tile[]{
			new Tile('A', 1),
			new Tile('B', 3),
			new Tile('C', 3),
			new Tile('D', 2),
			new Tile('E', 1),
			new Tile('F', 4),
			new Tile('G', 2),
			new Tile('H', 4),
			new Tile('I', 1),
			new Tile('J', 8),
			new Tile('K', 5),
			new Tile('L', 1),
			new Tile('M', 3),
			new Tile('N', 1),
			new Tile('O', 1),
			new Tile('P', 3),
			new Tile('Q', 10),
			new Tile('R', 1),
			new Tile('S', 1),
			new Tile('T', 1),
			new Tile('U', 1),
			new Tile('V', 4),
			new Tile('W', 4),
			new Tile('X', 8),
			new Tile('Y', 4),
			new Tile('Z', 10)};
			this.amountInBeg = 98;
			r = new Random();
			min=0;
			max=TilesInBeg.length-1;
		}

		public Tile getRand(){
			int i = r.nextInt(TilesInBeg.length);
			if(amountInBeg > 0) {
				while(TilesInBeg[i]==0){
					i = r.nextInt(TilesInBeg.length);
				}
				TilesInBeg[i]--;
				amountInBeg--;
				return tiles[i];
			}
			return null;

		}
    	
		public Tile getTile(char c) {
			if (c >= 'A' && c <= 'Z' && amountInBeg != 0) {
                amountInBeg--;
                return tiles[c - 'A'];
            }
            return null;
    	}
    	
		public void put(Tile t) {
			final int[] limitRuls = {9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1};
    		for(int i=0;i<tiles.length;i++) {
    			if(t != null && tiles[i].letter == t.letter && TilesInBeg[i] < limitRuls[i]) {
    				TilesInBeg[i]++;
					amountInBeg++;
					break;
    			}
            }
    	}
    	
		public int amountInBeg() {
    		int amountInBeg = 0;
    		for(int i=0;i<TilesInBeg.length;i++) {
    			amountInBeg = amountInBeg + TilesInBeg[i];
    		}
    		return amountInBeg;
    	}
    	
 		public static Bag getBag() {
    		if (bag == null) {
    			bag = new Bag();
    		}
    		return bag;
    	}
		
		public int[] getQuantities() {
			int[] dup = this.TilesInBeg.clone();
     		return dup;
    	}
	}
}
    

