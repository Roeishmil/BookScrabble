package test;

import java.util.ArrayList;

public class Board {
    // Constants defining board properties like double/triple letter scores
    final int dl = 2;
    final int tl = 3;
    final int dw = 20;
    final int tw = 30;
    private static Tile[][] tiles = null;
	boolean isEmpty;
    private static Board board=null;    
    private int[][] bonusTiles = {
        {tw, 0, 0, dl, 0, 0, 0, tw, 0, 0, 0, dl, 0, 0, tw},
        {0, dw, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, dw, 0},
        {0, 0, dw, 0, 0, 0, dl, 0, dl, 0, 0, 0, dw, 0, 0},
        {dl, 0, 0, dw, 0, 0, 0, dl, 0, 0, 0, dw, 0, 0, dl},
        {0, 0, 0, 0, dw, 0, 0, 0, 0, 0, dw, 0, 0, 0, 0},
        {0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0},
        {0, 0, dl, 0, 0, 0, dl, 0, dl, 0, 0, 0, dl, 0, 0},
        {tw, 0, 0, dl, 0, 0, 0, dw, 0, 0, 0, dl, 0, 0, tw},
        {0, 0, dl, 0, 0, 0, dl, 0, dl, 0, 0, 0, dl, 0, 0},
        {0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, tl, 0},
        {0, 0, 0, 0, dw, 0, 0, 0, 0, 0, dw, 0, 0, 0, 0},
        {dl, 0, 0, dw, 0, 0, 0, dl, 0, 0, 0, dw, 0, 0, dl},
        {0, 0, dw, 0, 0, 0, dl, 0, dl, 0, 0, 0, dw, 0, 0},
        {0, dw, 0, 0, 0, tl, 0, 0, 0, tl, 0, 0, 0, dw, 0},
        {tw, 0, 0, dl, 0, 0, 0, tw, 0, 0, 0, dl, 0, 0, tw}
};

    private Board(){ // Initialize board if it doesn't exist
		tiles = new Tile[15][15];
        this.isEmpty = true;
	}
	public static Board getBoard(){
		if(board == null){
		    board = new Board();
		}
		return board;
	}
	public Tile[][] getTiles(){
		return tiles.clone();
	}
	private boolean inBoard(int row, int col) { // Check if word is within board boundaries
        return (col >= 0 && col < 15 && row >= 0 && row < 15);
    }
	private boolean onStar(Word w) { // Check if word is played on star square
        int i = w.getRow(), j = w.getCol();
        for (int k = 0; k < w.getTiles().length; k++) {
            if (i == 7 && j == 7)
                return true;
            if (w.isVertical()) i++;
            else j++;
        }
        return false;
    }
	private boolean crossTile(Word w) { // Check if word intersects existing tiles
        int i = w.getRow(), j = w.getCol();
        for (int k = 0; k < w.getTiles().length; k++) {

            if (tiles[i][j] != null ||
                    (inBoard(i + 1, j) && tiles[i + 1][j] != null) ||
                    (inBoard(i + 1, j + 1) && tiles[i + 1][j + 1] != null) ||
                    (inBoard(i, j + 1) && tiles[i][j + 1] != null) ||
                    (inBoard(i - 1, j + 1) && tiles[i - 1][j + 1] != null) ||
                    (inBoard(i - 1, j) && tiles[i - 1][j] != null) ||
                    (inBoard(i - 1, j - 1) && tiles[i - 1][j - 1] != null) ||
                    (inBoard(i, j - 1) && tiles[i][j - 1] != null) ||
                    (inBoard(i + 1, j - 1) && tiles[i + 1][j - 1] != null)
            )
                return true;

            if (w.isVertical()) i++;
            else j++;
        }
        return false;
    }
	private boolean changesTile(Word w) { // Check if word changes existing tiles
        int i = w.getRow(), j = w.getCol();
        for (Tile t : w.getTiles()) {
            if (tiles[i][j] != null && tiles[i][j] != t)
                return true;
            if (w.isVertical()) i++;
            else j++;
        }
        return false;
    }
	public boolean boardLegal(Word w){ // Main validation checking all conditions
		int row = w.getRow();
        int col = w.getCol();
		if (!inBoard(row, col)){
			return false;
		}
		int eCol, eRow;
        if (w.isVertical()) {
            eCol = col;
            eRow = row + w.getTiles().length - 1;
	    } else {
            eRow = row;
            eCol = col + w.getTiles().length - 1;
		}
		if (!inBoard(eRow, eCol))
            return false;

		if (isEmpty && !onStar(w))
            return false;
		
		if (!isEmpty && !crossTile(w))
            return false;	
		
		return !changesTile(w);
	} 
    public boolean dictionaryLegal(Word w) {
		return true;
    }
    private ArrayList<Word> getAllWords(Tile[][] ts) {
        ArrayList<Word> ws = new ArrayList<>();

        // Horizontal scan
        for (int i = 0; i < ts.length; i++) {
            int j = 0;
            while (j < ts[i].length) {
                ArrayList<Tile> tal = new ArrayList<>();
                int row = i, col = j;
                while (j < ts[i].length && ts[i][j] != null) {
                    tal.add(ts[i][j]);
                    j++;
                }
                if (tal.size() > 1) {
                    Tile[] tiles = new Tile[tal.size()];
                    ws.add(new Word(tal.toArray(tiles), row, col, false));
                }
                j++;
            }
        }

        // Vertical scan
        for (int j = 0; j < ts[0].length; j++) {
            int i = 0;
            while (i < ts.length) {
                ArrayList<Tile> tal = new ArrayList<>();
                int row = i, col = j;
                while (i < ts.length && ts[i][j] != null) {
                    tal.add(ts[i][j]);
                    i++;
                }
                if (tal.size() > 1) {
                    Tile[] tiles = new Tile[tal.size()];
                    ws.add(new Word(tal.toArray(tiles), row, col, true));
                }
                i++;
            }
        }
        return ws;
    }
    
