package test;
import java.util.Arrays;
import java.util.Objects;

public class Word {
	
	Tile[] tiles;
	int row=0,col=0;
	boolean vertical = false;
	public Word(Tile[] ts, int row, int col, boolean vertical) {
		this.tiles = ts;
		this.row = row;
		this.col = col;
		this.vertical = vertical;
	}
	
	
	public Tile[] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isVertical() {
		return vertical;
	}

	public void setVertical(boolean vertical) {
		this.vertical = vertical;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(tiles);
		result = prime * result + row;
		result = prime * result + col;
		result = prime * result + (vertical ? 1231 : 1237);
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (!Arrays.equals(tiles, other.tiles))
			return false;
		if (row != other.row)
			return false;
		if (col != other.col)
			return false;
		if (vertical != other.vertical)
			return false;
		return true;
	}

	
	
}
