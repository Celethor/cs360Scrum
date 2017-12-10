package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



public class Leaderboard {

	//creates catch case if file isn't there
	private String gameType;
	private String[][] topScores = {{"Empty","0","N/A"},
						            {"Empty","0","N/A"},
				  		            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"}};
	private Scanner in;;

	public Leaderboard(String gameType) {
		this.gameType = gameType;
		loadScores();
	}

	private void loadScores() {
		File leaderTxt;
		if(gameType.equals("untimed")) {
			leaderTxt = new File("Leaders/leaders.txt");
		} else {
			leaderTxt = new File("Leaders/timeLeaders.txt");
		}
		Scanner scoreReader = null;

		try {
			scoreReader = new Scanner(leaderTxt);
		} catch (FileNotFoundException e) {
			//dd code here to create template for scores if not exists @done
			e.printStackTrace();
		}
		if (gameType.equals("untimed"))
			this.topScores = new String[10][3];
		else
			this.topScores= new String[10][4];

		// line by line read name, score, and date into the array
		for (int i = 0; scoreReader.hasNextLine(); i++) {
			this.topScores[i][0] = scoreReader.next();
			this.topScores[i][1] = scoreReader.next();
			this.topScores[i][2] = scoreReader.next();
			if(gameType.equals("timed")) {
				this.topScores[i][3] = scoreReader.next();
			}
		}

		scoreReader.close();
	}

	public void saveScores() {
		FileWriter scoreWriter = null;
		try {
			File scoreFile = new File("Leaders/leaders.txt");
			
			if(gameType.equals("timed")) {
				scoreFile = new File("Leaders/timeLeaders.txt");
			}
			
			scoreWriter = new FileWriter(scoreFile, false); // true = append, false = overwrite
			
			for(int i = 0; i < topScores.length; i++) {
				scoreWriter.write(topScores[i][0] + " " +
								  topScores[i][1] + " ");
					
				if(gameType.equals("untimed")) {
					if(i<9) {
						scoreWriter.write(topScores[i][2] + "\n");
					} else {
						scoreWriter.write(topScores[i][2]);
					}
				//case for timed games
				} else {
					scoreWriter.write(topScores[i][2] + " ");

					if(i<9) {
						scoreWriter.write(topScores[i][3] + "\n");
					} else {
						scoreWriter.write(topScores[i][3]);
					}
				}
			}
			scoreWriter.close();
			
		} catch (IOException e) {
			// add case to create file and call same method again
			e.printStackTrace();
		}		
	}

	public String[][] getScores() {
		return this.topScores;
	}
	
	//converts time left string to seconds
	private int convertToSeconds(String timeLeft) {
		int timeLeftS = 0;
		
		String[] parts = timeLeft.split(":");
		
		timeLeftS += 60 * Integer.parseInt(parts[0]);
		timeLeft += Integer.parseInt(parts[1]);
		
		return timeLeftS;
	}
	
	//convert time in seconds to X:XX format
	public String convertToDigital() {
		String digitalTime = "";
		
		
		return digitalTime;
	}

	//validate whether new high score was set
	public boolean checkScore(int score) {
		//check if passed score shouldn't make it on the list, if not return false
		if(score <= Integer.parseInt(topScores[9][1])) {
			return false;
		}
		return true;
	}
	
	//validate whether new low time was set
	public boolean checkTime(int seconds) {
		//check if passed score shouldn't make it on the list, if not return false
		if(seconds >= convertToSeconds(topScores[9][3])) {
			return false;
		}
		return true;
	}
	
	// add a score to the list	
	public void addScore(String name, int score){
		
		//get current Date
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now(); //11/19/2017
		
		//create score slot to insert
		String[] newHighScore = {name, Integer.toString(score), dtf.format(localDate)};
		
		//need temp var to store misplaced scores
		String[] tempScore = {null, null, null};
		
		
		int i;
		//will run through this.topScores and insert newHigh score into list and move rest of list down
		for(i = 0; i<topScores.length; i++) {
			//find where new score should be inserted
			if(Integer.parseInt(newHighScore[1]) > Integer.parseInt(topScores[i][1])){
				tempScore = topScores[i];
				topScores[i] = newHighScore;
				newHighScore = tempScore;
				i++;
				break;
			}
		}
		
		//move the rest of the list down
		for(;i<topScores.length;i++){
			tempScore = topScores[i];
			topScores[i] = newHighScore;
			newHighScore = tempScore;
		}
		
		
		return;
	}
	
	//ad a new score from a timed game.
	public void addScore(String name, int score, String timeLeft) {
		
		//get current Date
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now(); //11/19/2017
		
		//create score slot to insert
		String[] newHighScore = {name, Integer.toString(score), dtf.format(localDate), timeLeft};
		
		//need temp var to store misplaced scores
		String[] tempScore = {null, null, null, null};
		
		
		int i;
		//will run through this.topScores and insert newHigh score into list and move rest of list down
		for(i = 0; i<topScores.length; i++) {
			//find where new score should be inserted
			if(convertToSeconds(newHighScore[3]) < convertToSeconds(topScores[i][3])){
				tempScore = topScores[i];
				topScores[i] = newHighScore;
				newHighScore = tempScore;
				i++;
				break;
			}
		}
		
		//move the rest of the list down
		for(;i<topScores.length;i++){
			tempScore = topScores[i];
			topScores[i] = newHighScore;
			newHighScore = tempScore;
		}
		
		
		return;
	}
	
	public String toString(){
		String scores = "";
		
		if(gameType.equals("untimed")) {
			//build string
			for(int i = 0;i<topScores.length; i++) {
				scores += "\n" + "(" + Integer.toString(i+1) + ") "
						+ topScores[i][0] + "\t"
						+ topScores[i][1] + "\t";
				
				if(i<9) {
					scores += topScores[i][2] + "\n";
				} else {
					scores += topScores[i][2];
				}			
			}
		} else {
			//build string
			for(int i = 0;i<topScores.length; i++) {
				scores += "\n" + "(" + Integer.toString(i+1) + ") "
						+ topScores[i][0] + "\t"
						+ topScores[i][3] + "\t";
				
				
				
				scores += topScores[i][1] + "\t";

				if(i<9) {
					scores += topScores[i][2] + "\n";
				} else {
					scores += topScores[i][2];
				}
			}
				
			
		}
		
		
		return scores;
	}
}