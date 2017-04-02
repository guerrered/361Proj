package Event;

import java.util.ArrayList;
import java.util.List;

public class Group extends Event{
	
	List <Player> players = new ArrayList<>(9999);
	List <Player> playersFinished = new ArrayList<>();
	
	int queueStartNum = 0;
	int queueEndNum = 0;
	private long startTime = -1; //start time is negative if there is no current race. A new race is legal to begin.
	
	private int numberFinished = 0; //as racers finish (this specific race), they are assigned a placeholder number 
	private int numberFinishedMarked = 0; //this number is increased as IDs are entered
	private int newID = 10000; //new ID to be assigned when ID gets replaced
	
	public Group(){
		startTime =-1;
		numberFinished = numberFinishedMarked = 0;
		//create a list of temp racers. Temp racers can be replaced with proper IDs after the race ends.
//		for(int i=1; i <=9999; i++){
//			//the ID acts as a placeholder number
//			Player temp = new Player(i);
//			players.add(temp);
//		}
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
		Player temp = new Player(numberFinished);
//		if(!temp.ran){

//		}
		temp.start(startTime);
		temp.end(time);
		temp.participated();
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
	
}
