
public class Console {
	boolean powerState = false;
	Printer printer;
	Race race;
	Time time;
	
	public Console(){
		time = new Time();
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
	}
	
	public void Reset(){
		//start everything over
	}
	
	public void Time(){//sets time
		
	}
	
	public Race newRun(){
		endRun(race);		
		return new Race();
	}
	
	public void endRun(Race run){
		//log old race
		run= null;
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
