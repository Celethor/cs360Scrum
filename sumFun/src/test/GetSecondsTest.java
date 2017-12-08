

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Game;

public class GetSecondsTest {
	static Game testGame;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testGame=Game.getGame("timed");
	}

	@Before
	public void setUp() throws Exception {
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
