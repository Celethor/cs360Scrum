package Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Model extends Observable{
	//private Tile [][]tiles;
	private Integer [][]tiles; 		//integer type should be enough since we are using JButtons in our view.
	private Queue tilesQueue;
	private int score;
	private String gameType;
	private final int moveLimit=50;
	private final int queueSize=5;
	/**
	 * Default constructor
	 * Initializes all the data members 
	 */
	public Model(String gameType){
		//random object for assigning random values in the tiles
		Random rand=new Random();
		//this.tiles=new Tile[9][9];
		this.tiles=new Integer[9][9];
		//initialize every tile
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				this.tiles[i][j]=new Integer(-1);
			}
		}
		//set values for the tiles now; ignore borders
		for(int i=1;i<8;i++){
			for(int j=1;j<8;j++){
				//tiles[i][j].setValue(rand.nextInt());
				tiles[i][j]=rand.nextInt(9);
			}
		}
		//initialize the queue
		this.tilesQueue=new Queue(5);
		//initialize the score (initial)
		this.score=0;
		//initialize the gameType
		this.gameType=gameType;
		//initialize queue
		this.tilesQueue=new Queue(queueSize);
		for(int i=0;i<queueSize;i++){
			try {
				tilesQueue.enqueue(rand.nextInt(9));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Queue initialization exception");
			}
		}
	}
	
	public int isSuccessfulPlacement(Coordinates coord){
		int row=coord.getRow();
		int col=coord.getCol();
		boolean success=false;
		int sum=0;
		int usefulNeighborCtr=0;
		//get coordinates of all useful neighbors
		ArrayList<Coordinates>usefulNeighbors=getUsefulNeighbors(coord);
		//loop through all useful neighbors and calculate the sum of their values
		for(int i=0;i<usefulNeighbors.size();i++){
			int r=usefulNeighbors.get(i).getRow();
			int c=usefulNeighbors.get(i).getCol();
			sum+=tiles[r][c];
			usefulNeighborCtr++;
		}
		//get top element of queue
		int elementPlaced=-1;
		try {
			elementPlaced = tilesQueue.dequeue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//compare value according to given rule
		if(elementPlaced==-1){
			throw new NullPointerException("tilesQueue Deque null ptr. Not Working!");
		}
		else if(sum%10==elementPlaced){
			success=true;
		}
		
		//returns number of removed neighbors + 1 (since placed tile is also counted removed) if success
		if(success){
			return usefulNeighborCtr+1;
		}
		else{
			return -1;
		}
	}
	
	public ArrayList<Coordinates>getUsefulNeighbors(Coordinates coord){
		int row=coord.getRow();
		int col=coord.getCol();
		/*
		if((row==0&&col==0)){
			int [][]allNeighbors={{row,col+1},{row+1,col},{row+1,col+1}};
		}
		else if((row==0&&col==8)){
			int [][]allNeighbors={{row+1,col},{row,col-1},{row+1,col-1}};
		}
		else if((row==8&&col==0)){
			int [][]allNeighbors={{row,col+1},{row-1,col},{row-1,col+1}};
		}
		else if((row==8&&col==8)){
			int [][]allNeighbors={{row,col-1},{row-1,col},{row-1,col-1}};
		}
		else{
			int [][]allNeighborsCoords={{row-1,col-1},{row-1,col},{row-1,col+1},
					{row,col-1},{row,col+1},
					{row+1,col-1},{row+1,col},{row+1,col+1}};
		}*/
		//all possible combinations for a tile placed in a grid having coordinates {row,col}
		int [][]allNeighborsCoords={{row-1,col-1},{row-1,col},{row-1,col+1},
				{row,col-1},{row,col+1},
				{row+1,col-1},{row+1,col},{row+1,col+1}};
		ArrayList<Coordinates>allNeighbors=new ArrayList<Coordinates>();//all neighbors for the placed tile
		ArrayList<Coordinates>usefulNeighbors=new ArrayList<Coordinates>();//all neighbors that are not empty
		//find all the neighbors
		for(int i=0;i<allNeighborsCoords.length;i++){
			int r=allNeighborsCoords[i][0];
			int c=allNeighborsCoords[i][1];
			if((r>=0&&r<=8)&&(c>=0&&c<=8)){
				allNeighbors.add(new Coordinates(r,c));
			}
		}
		//find all non-empty neighbors
		for(int i=0;i<allNeighbors.size();i++){
			if(tiles[allNeighbors.get(i).getRow()][allNeighbors.get(i).getCol()]!=-1){
				usefulNeighbors.add(allNeighbors.get(i));
			}
		}
		return usefulNeighbors;
	}
		
	
	public Integer[][] getTiles() {
		return tiles;
	}

	public void setTiles(Integer[][] tiles) {
		this.tiles = tiles;
		setChanged();
		notifyObservers();
	}

	public Queue getTilesQueue() {
		return tilesQueue;
	}

	public void setTilesQueue(Queue tilesQueue) {
		this.tilesQueue = tilesQueue;
		setChanged();
		notifyObservers();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
		setChanged();
		notifyObservers();
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
		setChanged();
		notifyObservers();
	}

	public int getMoveLimit() {
		return moveLimit;
	}
	public int getTileValue(Coordinates coord){
		return tiles[coord.getRow()][coord.getCol()];
	}
	public void setTileValue(int value, Coordinates coord){
		this.tiles[coord.getRow()][coord.getCol()]=value;
		setChanged();
		notifyObservers();
	}
}
