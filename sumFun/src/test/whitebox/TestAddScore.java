package whitebox;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;



import org.junit.BeforeClass;
import org.junit.Test;



//Organzation and structure of leaders.txt before testing begins
//Assumptions will be made that it is already in this state
//
//EACH LEADERBOARD ARRAY (A, B, C) INITIALIZED WITH THIS TEXT FILE OF THESE CONTENTS
//IF RUNNING TESTS CHANGE leaders.txt ACCORDINGLY
//
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
	static Leaderboard topScoresD;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		topScoresA = new Leaderboard("untimed");
		topScoresB = new Leaderboard("untimed");
		topScoresC = new Leaderboard("untimed");
		topScoresD = new Leaderboard("untimed");
	}
	
	/**
	 * ADD SCORES TEST A
	 * the first scenario is score is higher than all on 
	 * current leaderboard 
	 * addScore("TestA, 1010")
	 * this should be the top score and thus be located 
	 * at topScoresA[0][1] = 1010
	 * everything else should remain move down one spot
	 */
	
	@Test
	public void testAddScoreStringInt_A() {
		topScoresA.addScore("TestA", 1010);
		assertEquals(Integer.parseInt(topScoresA.getScores()[0][1]), 1010);
		assertEquals(Integer.parseInt(topScoresA.getScores()[1][1]), 1002);
		assertEquals(Integer.parseInt(topScoresA.getScores()[2][1]), 1000);
		assertEquals(Integer.parseInt(topScoresA.getScores()[3][1]), 838);
		assertEquals(Integer.parseInt(topScoresA.getScores()[4][1]), 836);
		assertEquals(Integer.parseInt(topScoresA.getScores()[5][1]), 798);
		assertEquals(Integer.parseInt(topScoresA.getScores()[6][1]), 654);
		assertEquals(Integer.parseInt(topScoresA.getScores()[7][1]), 640);
		assertEquals(Integer.parseInt(topScoresA.getScores()[8][1]), 630);
		assertEquals(Integer.parseInt(topScoresA.getScores()[9][1]), 620);
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
		//score added
		assertEquals(Integer.parseInt(topScoresB.getScores()[6][1]), 653);
		
		//remaining elements of the list
		assertEquals(Integer.parseInt(topScoresB.getScores()[0][1]), 1002);
		assertEquals(Integer.parseInt(topScoresB.getScores()[1][1]), 1000);
		assertEquals(Integer.parseInt(topScoresB.getScores()[2][1]), 838);
		assertEquals(Integer.parseInt(topScoresB.getScores()[3][1]), 836);
		assertEquals(Integer.parseInt(topScoresB.getScores()[4][1]), 798);
		assertEquals(Integer.parseInt(topScoresB.getScores()[5][1]), 654);
		assertEquals(Integer.parseInt(topScoresB.getScores()[7][1]), 640);
		assertEquals(Integer.parseInt(topScoresB.getScores()[8][1]), 630);
		assertEquals(Integer.parseInt(topScoresB.getScores()[9][1]), 620);
		
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
		assertEquals(Integer.parseInt(topScoresC.getScores()[0][1]), 1002);
		assertEquals(Integer.parseInt(topScoresC.getScores()[1][1]), 1000);
		assertEquals(Integer.parseInt(topScoresC.getScores()[2][1]), 838);
		assertEquals(Integer.parseInt(topScoresC.getScores()[3][1]), 836);
		assertEquals(Integer.parseInt(topScoresC.getScores()[4][1]), 798);
		assertEquals(Integer.parseInt(topScoresC.getScores()[5][1]), 654);
		assertEquals(Integer.parseInt(topScoresC.getScores()[6][1]), 640);
		assertEquals(Integer.parseInt(topScoresC.getScores()[7][1]), 630);
		assertEquals(Integer.parseInt(topScoresC.getScores()[8][1]), 620);
		assertEquals(Integer.parseInt(topScoresC.getScores()[9][1]), 610);
	}
	
	/**
	 * ADD SCORES TEST D
	 * 
	 * topScores object is null. The final path where the initial for loop will 
	 * not execute is the array is null in the beginning. ;
	 *
	 */
	@Test
	public void testAddScoreStringInt_D() {
		assertNotNull("true",topScoresD.getScores());
	}
}
