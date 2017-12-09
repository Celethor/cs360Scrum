package whitebox;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Game;
import model.Leaderboard;

//Aneesh 1002 11/20/2017
//Aneesh 1000 11/20/2017
//Benjamin 838 11/19/2017
//Aneesh 836 11/20/2017
//Aneesh 798 11/19/2017
//Aneesh 654 11/19/2017
//Matt 640 11/19/2017
//Matt 630 11/19/2017
//Matt 620 11/19/2017
//Matt 610 11/19/2017



public class TestAddScore {
	/**
	 * Three situations to consider all with referentce to input parameter int score
	 * A --> score is higher than all scores on leaderboard
	 * B --> score is somewhere in the middle
	 * C --> score is not high enough to be included in the list
	 */
	
	static Leaderboard topScoresA;
	static Leaderboard topScoresB;
	static Leaderboard topScoresC;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		topScoresA = new Leaderboard("untimed");
		topScoresB = new Leaderboard("untimed");
		topScoresC = new Leaderboard("untimed");
	}
	
	/**
	 * ADD SCORES TEST A
	 * the first scenario is score is higher than all on 
	 * current leaderboard 
	 * addScore("TestA, 1010")
	 */
	
	@Test
	public void testAddScoreStringInt_A() {
		topScoresA.addScore("TestA", 1010);
		assertEquals(Integer.parseInt(topScoresA.getScores()[0][1]), 1010);
	}
	
	/**
	 * ADD SCORES TEST B
	 * 
	 * Score is somewhere in the middle of the leaderboard;
	 * addScore("TestA, 653");
	 */
	@Test
	public void testAddScoreStringInt_B() {
		topScoresB.addScore("TestB", 653);
		assertEquals(Integer.parseInt(topScoresB.getScores()[6][1]), 653);
	}
	
	/**
	 * ADD SCORES TEST C
	 * 
	 * Score is not high enough to be on the leaderboard;
	 * addScore("TestA, 1");
	 */
	@Test
	public void testAddScoreStringInt_C() {
		topScoresB.addScore("TestC", 1);
		assertNotEquals(Integer.parseInt(topScoresB.getScores()[9][1]), 1);
	}
}
