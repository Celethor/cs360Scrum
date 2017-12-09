package whitebox;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Game;

public class TestUpdateTime {
	
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
	}

}
