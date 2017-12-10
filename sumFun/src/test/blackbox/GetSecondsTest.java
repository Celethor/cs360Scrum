package blackbox;
import static org.junit.Assert.assertEquals;

import model.Game;

import org.junit.BeforeClass;
import org.junit.Test;



public class GetSecondsTest {
	static Game testGame;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testGame=Game.getGame("timed");
	}

	@Test
	public void testGetSecondsForA() {
		testGame.getTimer().setTimeLimit(129);
		assertEquals("09",testGame.getTimer().getSeconds());
	}
	@Test
	public void testGetSecondsForB() {
		testGame.getTimer().setTimeLimit(179);
		assertEquals("59",testGame.getTimer().getSeconds());
	}
	@Test
	public void testGetSecondsForC() {
		testGame.getTimer().setTimeLimit(181);
		assertEquals("Invalid Time",testGame.getTimer().getSeconds());
	}
	@Test
	public void testGetSecondsForD() {
		testGame.getTimer().setTimeLimit(-1);
		assertEquals("Invalid Time",testGame.getTimer().getSeconds());
	}

}
