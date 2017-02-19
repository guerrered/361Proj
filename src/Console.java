
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
	
	public void newRun(){
		endRun(race);
		race = new Race();
	}
	
	public void endRun(Race run){
		//log old race
		run= null;
	}
	
	public void Swap(){
		
	}
	
	public void DNF(){
		
	}
	public void Clear(){
		
	}
	public void Cancel(){
		
	}
	public void Print(){
		
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
