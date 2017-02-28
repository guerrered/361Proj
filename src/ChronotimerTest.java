import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Event.RaceIndependent;

public class ChronotimerTest {
	RaceIndependent rIND;
	@Before
	public void setup() {
		rIND = new RaceIndependent();
	}
	
	@Test
	public void testRemovePlayer(){
		assertTrue(rIND.remove(0));
	}
	
	@Test
	public void testRemovePlayerTwice(){
		assertTrue(rIND.removeRunner(0));
		assertFalse(rIND.removeRunner(0));
	}
	
	@Test
	public void testSwapPlayers(){
		//swap should fail if neither are running
		assertFalse(rIND.swapRacers(0, 1));
		//start 0
		rIND.startIND(System.currentTimeMillis());
		//start 1
		rIND.startIND(System.currentTimeMillis());
		
		assertTrue(rIND.swapRacers(0, 1));
	}

}
