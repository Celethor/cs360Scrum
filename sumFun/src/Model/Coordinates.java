package Model;

public class Coordinates {
	private int row;
	private int col;
	
	public Coordinates(int rows,int cols){
		this.row=rows;
		this.col=cols;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int rows) {
		this.row = rows;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int cols) {
		this.col = cols;
	}
	
	
}
