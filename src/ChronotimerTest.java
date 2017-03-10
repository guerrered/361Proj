
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
         *Channels default to �disarmed� on	power up
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
	public void testAddTwice(){
		rIND.addRunner(1);
		//ID:1 is already in the list
		assertFalse(rIND.addRunner(1));
	}
	
	@Test
	public void testRemove(){
		rIND.addRunner(1);
		assertTrue(rIND.remove(1));
		assertFalse(rIND.contains(1));
	}
	
	//ensures program won't break down
	@Test
	public void testRemoveTwice(){
		rIND.addRunner(1);
		assertTrue(rIND.remove(1));
		assertFalse(rIND.remove(1));
		
	}
	
	@Test
	public void testSwapIND(){
		//start two racers
		
		rIND.start(System.currentTimeMillis());
		rIND.start(System.currentTimeMillis());
		assertTrue(rIND.swap());
		try {
			//pause
			Thread.sleep(10);
			rIND.finish(System.currentTimeMillis());
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rIND.finish(System.currentTimeMillis());
		//the first player should have a longer total time than the player that started second.
		assertTrue(rIND.getPlayerList().get(0).totalTime<rIND.getPlayerList().get(1).totalTime);
	}
	
	@Test
	public void testSwapBeforeRaceIND(){
		assertFalse(rIND.swap());
		
		rIND.start(System.currentTimeMillis());
		assertFalse(rIND.swap());
		
		rIND.start(System.currentTimeMillis());
		assertTrue(rIND.swap());
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
	
	@Test
	public void testRunAllPlayersIND(){
		for(int i = 0; i < 9999; i++){
			rIND.start(0);
			rIND.finish(1);
		}
		assertFalse(rIND.start(0));//max bib num is 9999 so 10000 should not run
	}
	
	@Test
	public void testRunAllPlayersPARIND(){
		for(int i = 0; i < 9999; i++){
			pIND.start(0 , 1);
			pIND.finish(1, 1);
		}
		assertFalse(pIND.start(0,1));//max bib num is 9999 so 10000 should not run
	}
	
	@Test
	public void testDNFIND(){
		rIND.start(0);
		rIND.DNF();
		Player tested = rIND.getRacer(0);
		assertTrue(tested.DNF);
		
	}
	@Test
	public void testDNFPARIND(){
		pIND.start(0,1);//runner in lane 1
		pIND.DNF(1);
		Player tested = pIND.getRacer(0);
		assertTrue(tested.DNF);
		
		pIND.start(0,2);//runner in lane 2
		pIND.DNF(2);
		Player tested2 = pIND.getRacer(1);
		assertTrue(tested2.DNF);
	}
	
	@Test
	public void testCancelIND(){
		Player canceled = rIND.getRacer(0);
		rIND.start(0);
		rIND.cancel();
		assertFalse(canceled.isRunning());
	}
	
	@Test
	public void testCancelPARIND(){
		Player canceled = pIND.getRacer(0);//tests cancel on first player
		pIND.start(0,1);
		pIND.cancel();
		assertFalse(canceled.isRunning());
		
		pIND.start(0,1);//tests cancel in separate lanes
		pIND.start(0,2);
		Player canceled2 = pIND.getRacer(1);
		pIND.cancel();
		assertFalse(canceled2.isRunning());
		pIND.finish(1);
		
		pIND.start(0,1);//test cancel for runners in same lane
		Player canceled3 = pIND.getRacer(2);
		pIND.start(0,1);
		pIND.cancel();
		assertFalse(canceled3.isRunning());
	}
	
	@Test
	public void testNoFinishNoRunner(){
		assertFalse(rIND.finish(0)); //finish when no runners have started
		assertFalse(pIND.finish(0,1));
		rIND.start(0);
		pIND.start(0,1);
		rIND.finish(1);
		assertFalse(pIND.finish(1,2));//finish on 2nd lane when there is runner in first
		assertFalse(rIND.finish(1));//finish after last runner running finished
		pIND.finish(0,1);
		assertFalse(pIND.finish(0,1));//finish after last runner running finished parallel
	}
	
	@Test
	public void testRunSameTwice(){
		rIND.start(0);
		rIND.finish(1);
		
		//can't run the same race again.
		assertFalse(rIND.next(0));
	}
	
	@Test
	public void testNextUpWhileRunning(){
		//By default #0 runs first.
		rIND.start(0);
		//Try to get #0 to restart.
		assertFalse(rIND.next(0));
	}
	
	//NUM command
	@Test
	public void testRunNext(){
		//by default, bib #0 runs first so we'll try to have bib #3 start.
		
		rIND.next(3);
		rIND.start(0);
		rIND.finish(1);
		
		assertTrue(rIND.getPlayerList().get(0).ran);
		assertEquals(rIND.getPlayerList().get(0).getID(),3);
	}
	@Test
	public void testSensorTest()
	{
		console.Power();
		console.Connect("eye", 1);
		Channels chs=console.getChannels();
		Channel ch=chs.getCh(1);
		assertTrue(ch.getSens()!=null);
		assertEquals("eye".toUpperCase(),ch.getSens().getClass().getSimpleName());
		ch.removeSens();
		assertTrue(ch.getSens()==null);
		console.Connect("pad", 1);
		assertEquals("pad".toUpperCase(),ch.getSens().getClass().getSimpleName());
		ch.removeSens();
		assertTrue(ch.getSens()==null);
		console.Connect("gate", 1);
		assertEquals("gate".toUpperCase(),ch.getSens().getClass().getSimpleName());
		ch.removeSens();
		assertTrue(ch.getSens()==null);
	}
	
	
	@Test
	public void testSensorTrig()
	{
		console.Power();
		console.Connect("eye", 1);
		console.Connect("eye", 2);
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		assertTrue(console.race.getPlayerList().get(0).ran);
		
		console.getChannels().getCh(1).removeSens();
		console.getChannels().getCh(2).removeSens();
		console.Connect("PAD", 1);
		console.Connect("pad", 2);
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		assertTrue(console.race.getPlayerList().get(1).ran);
		
		console.getChannels().getCh(1).removeSens();
		console.getChannels().getCh(2).removeSens();
		console.Connect("Gate", 1);
		console.Connect("gate", 2);
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		assertTrue(console.race.getPlayerList().get(2).ran);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSensorIllegal() 
	{
		console.Power();
		console.Connect("yes", 1);
	}
	
	@Test
	public void testSensorAfterPowerOff()
	{
		console.Power();
		console.Connect("eye", 1);
		console.Connect("eye", 2);
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		assertTrue(console.race.getPlayerList().get(0).ran);
		console.Power();
		console.Power();
		assertEquals(null,console.getChannels().getCh(1).getSens());
		assertEquals(null,console.getChannels().getCh(2).getSens());
		assertFalse(console.race.getPlayerList().get(0).ran);
		
	}
	
	@Test
	public void testSensorInParaRace()
	{
		console.Power();
		console.endRun();
		console.Event("PARIND");
		console.Connect("pad", 1);
		console.Connect("pad", 2);
		console.Connect("gate", 3);
		console.Connect("gate", 4);
		console.newRun();
		assertFalse(console.race.getPlayerList().get(0).participated());
		assertFalse(console.race.getPlayerList().get(1).participated());
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		assertTrue(console.race.getRacer(0).participated());
		assertTrue(console.race.getRacer(0).ran);
		assertFalse(console.race.getRacer(1).participated());
		
	}
	
	

}
