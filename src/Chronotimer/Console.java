package Chronotimer;
import Event.*;
/**
 * 
 * @author HiddenBit
 *
 */
public class Console {
	boolean powerState = false;
	
	boolean CurRunOn = false;
	Printer printer;
	Event race;//gonna be Event race
	public Time time;
	Channels channels;
	String eventType;
	Thread runner;
	
	/**
	 * instantiates a console with a time class that is counting concurrently to it
	 * also channels are available
	 */
	public Console(){
		time = new Time();
		channels = new Channels();
		Runnable r1 = new runnableTimer(time);
		runner = new Thread(r1);
		runner.start();
	}
	
	/**
	 * <p>if the machine is off it turns it on creating an independent race along with it 
	 * also turn on printer</p> 
	 * <p>else if the machine is on if no events are currently happening it will clear the race
	 *  and turn the printer off. else it states event is happaning in case of accidental shutdown</p>
	 * 
	 */
	public void Power(){
		powerState = !powerState;
		if(powerState == true){
			this.race = new RaceIndependent();//default
			CurRunOn = true;
			this.eventType = "IND";//default
			printer = new Printer();
		}
		else if(curRunCheck())
		{//forbid force power off during the race
			System.out.println("The race still going");
		}
		else{
			//save race contents first
			this.race = null;
			printer = null;
		}
	}
	
	/**
	 *  Checks if machine is on and resets the time to 0 and creates a brand new race
	 *  setting the event to "IND" if it had changed
	 */
	public void Reset(){//might be able to just set time to 0:0:0.0
		//start everything over
		if(onCheck()){
			this.time= new Time();
			Runnable r1 = new runnableTimer(time);//might want to make it time("0:0:0.0"); instead of messing with threads
			this.runner = new Thread(r1);
			this.runner.start();
			this.race = new RaceIndependent();
			//CurRunOn=false;
			eventType = "IND";//default type of event;
		}
	}

	/**
	 * if the machine is on it sets the current time to that of newtime
	 * @param newTime
	 */
	public void Time(String newTime){//sets time
		if(onCheck()){
			this.time.setTime(newTime);
		}
	}
	
	/**
	 * if the machine is on and there isnt an event currently happening it sets the 
	 * type of event to event otherwise it asks the user to end the current event
	 * @param event
	 */
	public void Event(String event){
		if(onCheck() && !curRunCheck()){
			this.eventType = event;
		}
		else{
			System.out.println("An event is ongoing end it first.");
		}
	}
	
	/**
	 * if the machine is on and there isnt an ecent currently it creates a new event of eventType 
	 */
	public void newRun(){
		if(onCheck()&& !curRunCheck()){
			CurRunOn = true;		
			switch(eventType){//creates different types of races 
				case("IND"):
					this.race = new RaceIndependent();
					break;
			}
		}
	}
	
	/**
	 * if the machine is on and there is an event currently running it ends it
	 */
	public void endRun(){
		if(onCheck() && curRunCheck()){//log old race
			this.race = null;
			CurRunOn=false;
		}
		
	}
	/**
	 * if the machine is on and an event is happening it 
	 * sets the racer associated with ID1 as next to start in the race
	 * @param ID1
	 */
	public void Num(int ID1){
		if(onCheck() && curRunCheck()){
			this.race.next(ID1);
		}
	}
	
	/**
	 * if the machine is on and and event is happening it swaps the ending order of the
	 * racer associated with ID1 and ID2 
	 * @param ID1
	 * @param ID2
	 */
	public void Swap(int ID1, int ID2){
		if(onCheck() && curRunCheck()){
			this.race.swap(ID1, ID2);
		}
	}
	
	/**
	 * if the machine is on and an event is currently happening
	 * the next runner to finish will get a DNF
	 */
	public void DNF(){
		if(onCheck() && curRunCheck()){
			this.race.DNF();
		}
	}
	/**
	 * if the machine is on and event is currently happening the runner associated with runnerID 
	 * will be removed from event
	 * @param runnerID
	 */
	public void Clear(int runnerID){
		if(onCheck() && curRunCheck()){
			this.race.remove(runnerID);
		}
	}
	/**
	 * if the machine is on and and event is currently happening the last runner to start will 
	 * be his start time cleared and will be place back as next to start
	 */
	public void Cancel(){
		if(onCheck() && curRunCheck()){
			this.race.cancel();
		}
	}
	/**
	 * if machine is on and and event is currently happening the machine will print 
	 * all the participating runners
	 */
	public void Print(){
		if(onCheck() && curRunCheck()){
			//this.race.printRace();
			this.printer.print(this.race.getPlayerList(), this.eventType);
		}
		//option to print to console
	}
	/**
	 * connects to channel chNum an sensor of type
	 * @param type
	 * @param ChNum
	 */
	public void Connect(String type,int ChNum){
		if(onCheck()){
			Channels.connect(type, ChNum);
		}
	}
	/**
	 * disconnect the sensor at chNum
	 * @param chNum
	 */
	public void Disconnect(int chNum){
		if(onCheck()){
			Channels.Channel ch=channels.getCh(chNum);
			if(ch!=null)
			{
				ch.disconnect();
			}
		}
	}
	/**
	 * if on turn the channel chNum off and vice versa
	 * @param chNum
	 */
	public void Tog(int chNum){
		if(onCheck()){
			channels.Tog(chNum);
		}
	}
	
	/** 
	 * triggers the channel chNum
	 * since it is an independent race trig 1 will start next in line
	 * trig 2 will end the current runner
	 * @param chNum
	 */
	public void Trig(int chNum){
		if(onCheck() && curRunCheck()){	
			if(channels.getCh(chNum).connected()){
				switch(chNum){
					case(1):
						race.start(this.time.getTime());
						break;
					case(2):
						race.finish(this.time.getTime());
						break;
					default:
						System.out.println("Not a Channel");
				}
			}
		}
	}
	
	/**
	 * shorthand for trig 1
	 */
	public void Start(){
		Trig(1);
	}
	
	
	/** 
	 * shorthand for trig 2
	 */
	public void Finish(){
		Trig(2);
	}
	/** 
	 * 
	 * @return the time currently read by the console
	 */
	public long getTime(){
		return time.getTime();
	}
	/**
	 * 
	 * @return boolean regarding the powerstate of the machine
	 * whether it is on or off
	 */
	private boolean onCheck(){
		return powerState;
	}
	
	/**
	 * 
	 * @return boolean regarding an event currently being held
	 */
	private boolean curRunCheck(){
		return CurRunOn;
	}
}
