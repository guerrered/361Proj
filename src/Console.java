

public class Console {
	boolean powerState = false;
	
	boolean CurRunOn = false;
	Printer printer;
	RaceIndependent race;//gonna be Event race
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
	
	public void Reset(){
		//start everything over
		if(onCheck()){
			time= new Time();
			CurRunOn=false;
			eventType = "IND";//default type of event;
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
	
	public void Event(String event){
		this.eventType = event;
	}
	
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
	
	public void endRun(){
		if(onCheck() && curRunCheck()){//log old race
			this.race = null;
			CurRunOn=false;
		}
		
	}
	
	public void Num(int ID1){
		if(onCheck() && curRunCheck()){
			this.race.nextUp(ID1);
		}
	}
	public void Swap(int ID1, int ID2){
		if(onCheck() && curRunCheck()){
			this.race.swapRacers(ID1, ID2);
		}
	}
	
	public void DNF(){
		if(onCheck() && curRunCheck()){
			this.race.setDNF();
		}
	}
	public void Clear(int runnerID){
		if(onCheck() && curRunCheck()){
			this.race.removeRunner(runnerID);
		}
	}
	
	public void Cancel(){
		if(onCheck() && curRunCheck()){
			this.race.cancel();
		}
	}
	public void Print(){
		if(onCheck() && curRunCheck()){
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
		if(onCheck() && curRunCheck()){	
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
	private boolean curRunCheck(){
		return CurRunOn;
	}
}
