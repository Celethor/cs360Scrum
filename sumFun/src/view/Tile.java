package view;

import javax.swing.JButton;

import model.Coordinates;

public class Tile extends JButton {
	
	private Coordinates coord;
	
	public Tile(int r,int c){
		Coordinates coord=new Coordinates(r,c);
		this.coord=coord;
	}

	public Coordinates getCoord() {
		return coord;
	}

	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}
	
}
