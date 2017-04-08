package Event;

import java.util.ArrayList;
import java.util.List;

public class Group extends Event{
	
	List <Player> playersFinished = new ArrayList<>();
	
	private long startTime = -1; //start time is negative if there is no current race. A new race is legal to begin.
	
	private int numberFinished = 0; //as racers finish (this specific race), they are assigned a placeholder number 
	private int numberFinishedMarked = 0; //this number is increased as IDs are entered
	
	public Group(){
		startTime =-1;
		numberFinished = numberFinishedMarked = 0;
	}
	
	public boolean start(long time){
		if(startTime==-1){
			/*
			 * not sure if we know how many are supposed to start let alone
			 * which racers are starting so I don't think we can check if they've participated
			 * already like we do in other races.
			 */
			startTime = time;
			return true;
		}
		//there is currently a race in progress.
		return false;
	}
	
	public boolean finish(long time){
		if(startTime==-1){
			return false;
		}
		
		Player temp = new Player(numberFinished);

		temp.start(startTime);
		temp.end(time);
		numberFinished++;
		playersFinished.add(temp);
		
		return true;
	}
	
	//called when IDs are entered after players finished.
	public boolean setPlayerID(int ID){
		if(numberFinished>numberFinishedMarked){
			for(int i=0; i<playersFinished.size(); i++){
				if(playersFinished.get(i).getID()==ID){
					return false;
				}
			}
			Player finishedTemp = playersFinished.get(numberFinishedMarked);
			finishedTemp.setID(ID);
			numberFinishedMarked++;
			return true;
		}
		
		return false;
	}
	
	/*
	 * players List that will be displayed on console screen 
	 */
	public List<Player> getDisplayLsit(){
		List<Player> dis = new ArrayList<>(1);
		dis.add(playersFinished.get(numberFinished));
		return dis;
	}
	
	/*
	 * cancel started race
	 */
	public void cancel(){
		startTime = -1;
		//normally we'd mark started racers as such, but we are don't know how many started.
	}
	
	//getter for StartTime
	public long getStartTime(){
		return startTime;
	}
	
}
