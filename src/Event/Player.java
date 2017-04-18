package Event;
/**
 * 
 * @author HiddenBit
 *
 */

/**
 * Player class that hold all the individual information include their ID, start time, end time, and 
 * whether each of them ran, during the running or did not finish 
 *
 *
 */
public class Player {
	private int ID;
	public long startTime, endTime, totalTime;
	public boolean DNF = false;
	public boolean ran = false;
	public boolean running = false;
	public boolean cancel =false;
	
	/**
	 * Method that return player ID.
	 *
	 */
	public int getID(){
		return ID;
	}
	
	/**
	 * Constructor of the player
	 *
	 */
	public Player(int ID){
		this.ID = ID;
	}
	
	/**
	 * Set player's start time from console
	 *
	 */
	public void start(long time){
		this.running = true;
		//this.cancel = false; 
		this.startTime = time;
	}

	/**
	 * Set player's end time from console
	 *
	 */
	public void end(long time) {
		this.endTime = time;
		this.ran = true;
		this.cancel = false;
		this.running = false;
		this.totalTime = this.endTime - this.startTime;
		if(this.totalTime > 9999990){//timeoverflow for player 9999seconds 99hundreths 
			DNF();
		}
	}
	
	/**
	 * Set player's statue that he/she does not finish the run
	 *
	 */
	public void DNF(){
		this.running = false;
		this.ran = true;
		this.DNF = true;
	}
	
	/**
	 * Set player's current run has been cancel and back to the next first run.
	 *
	 */
	public void cancel(){
		this.running = false;
		this.cancel = true;
		startTime = endTime = totalTime = 0;
	}
	/**
	 * Method that return the total time that the player use for the run.
	 *
	 */
	public long getTotalTime(){
		return totalTime;
	}
	
	/**
	 * Method return player's start time.
	 *
	 */
	public long getStartTime(){
		return startTime;
	}
	
	/**
	 * Method return player's end time.
	 *
	 */
	public long getEndTime(){
		return endTime;
	}
	
	/**
	 * Method check whether player had run.
	 *
	 */
	public boolean participated(){
		return ran;
	}
	/**
	 * Method check whether player is running.
	 *
	 */
	public boolean isRunning(){
		return running;
	}
	
	/*
	 * setter for boolean cancel
	 */
	public boolean togCancel(){
		cancel=!cancel;
		return cancel;
	}
	
	public boolean wasCanceled(){
		return cancel;
	}
	public boolean isCanceled(){
		if(cancel){
			if(!running){
				return true;
			}
		}
		return false;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String toString(){
		String ret = "";
		ret +=ID;
		return ret;
	}
}
