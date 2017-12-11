package whitebox;
import static org.junit.Assert.assertEquals;

import model.Game;

import org.junit.BeforeClass;
import org.junit.Test;



public class TestUpdateTime {
	
	/**
	 * Whitebox testing, Independent Path testing, for updateTime
	 * 
	 * Update time checks current amount of time left in the game and updates the time accordingly
	 * If the time limit is up or at 0 then the game is over.
	 * If time limit is not up then the limeLimit is reduced by one.
	 * To test all paths it was necessary for us to use two cases with input TimeLimit > 0 and TimeLimit = 0.
	 * 
	 * Test Cases:
	 * 
	 * TimeLimit(Before)	TimeLimit(After)	TimeRemaining(Before)	TimeRemaining(After)	Case
	 *      60					59						"1 : 00"				"0 : 59"		  A
	 *       0					0						"0 : 00"				"0 : 00" 		  B
	 * 
	 */
	
	static Game test;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		test=Game.getGame("timed");
	}
	
	/**
	 * UPDATE TIME TEST A
	 * 
	 * timeLimit = 60
	 * 	isGameOver == false if timeLimit > 0
	 * 	timeLimit (after) == 59
	 */
	
	@Test
	public void testUpdateTime_A() {
		test.getTimer().setTimeLimit(60);
		test.getTimer().updateTime();
		assertEquals(test.isGameOver(), false);
		assertEquals(test.getTimer().getTimeLimit(), 59);
		assertEquals("0 : 59",test.getRemainingTime());
	}
	
	/**
	 * UPDATE TIME TEST B
	 * 
	 * timeLimit = 0
	 * 	isGameOver == true if timeLimit = 0
	 * 	timeLimit (after) = 0
	 */
	
	@Test
	public void testUpdateTime_B() {
		test.getTimer().setTimeLimit(0);
		test.getTimer().updateTime();
		assertEquals(test.isGameOver(), true);
		assertEquals(test.getTimer().getTimeLimit(), 0);
		assertEquals("0 : 00",test.getRemainingTime());
	}

}
