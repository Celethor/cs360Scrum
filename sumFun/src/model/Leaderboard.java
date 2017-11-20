package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Leaderboard {

	//creates catch case if file isn't there
	private String[][] topScores = {{"Empty","0","N/A"},
						            {"Empty","0","N/A"},
				  		            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"},
						            {"Empty","0","N/A"}};;

	public Leaderboard() {
		loadScores();
	}

	private void loadScores() {

		File leaderTxt = new File("Leaders/leaders.txt");
		Scanner scoreReader = null;

		try {
			scoreReader = new Scanner(leaderTxt);
		} catch (FileNotFoundException e) {
			//dd code here to create template for scores if not exists @done
			e.printStackTrace();
		}

		this.topScores = new String[10][3];

		// line by line read name, score, and date into the array
		for (int i = 0; scoreReader.hasNextLine(); i++) {
			this.topScores[i][0] = scoreReader.next();
			this.topScores[i][1] = scoreReader.next();
			this.topScores[i][2] = scoreReader.next();
		}

		scoreReader.close();
	}

	private void saveScores() {
		FileWriter scoreWriter = null;
		try {
			scoreWriter = new FileWriter(new File("Leaders/leaders.txt"), false); // true = append, false = overwrite
			
			for(int i = 0; i < topScores.length; i++) {
				scoreWriter.write(topScores[i][0] + " " +
								  topScores[i][1] + " ");
				
				if(i<9) {
					scoreWriter.write(topScores[i][2] + "\n");
				} else {
					scoreWriter.write(topScores[i][2]);
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

	// add a score to the list
	public boolean addScore(int score){
		
		//check if passed score shouldn't make it on the list, if not return false
		if(score < Integer.parseInt(topScores[9][1])) {
			return false;
		}
		
		//if it should, then find where it should go and replace others accordingly
		String[] newHighScore = {JOptionPane.showInputDialog(null, "Please enter your name:", "New High Score!", JOptionPane.PLAIN_MESSAGE),
														 Integer.toString(score),
														 "temp"};
		//get current Date
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = LocalDate.now();
		newHighScore[2] = dtf.format(localDate); //11/19/2017
		
		
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
		
		saveScores();
		return true;
	}
	
	public String toString(){
		String scores = "";
		
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

		return scores;
	}
}

