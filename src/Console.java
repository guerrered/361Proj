

public class Console {
	boolean powerState = false;
	
	boolean CurRunOn = false;
	Printer printer;
	Race race;
	Time time;
	
	public Console(){
		//time = new Time();
	}
	
	public void Power(){
		powerState = !powerState;
		if(powerState == true){
			race = new Race();
			printer = new Printer();
		}
		else if(CurRunOn == true)
		{//forbid force power off during the race
			System.out.println("The race still going");
		}
		else{
			//save race contents first
			race = null;
			printer = null;
		}
		System.out.println(powerState);
	}
	
	public void Reset(){
		//start everything over
		time= new Time();
		CurRunOn=false;
	}
	
	public void Exit(){
		//maybe shouldn't be as abrupt;
		System.exit(1);
	}
	
	public void Time(){//sets time

	}
	
	public Race newRun(){
		CurRunOn =true;
		//endRun(race.getNumber()) getName?;		
		return new Race();
	}
	
	public void endRun(int raceNumber){
		//log old race
		//if(race.getNumber()) == raceNumber 
		// end it 
		CurRunOn=false;
		
	}
	
	public void Swap(int ID1, int ID2){
		race.swapRacers(ID1, ID2);
	}
	
	public void DNF(int runnerID){
		race.setDNF(runnerID);
	}
	public void Clear(int runnerID){
		race.removeRunner(runnerID);
	}
	
	public void Cancel(){
		race.cancel();
	}
	public void Print(){
		printer.exportToFile(race.players, race.curRaceData);
		//option to print to console
	}
	
	public void Connect(int ChNum){
	Channels.Channel ch= Channels.getCh(ChNum);
		if(ch!=null)
		{
			ch.connect();
		}
		
	}
	
	public void Disconnect(int chNum){
		Channels.Channel ch=Channels.getCh(chNum);
		if(ch!=null)
		{
			ch.connect();
		}
		
	}
	public void Tog(int chNum){
		Channels.Tog(chNum);
		
		
	}
	
	public void Trig(int chNum){
			switch(chNum){
				case(1):
					race.startIND();
					break;
				case(2):
					race.finishIND();
					break;
				default:
					System.out.println("Not a Channel");
			}
		}
	
	public void Start(){
		race.startIND();
	}
	
	public void Finish(){
		race.finishIND();
	}

}
