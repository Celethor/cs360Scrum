package Model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Timer;

public class Model extends Observable{
	//private Tile [][]tiles;
	private Integer [][]tiles; 		//integer type should be enough since we are using JButtons in our view.
	private Queue<Integer> tilesQueue; // @TODO check implementation
	private int score;
	private String gameType;
	private int remainingMoves;
	private Timer timer;
	private boolean gameOver;
	private int empty;
	private boolean won;
	private final int moveLimit=50;
	private final int queueSize=5;
	private int removedElement;
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
				tiles[i][j]=rand.nextInt(10);
			}
		}
		//initialize the queue
		//this.tilesQueue=new ArrayList<Integer>();   // Initialized Twice???????????
		//initialize the score (initial)
		this.score=0;
		//initialize the gameType
		this.gameType=gameType;
		//initialize queue
		this.tilesQueue=new Queue<Integer>();
		populateQueue();
		
		this.remainingMoves=50;
		this.gameOver=false;
		this.empty=32;//present number in the borders
		
	}
	
	public int getRemainingMoves() {
		return remainingMoves;
	}

	public void setRemainingMoves(int remainingMoves) {
		this.remainingMoves = remainingMoves;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getEmpty() {
		return empty;
	}

	public void setEmpty(int empty) {
		this.empty = empty;
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public int getQueueSize() {
		return queueSize;
	}
	
	public void startTimer() {
		this.timer = new Timer();
		timer.schedule(new EndGameTask(), 180000);
	}

	public boolean isSuccessfulPlacement(Coordinates coord,int element){
		int row=coord.getRow();
		int col=coord.getCol();
		boolean success=false;
		int sum=0;
		int usefulNeighborCtr=0;
		//get coordinates of all useful neighbors
		//useful neighbors are those which are not empty. 
		ArrayList<Coordinates>usefulNeighbors=getUsefulNeighbors(coord);
		//loop through all useful neighbors and calculate the sum of their values
		for(int i=0;i<usefulNeighbors.size();i++){
			int r=usefulNeighbors.get(i).getRow();
			int c=usefulNeighbors.get(i).getCol();
			//System.out.println("Neighbor: "+tiles[r][c]);
			sum+=tiles[r][c];
			usefulNeighborCtr++;
		}
		//System.out.println(usefulNeighbors.size()+" "+sum);
		//get top element of queue
		int elementPlaced=element;
		//System.out.println("Element : "+elementPlaced);
		//compare value according to given rule
		if(elementPlaced==-1){
			throw new NullPointerException("tilesQueue getQueueElement in successfulplacement null ptr in FOLLOWING IF. Not Working!\n");
		}
		
		else if((sum%10)==elementPlaced){
			success=true;
		}
		
		//method now returns if tiles surrounding the current tile can be removed or not
		return success;
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
		/**
		//all possible combinations for a tile placed in a grid having coordinates {row,col}
		//Logic as follows
		/*
		 * Assume that a tile is placed at location row,col in a matrix
		 * The matrix looks like:
		 * 							row-1,col-1	|	row-1,col	|	row-1,col+1
		 * 							row,col-1	|	row,col		|	row,col+1
		 * 							row+1,col	|	row+1,col	|	row+1,col+1
		 * 
		 * The neighbors will be all the surrounding tiles. 
		 * Now, if the tile is placed at the corners, the values of [i,j] where i belongs to all 
		 * values of rows and j belongs to all values of columns have to be within the limits 
		 * of 0<=row<=8 since it is a 9*9 board. So running a simple if-else conditional structure 
		 * to check these limits for all the above neighbor coordinates can simplify the procedure
		 * WE CAN AVOID THE ABOVE COMMENTED CODE, WHICH IS LENGTHY AND REPETITIVE
		 */
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
		//System.out.println(allNeighbors.size());
		//find all non-empty neighbors
		for(int i=0;i<allNeighbors.size();i++){
			if(tiles[allNeighbors.get(i).getRow()][allNeighbors.get(i).getCol()]!=-1){
				//System.out.println("Neighbor: "+"r :"+allNeighbors.get(i).getRow()+ " c: "+allNeighbors.get(i).getCol());
				usefulNeighbors.add(allNeighbors.get(i));
			}
		}
		//System.out.println(usefulNeighbors.size());
		/*
		 *return only non empty neighbors. This will be useful since in the future, all non-empty neighbors
		 *will be removed if it is a successful placement. Need not check later if they are empty
		 */
		return usefulNeighbors;
	}
		
	public boolean updateTilesinBoard(Coordinates coord){
		
		//first get row and col from the given coordinates 
		int row=coord.getRow();
		int col=coord.getCol();
		//check if the given tile is empty. If not, return false
		//indicating that tile cannot be placed
		if(tiles[row][col]!=-1){
			return false;
		}
		else{
			//tile at given position is updated 
			try {
				removedElement=tilesQueue.dequeue();
				tiles[row][col]=removedElement;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//if untimed game
			if(gameType.equals("untimed")){
				//update moves
				remainingMoves--;
				//check if game is over
				if(remainingMoves==0){
					//update gameOver status
					gameOver=true;
					//update the observable interface
					setChanged();
					notifyObservers();
					//return true since tile was successfully placed
					return true;
				}
			}
		}
		
			//Score calculation
			this.score+=scorePlacement(coord);
			//score calculation ends
			
			//now come to the other tiles/neighbors
			boolean placement=isSuccessfulPlacement(coord,removedElement);
			if(placement==false){//if it is not a successful placement
				empty--;//decrease the number of empty tiles in the board 
			}
			else{//if the placement is successful
				ArrayList<Coordinates> usefulNeighbors=getUsefulNeighbors(coord);//get all the useful neighbors that were removed
				//change the placed tile to empty since it was a successful placement
				//System.out.println(usefulNeighbors.size());
				tiles[row][col]=-1;
				//now loop through all useful neighbors and change their values to empty
				for(int i=0;i<usefulNeighbors.size();i++){
					//System.out.println("Neighbor: "+tiles[usefulNeighbors.get(i).getRow()][usefulNeighbors.get(i).getCol()]);
					tiles[usefulNeighbors.get(i).getRow()][usefulNeighbors.get(i).getCol()]=-1;
				}
				//the number of empty tiles now will be equal to the size of usefulNeighbors.
				//so update the number of empty tiles in the board
				empty+=usefulNeighbors.size();
			}
			
			//tilesQueue update starts
			if(tilesQueue.getSize() < queueSize) {
				populateQueue();
			}
			//tilesQueue update ends
			
			//check game status again
			if(empty==0){//if the number of empty tiles in the board is zero, game is over!
				//SHOULD MODIFY TO CHECK FOR BOTH TIMED AND UNTIMED IN LATER SPRINT CYCLES
				gameOver=true;
				setChanged();
				notifyObservers();
				//return true since tile was updated and some operation took place
				return true;
			}
			else if(empty==81){//since there are 81 tiles in the board. If all are empty, game won!
				//game is won; update that in the model 
				won=true;
				setChanged();
				notifyObservers();
				//return true for updated tiles 
				return true;
			}
			setChanged();
			notifyObservers();
			//if nothing works
			return false;
	}
	
	//
	public void populateQueue(){
		
		Random rand = new Random();
		while(this.tilesQueue.getSize() < this.getQueueSize()){
			try {
				tilesQueue.enqueue(rand.nextInt(10));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Queue initialization/population exception");
			}
		}
	}
	
	//if that returns true the method scores that placement accordingly
	public int scorePlacement(Coordinates coord) {
		int score=0;
		if(isSuccessfulPlacement(coord,removedElement)){
			ArrayList<Coordinates> neighbors = this.getUsefulNeighbors(coord);
			
			//add placed tile value to score
			score+=tiles[coord.getRow()][coord.getCol()].intValue();
			int sum=0;
			//add all neighbors values to score
			for(int i= 0; i < neighbors.size(); i++) {
				//this.setScore(this.score + tiles[neighbors.get(i).getRow()][neighbors.get(i).getCol()].intValue());
				sum+=tiles[neighbors.get(i).getRow()][neighbors.get(i).getCol()];
			}
			score+=sum;
			//add bonus points if applicable
			if(neighbors.size() >= 3) {
				//this.setScore(this.score + (10 * neighbors.size()));
				score+=10*neighbors.size();
			}
		}
		return score;
	}
	
	public Integer[][] getTiles() {
		return tiles;
	}

	public void setTiles(Integer[][] tiles) {
		this.tiles = tiles;
		setChanged();
		notifyObservers();
	}

	public Queue<Integer> getTilesQueue() {
		return tilesQueue;
	}

	public void setTilesQueue(Queue<Integer> tilesQueue) {
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
