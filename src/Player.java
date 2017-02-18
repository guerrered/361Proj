
public class Player {
	private int ID;
	long startTime, endTime, totalTime;
	boolean DNF = false;
	
	public int getID(){
		return ID;
	}
	
	public Player(int ID){
		this.ID = ID;
	}
	
	public void start(long time){
		this.startTime = time;
	}

	public void end(long time) {
		this.endTime = time;
		totalTime = endTime - startTime;
	}
	
	public void DNF(){
		DNF = true;
	}
}
