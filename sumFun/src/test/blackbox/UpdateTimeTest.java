package blackbox;

import static org.junit.Assert.assertEquals;

import model.Game;

import org.junit.BeforeClass;
import org.junit.Test;



public class UpdateTimeTest {
	static Game testGame;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testGame=Game.getGame("timed");
	}
	
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
	
	@Test
	public void testForB() {
		
		testGame.getTimer().setTimeLimit(0);
		testGame.getTimer().updateTime();
		assertEquals(false,testGame.getTimer().getGameTimer().isRunning());
		assertEquals(true,testGame.isGameOver());
		assertEquals("0 : 00",testGame.getRemainingTime());
	}
	
	
}
