
public class Player {
	private int ID;
	long startTime, endTime, totalTime;
	public int getID(){
		return ID;
	}
	
	public Player(long startTime, int ID){
		this.ID = ID;
		this.startTime = startTime;
	}
}
