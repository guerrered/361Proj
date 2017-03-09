
import org.junit.Assert.*;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

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
	@Before
	public void setup() {
		rIND = new RaceIndependent();
		pIND = new parallelIndependent();
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
