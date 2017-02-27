
public class Player {
	private int ID;
	long startTime, endTime, totalTime;
	boolean DNF = false;
	boolean ran = false;
	boolean running = false;
	
	public int getID(){
		return ID;
	}
	
	public Player(int ID){
		this.ID = ID;
	}
	
	public void start(){
		this.running = true;
		this.startTime = System.nanoTime();
	}

	public void end() {
		this.endTime = System.nanoTime();
		this.ran = true;
		this.running = false;
		totalTime = endTime - startTime;
	}
	
	public void DNF(){
		this.running = false;
		this.ran = true;
		DNF = true;
	}
	
	public void cancel(){
		this.running = false;
		startTime = endTime = totalTime = 0;
	}
	
	public boolean participated(){
		return ran;
	}
	public boolean isRunning(){
		return running;
	}
}
