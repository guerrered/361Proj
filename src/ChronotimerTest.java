
import org.junit.Assert.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Event.*;

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
		assertTrue(rIND.remove(0));
		assertFalse(rIND.remove(0));
	}
	
	@Test
	public void testSwapPlayers(){
		//swap should fail if neither are running
		assertFalse(rIND.swap(0, 1));
		//start 0
		rIND.start(System.currentTimeMillis());
		//start 1
		rIND.start(System.currentTimeMillis());
		
		assertTrue(rIND.swap(0, 1));
	}

}
