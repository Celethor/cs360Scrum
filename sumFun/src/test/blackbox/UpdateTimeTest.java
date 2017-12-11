package blackbox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import model.Game;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Blackbox testing for updateTime() method of Game.GameTimer class 
 * @author Aneesh Raman
 * 
 * Cases 					Class		 	Label
 * value of timeLimit	timeLimit<=180		  1
 * 						timeLimit>180		  2
 * 
 * value of timeLimit	timeLimit>0			  3
 * 						timeLimit<=0		  4
 * 
 * value of timeLeft	getMinutes+" : "+	  5
 * 						getSeconds
 * 						null				  6
 * 
 * timer stopped		true				  7
 * 						false				  8
 * 
 * gameOver				true				  9
 * 						false				  10
 * 
 * remainingTime		remainingTime=
 * 						getTimeLeft()		  11
 * 						null				  12
 * 
 * 										Test Cases
 * 							**********************************
 * Equivalence Class	timeLimit(initial)	timeLimit(final)	timeLeft	timerStopped	gameOver	remainingTime	Case 
 * 
 * 1,3,5,8,10,11			180					179					2:59		false		false		2:59			A
 * 1,4,6,7,9,11				0					0					null		true		true		0:00			B
 * 2,4,8,10,12				181					181					null		false		false		null			C
 * 
 * 
 * v
 */

public class UpdateTimeTest {
	static Game testGame;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testGame=Game.getGame("timed");
	}
	
	/**
	 * Test case :  TimeLimit = 180 for boundary value analysis
	 */
	@Test
	public void testForA() {
		testGame.getTimer().setTimeLimit(180);
		testGame.getTimer().updateTime();
		assertEquals("179",Integer.toString(testGame.getTimer().getTimeLimit()));
		assertEquals("2",testGame.getTimer().getMinutes());
		assertEquals("59",testGame.getTimer().getSeconds());
		assertEquals("2 : 59",testGame.getTimer().getTimeLeft());
		assertEquals("2 : 59",testGame.getRemainingTime());
	}
	
	/**
	 * Test case : TimeLimit=0 for boundary value analysis
	 */
	@Test
	public void testForB() {
		
		testGame.getTimer().setTimeLimit(0);
		testGame.getTimer().updateTime();
		assertEquals(false,testGame.getTimer().getGameTimer().isRunning());
		assertEquals(true,testGame.isGameOver());
		assertEquals("0 : 00",testGame.getRemainingTime());
	}
	
	/**
	 * Test case : timeLimit>180
	 */
	@Test
	public void testForC() {
		testGame.clear();
		testGame=Game.getGame("timed");
		testGame.getTimer().setTimeLimit(181);
		testGame.getTimer().updateTime();
		assertEquals(testGame.getTimer().getTimeLimit(),181);
		assertNull(null,testGame.getTimer().getTimeLeft());
		assertEquals(true,testGame.getTimer().getGameTimer().isRunning());
		assertNull(testGame.getRemainingTime(),null);
		assertEquals(false,testGame.isGameOver());
	}
	
}
