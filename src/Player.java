
public class Player {
	private int ID;
	long startTime, endTime, totalTime;
	boolean DNF = false;
	boolean ran = false;
	
	public int getID(){
		return ID;
	}
	
	public Player(int ID){
		this.ID = ID;
	}
	
	public void start(){
		this.startTime = System.nanoTime();
		this.ran = true;
	}

	public void end() {
		this.endTime = System.nanoTime();
		totalTime = endTime - startTime;
	}
	
	public void DNF(){
		DNF = true;
	}
	
	public void cancel(){
		startTime = endTime = totalTime = 0;
	}
	
	public boolean participated(){
		return ran;
	}
}