	public ArrayList<Word> getWords(Word w) {
		Tile[][] ts = getTiles();
		ArrayList<Word> before = getAllWords(ts);
		// demo placement of new word
		int row = w.getRow();
		int col = w.getCol();
		for (Tile t : w.getTiles()) {
			ts[row][col] = t;
			if (w.isVertical()) row++;
			else col++;
		}
        ArrayList<Word> after = getAllWords(ts);
        // is the word is in before - delete it
        after.removeAll(before);
        return after;
    }
    
    public int getScore(Word w) { // Method to calculate score for a played word
        int row = w.getRow();
        int col = w.getCol();
        int sum = 0;
        int tripleWord = 0, doubleWord = 0;
        for (Tile t : w.getTiles()) {
            sum += t.score;
            if (bonusTiles[row][col] == dl)
                sum += t.score;
            if (bonusTiles[row][col] == tl)
                sum += t.score * 2;
            if (bonusTiles[row][col] == dw)
                doubleWord++;
            if (bonusTiles[row][col] == tw)
                tripleWord++;
            if (w.isVertical()) row++;
            else col++;
        }

        if (doubleWord > 0)
            sum *= (2 * doubleWord);
        if (tripleWord > 0)
            sum *= (3 * tripleWord);
        return sum;

    }
    public int tryPlaceWord(Word w) { // Method to simulate playing a word

        Tile[] ts = w.getTiles();
        int row = w.getRow();
        int col = w.getCol();
        for (int i = 0; i < ts.length; i++) {
            if (ts[i] == null)
                ts[i] = tiles[row][col];
            if (w.isVertical()) row++;
            else col++;
        }

        Word test = new Word(ts, w.getRow(), w.getCol(), w.isVertical());

        int sum = 0;
        if (boardLegal(test)) {
            ArrayList<Word> newWords = getWords(test);  
            for (Word nw : newWords) {          
            if (dictionaryLegal(nw)) {
                sum += getScore(nw);
            }else
                return 0;
            }
            
        } else return -1;


        // the placement
        row = w.getRow();
        col = w.getCol();
        for (Tile t : w.getTiles()) {
            tiles[row][col] = t;
            if (w.isVertical()) row++;
            else col++;
        }

        if (isEmpty) {
            isEmpty = false;
            bonusTiles[7][7] = 0;
        }
        return sum;
    }
}
	
	

