package Event;

import java.util.ArrayList;
import java.util.List;

public class Group extends Event{
	
	List <Player> playersFinished = new ArrayList<>();
	List <Player> playersFinishedMarked = new ArrayList<Player>();
	
	private long startTime = -1; //start time is negative if there is no current race. A new race is legal to begin.
	
	private int tempNumber = 0; //as racers finish (this specific race), they are assigned a placeholder number 
	
	public Group(){
		startTime =-1;
		tempNumber = 0;
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
	
	//called as players cross finish line
	public boolean finish(long time){
		if(startTime==-1){
			return false;
		}
		tempNumber++;
		Player temp = new Player(tempNumber);
		temp.start(startTime);
		temp.end(time);
		playersFinished.add(temp);
		return true;
	}
	
	
	//merge into one list
	public void endRace(){
		startTime =-1;
		for(Player p: playersFinished){
			while(!setPlayerID(p.getID())){
				tempNumber++;
				setPlayerID(tempNumber);
			}
		}
	}
	
	//called when IDs are entered after players finished.
	public boolean setPlayerID(int ID){
		if(playersFinished.size()==0){
			return false;
		}
		for(Player p: playersFinishedMarked){
			if(p.getID()==ID){
				System.out.println("Player exists in list");
				return false;
			}
		}
		Player temp = playersFinished.get(0);
		temp.setID(ID);
		playersFinishedMarked.add(temp);//add to marked list
		playersFinished.remove(0); //remove the player from temp list 
		return true;
	}
	
	/*
	 * players List that will be displayed on console screen 
	 */
	public List<Player> getDisplayLsit(){
		List<Player> dis = new ArrayList<>(1);
		dis.add(playersFinished.get(tempNumber));
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
	
	public int getTotalNumberFinished(){
		return playersFinished.size()+playersFinishedMarked.size();
	}
	
	public List<Player> getPlayerList(){
		List<Player> pList = new ArrayList<>();
		pList.addAll(playersFinishedMarked);
		pList.addAll(playersFinished);
		
		return pList;
	}
}
