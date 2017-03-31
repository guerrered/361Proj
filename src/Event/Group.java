package Event;

import java.util.ArrayList;
import java.util.List;

public class Group extends Event{
	
	List <Player> players = new ArrayList<>(9999);
	
	int queueStartNum = 0;
	int queueEndNum = 0;
	private long startTime = -1; //start time is negative if there is no current race. A new race is legal to begin.
	
	private int numberFinished = 0; //as racers finish, they are assigned a placeholder number
	
	public Group(){
		startTime =-1;
		numberFinished = 0;
		//create a list of temp racers. Temp racers can be replaced with proper IDs after the race ends.
		for(int i=1; i <=9999; i++){
			//the ID acts as a placeholder number
			Player temp = new Player(i);
			players.add(temp);
		}
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
		return false;
	}
	
	public boolean finish(long time){
		Player temp = players.get(numberFinished);
		//check if runner has ran previously
		if(!temp.ran){
			temp.start(startTime);
			temp.end(time);
			temp.participated();
			numberFinished++;
			return true;
		}
		
		return false;
	}
	
	
}
