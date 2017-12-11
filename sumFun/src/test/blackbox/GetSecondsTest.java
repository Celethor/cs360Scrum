package blackbox;
import static org.junit.Assert.assertEquals;

import model.Game;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Blackbox testing for getSeconds() method
 * @author Aneesh Raman
 * 
 *  	Condition				Class					Label
 * value of timeLimit			timeLimit<=180			1
 * 								timeLimit>=0			2
 * 								timeLimit>180			3
 * 								timeLimit<0				4
 * 	
 * value of seconds				seconds<10				5
 * 								seconds>=10				6
 * 								not init. 				7
 * 
 * output 						0+toString(seconds)		8
 * 								toString(seconds)		9
 * 								Invalid Time			10
 * 
 * 										
 *	Class				timeLimit	seconds(local)		return value		Label
 *	1,5,8				180				0				"00"				A
 *	1,6,9				179				59				"59"				B
 *	2,5,8				0				0				"00"				C
 *	3,7,10				181				not init.		"Invalid Time"		D
 *	4,7,10				-1				not init.		"Invalid Time"		E	
 */

public class GetSecondsTest {
	static Game testGame;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testGame=Game.getGame("timed");
	}
	/**
	 * Test case for timeLimit=180
	 */
	@Test
	public void testGetSecondsForA() {
		testGame.getTimer().setTimeLimit(180);
		assertEquals("00",testGame.getTimer().getSeconds());
	}
	/**
	 * Test case for timeLimit=179 seconds=59
	 */
	@Test
	public void testGetSecondsForB() {
		testGame.getTimer().setTimeLimit(179);
		assertEquals("59",testGame.getTimer().getSeconds());
	}
	/**
	 * Test case for timeLimit=0
	 */
	@Test
	public void testGetSecondsForC() {
		testGame.getTimer().setTimeLimit(0);
		assertEquals("00",testGame.getTimer().getSeconds());
	}
	/**
	 * Test case for invalid time timeLimit=181
	 */
	@Test
	public void testGetSecondsForD() {
		testGame.getTimer().setTimeLimit(181);
		assertEquals("Invalid Time",testGame.getTimer().getSeconds());
	}
	/**
	 * Test case for invalid time timeLimit=-1
	 */
	@Test
	public void testGetSecondsForE() {
		testGame.getTimer().setTimeLimit(-1);
		assertEquals("Invalid Time",testGame.getTimer().getSeconds());
	}
}
