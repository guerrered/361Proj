
import org.junit.Assert.*;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Chronotimer.Channels;
import Chronotimer.Channels.Channel;
import Chronotimer.Console;
import Chronotimer.ExportObject;
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
	Independent rIND;
	parallelIndependent pIND;
	Group group;
	Console console;
	@Before
	public void setup() {
		rIND = new Independent();
		pIND = new parallelIndependent();
		group = new Group();
		console = new Console();
	}
	
	@Test
	public void testDefaults(){
		/*
		 * From Sprint0 document:
		 *The default type of competition is IND.
		 *The default run number is	1.
         *Channels default to ���disarmed��� on	power up
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
	
	@Test
	public void testLoad() throws IOException{
		File file;
		console.Power();
		console.Connect("pad", 1);
		console.Connect("pad", 2);
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		
		
		File dir = new File("USB");
		if(dir.exists()){
			for(File f: dir.listFiles()){
				f.delete(); 
			}
			dir.delete();
		}
		file = console.export();
		List<ExportObject> eo = new ArrayList<ExportObject>();
		eo = console.load(file);
		
		for(ExportObject exportObject : eo){
			exportObject.printEO();
		}
		
	}
	@Test
	public void testLoadMultiple() throws IOException{
		console.Power();
		File file;
		console.Connect("pad", 1);
		console.Connect("pad", 2);
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		
		File dir = new File("USB");
		if(dir.exists()){
			for(File f: dir.listFiles()){
				f.delete(); 
			}
			dir.delete();
		}
		file = console.export();
		List<ExportObject> eo = new ArrayList<ExportObject>();
		eo = console.load(file);
		
		for(ExportObject exportObject : eo){
			exportObject.printEO();
		}
		
		console.endRun();
		console.newRun();
		console.Connect("pad", 1);
		console.Connect("pad", 2);
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		
		file =console.export();
		eo = console.load(file);
		
		for(ExportObject exportObject : eo){
			exportObject.printEO();
		}
		
	}
	
	@Test
	public void testClearSavedData() throws IOException{
		File dir = new File("USB");
		console.clearSavedData();
		assertTrue(dir.listFiles().length==0);
		
	}
	
	@Test
	public void testBlankRun(){
		if(!console.powerState){
			console.Power();
			console.Reset();
		}
		console.newRun();
		console.endRun();
		
		assertEquals(Event.fileNumber,1);
		
		
	}
	
	@Test
	public void testAddNewRunCurrentRun(){
		console.Power();
		assertFalse(console.newRun());//cant create new run while current is on
	}
	@Test
	public void testExportEventCodes(){
		if(!console.powerState){
			console.Power();
			console.Reset();
		}
		console.newRun();
		console.Connect("pad", 1);
		console.Connect("pad", 2);
		
		//first runs normally
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		
		//DNF
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.DNF();
		
		//In progress
		console.getChannels().getCh(1).getSens().notifyObserver();
		
		
		//cancelled run
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.Cancel();
		
		
		
		console.endRun();
		

		
		
	}
	
	@Test
	public void testChangeEventCurrentRun(){
		console.Power();
		assertEquals("IND", console.getRaceType());
		assertFalse(console.Event("PARIND"));//cant change event
		assertTrue(console.endRun());
		assertTrue(console.Event("PARIND"));//event changes
		assertEquals("PARIND", console.getRaceType());
	}
	
	@Test
	public void testChangePowerState(){
		assertTrue(console.Power());
		assertFalse(console.Power());
	}
	@Test
	public void testEndRun(){
		console.Power();
		assertTrue(console.endRun()); 
		assertFalse(console.endRun());//cant end run if no run
	}
	
	@Test
	public void testReset(){
		console.Power();
		console.endRun();
		console.Event("PARIND");
		assertEquals("PARIND", console.getRaceType());
		console.newRun();
		console.endRun();
		console.newRun();
		assertNotEquals(1, console.race.getFileNumber());//multiple races have been created
		assertTrue(console.Reset());
		assertEquals("IND", console.getRaceType());//event should revert to IND after reset
		assertEquals(1, console.race.getFileNumber());//race # reset to 1
		console.Power();
		assertFalse(console.Reset());//cant reset if power off
	}
	
	@Test
	public void testTog(){
		console.Power();
		for(int i = 1; i <= 8 ; i++){//channel on
			assertTrue(console.Tog(i));
		}
		for(int i = 1; i <= 8 ; i++){//channels off
			assertFalse(console.Tog(i));
		}
	}
	@Test
	public void testTriggerChannelONOFF(){
		console.Power();
		for(int i = 1; i <= 8 ; i++){//channel on
			console.Tog(i);
		}
		for(int i = 1; i <= 8; i++){
			assertTrue(console.Trig(i));
		}
		for(int i = 1; i <= 8 ; i++){//channel off
			console.Tog(i);
		}
		for(int i = 1; i <= 8; i++){
			assertFalse(console.Trig(i));
		}	
	}
	
	@Test
	public void testTriggerNoRun(){
		console.Power();
		console.endRun();
		for(int i = 1; i <= 8 ; i++){//channel on
			console.Tog(i);
		}
		for(int i = 1; i <= 8; i++){
			assertFalse(console.Trig(i)); //no race nothing to receive trigger signal
		}
	}
	
	@Test
	public void testTrigPowerOFF(){
		for(int i = 1; i <= 8; i++){
			assertFalse(console.Trig(i)); //console off no trigger heard
		}
	}
	
	@Test
	public void testSensorHandleInorderTriger()
	{
		console.Power();
		console.Connect("eye", 1);
		console.Connect("eye", 2);
		console.getChannels().getCh(2).getSens().notifyObserver();
		assertFalse(console.race.getPlayerList().get(0).ran);
		
	}
	
	@Test
	public void testSensorHandleInorderTrigerPar()
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
		console.getChannels().getCh(2).getSens().notifyObserver();
		console.getChannels().getCh(4).getSens().notifyObserver();
		assertFalse(console.race.getRacer(0).participated());
		assertFalse(console.race.getRacer(0).ran);
		assertFalse(console.race.getRacer(1).participated());
		
	}
	
	@Test
	public void testGroupSimple(){
		console.Power();
		console.endRun();
		console.Connect("pad", 1);
		console.Connect("gate", 2);
		console.Event("GROUP");
		assertEquals("GROUP", console.getRaceType());
		console.getChannels().getCh(1).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		console.getChannels().getCh(2).getSens().notifyObserver();
		
		console.endRun();
		
	}
	
	@Test
	public void testGroupSecondStart(){
		console.Power();
		console.endRun();
		group.start(0);
		assertFalse(group.start(0));
		group.finish(1);
		assertFalse(group.start(0));
	}
	
	

}
