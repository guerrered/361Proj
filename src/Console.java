

public class Console {
	boolean powerState = false;
	
	boolean CurRunOn = false;
	Printer printer;
	RaceIndependent race;
	Time time;
	Channels channels;
	String eventType;
	
	public Console(){
		time = new Time();
		channels = new Channels();
		Runnable r1 = new runnableTimer(time);
		Thread t1 = new Thread(r1);
		t1.start();
	}
	
	public void Power(){
		powerState = !powerState;
		if(powerState == true){
			this.race = new RaceIndependent();
			this.eventType = "IND";
			printer = new Printer();
		}
		else if(CurRunOn == true)
		{//forbid force power off during the race
			System.out.println("The race still going");
		}
		else{
			//save race contents first
			this.race = null;
			printer = null;
		}
	}
	
	public void Reset(){
		//start everything over
		if(onCheck()){
			time= new Time();
			CurRunOn=false;
		}
	}
	
	public void Exit(){
		//maybe shouldn't be as abrupt;
		System.exit(1);
	}
	
	public void Time(String newTime){//sets time
		if(onCheck()){
			this.time.setTime(newTime);
			//System.out.println(time.getTime());
		}
	}
	
	public void newRun(){
		if(onCheck()&&CurRunOn==false){
			CurRunOn = true;
		//endRun(race.getNumber()) getName?;		
			this.race = new RaceIndependent();
		}
	}
	
	public void endRun(int raceNumber){
		if(onCheck()){//log old race
			//if(race.getNumber()) == raceNumber 
			// end it 
			CurRunOn=false;
		}
		
	}
	
	public void Num(int ID1){
		if(onCheck()){
			this.race.nextUp(ID1);
		}
	}
	public void Swap(int ID1, int ID2){
		if(onCheck()){
			this.race.swapRacers(ID1, ID2);
		}
	}
	
	public void DNF(){
		if(onCheck()){
			this.race.setDNF();
		}
	}
	public void Clear(int runnerID){
		if(onCheck()){
			this.race.removeRunner(runnerID);
		}
	}
	
	public void Cancel(){
		if(onCheck()){
			this.race.cancel();
		}
	}
	public void Print(){
		if(onCheck()){
			//this.race.printRace();
			this.printer.print(this.race.getPlayerList(), this.eventType);
		}
		//option to print to console
	}
	
	public void Connect(String type,int ChNum){
		if(onCheck()){
			Channels.Channel ch= channels.getCh(ChNum);
			if(ch!=null)
			{
				ch.connect(type,this.race);
			}
		}
	}
	
	public void Disconnect(int chNum){
		if(onCheck()){
			Channels.Channel ch=channels.getCh(chNum);
			if(ch!=null)
			{
				ch.disconnect();
			}
		}
	}
	public void Tog(int chNum){
		if(onCheck()){
			channels.Tog(chNum);
		}
	}
	
	public void Trig(int chNum){
		if(onCheck()){	
			if(channels.getCh(chNum).connected()){
				switch(chNum){
					case(1):
						race.startIND(this.time.getTime());
						break;
					case(2):
						race.finishIND(this.time.getTime());
						break;
					default:
						System.out.println("Not a Channel");
			}
			}
		}
	}
	
	public void Start(){
		Trig(1);
	}
	
	public void Finish(){
		Trig(2);
	}
	public long getTime(){
		return time.getTime();
	}
	private boolean onCheck(){
		return powerState;
	}
}
