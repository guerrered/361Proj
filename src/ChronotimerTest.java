
import org.junit.Assert.*;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import Chronotimer.Channels;
import Chronotimer.Channels.Channel;
import Chronotimer.Console;
import Event.*;
/**
 * 
 * @author HiddenBit
 *
 */

/**
 * JUnit test setup for testing
 *
 */
public class ChronotimerTest {
	RaceIndependent rIND;
	parallelIndependent pIND;
	Console console;
	@Before
	public void setup() {
		rIND = new RaceIndependent();
		pIND = new parallelIndependent();
		console = new Console();
	}
	
	@Test
	public void testDefaults(){
		/*
		 * From Sprint0 document:
		 *The default type of competition is IND.
		 *The default run number is	1.
         *Channels default to “disarmed” on	power up
		 */
		assertFalse(console.powerState);
		//turn on console
		console.Power();
		
		assertEquals("IND",console.getRaceType());
		assertEquals(1,console.getRaceNum());
		Channels c = console.getChannels();
		for(int i=1; i<=8; i++){
			assertFalse(c.getCh(i).connected());
		}
	}
	
	
	@Test
	public void testMakeDirectory(){
		//if there is a directory, delete it so we can test directory creation.
		File dir = new File("USB");
		if(dir.exists()){
			for(File f: dir.listFiles()){
				f.delete(); 
			}
			dir.delete();
		}
		
		assertFalse(dir.exists());
		
		rIND.createRaceOutputFile();
		assertTrue(dir.exists());
	}


}
