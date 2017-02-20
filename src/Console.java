
public class Console {
	boolean powerState = false;
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
		else{
			//save race contents first
			race = null;
			printer = null;
		}
		System.out.println(powerState);
	}
	
	public void Reset(){
		//start everything over
	}
	
	public void Exit(){
		//maybe shouldn't be as abrupt;
		System.exit(1);
	}
	
	public void Time(){//sets time

	}
	
	public Race newRun(){
		//endRun(race.getNumber()) getName?;		
		return new Race();
	}
	
	public void endRun(int raceNumber){
		//log old race
		//if(race.getNumber()) == raceNumber 
		// end it 
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
		
	}
	public void Print(){
		printer.exportToFile(race.players, race.curRaceData);
		//option to print to console
	}
	
	public void Connect(){
		
	}
	
	public void Disconnect(){
		
		
	}
	public void Tog(){
		
	}
	
	public void Trig(){
		
	}
	
	public void Start(){
		//trig channel 1
	}
	
	public void Finish(){
		//trig channel 2
	}

}
