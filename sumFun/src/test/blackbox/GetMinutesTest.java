package blackbox;
import static org.junit.Assert.assertEquals;

import model.Game;

import org.junit.BeforeClass;

import org.junit.Test;

public class GetMinutesTest {
	static Game test;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		test=Game.getGame("timed");
	}

	@Test
	public void testForA() {
		test.getTimer().setTimeLimit(179);
		assertEquals("2",test.getTimer().getMinutes());
	}
	@Test
	public void testForB() {
		test.getTimer().setTimeLimit(1);
		assertEquals("0",test.getTimer().getMinutes());
	}
	@Test
	public void testForC() {
		test.getTimer().setTimeLimit(181);
		assertEquals("Invalid Time",test.getTimer().getMinutes());
	}
	@Test
	public void testForD() {
		test.getTimer().setTimeLimit(-1);
		assertEquals("Invalid Time",test.getTimer().getMinutes());
	}


}
