package Event;

import java.util.ArrayList;
import java.util.List;

public class Group extends Event{
	
	List <Player> playersFinished = new ArrayList<>();
	List <Player> playersFinishedMarked = new ArrayList<Player>();
	List <Player> DNFs = new ArrayList<>();
	
	private long startTime = -1; //start time is negative if there is no current race. A new race is legal to begin.
	
	private int tempNumber = 0; //as racers finish (this specific race), they are assigned a placeholder number 
	private int markedNum = 0;
	
	/**
	 * startTime =-1 signifies that a new race is ready to begin
	 * temp number is reset to 0
	 */
	public Group(){
		startTime =-1;
		tempNumber = 0;
	}
	
	/**
	 * sets start time
	 * 
	 * @param time - long that will be the start time for all players
	 * @return boolean - based on success
	 */
	public boolean start(long time){
		if(startTime==-1){
			startTime = time;
			return true;
		}
		//there is currently a race in progress.
		return false;
	}
	
	//called as players cross finish line
	/**
	 * sets finish time
	 * 
	 * @param finish - long that will be the end time for one player
	 * @return boolean - based on success 
	 */
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
	
	/**
	 * sets did not finish
	 * 
	 * @param num - int that will be the runners ID
	 */
	public boolean DNF(int num){
		for(Player p: DNFs){
			if(p.getID()==num){
				System.out.println("Player exists in list");
				return false;
			}
		}
		Player temp = new Player(num);
		temp.DNF();
		DNFs.add(temp);
		return true;
	}
	
	/**
	 * ends the current race
	 * 
	 */
	public void endRace(){
		startTime =-1;
		for(Player p: playersFinished){
			while(!setPlayerID(p.getID())){
				tempNumber++;
				setPlayerID(tempNumber);
			}
		}
	}
	
	/**
	 * Called when NUM is clicked. It's used to overwrite temp numbers
	 * 
	 * @param ID - int to be set as ID
	 * @return boolean - false if player exists in list already
	 */
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
		for(Player p: DNFs){
			if(p.getID()==ID){
				System.out.println("Player exists in list");
				return false;
			}
		}
		if(markedNum < playersFinished.size()){
		Player temp = playersFinished.get(markedNum++);
		temp.setID(ID);
		playersFinishedMarked.add(temp);//add to marked list
		//playersFinished.remove(0); //remove the player from temp list 
		return true;
		}
		return false;//prompted setID more times than players exist
	}
	
	
	/**
	 * Players list that will be displayed on the console screen
	 * 
	 * @return List<Player>
	 */
	public List<Player> getDisplayList(){
		List<Player> dis = new ArrayList<>(1);
		if(!playersFinished.isEmpty()){
			dis.add(playersFinished.get(tempNumber -1));//last to finish
		}
		return dis;
	}
	
	
	/**
	 * Cancels the started race by resetting startTime 
	 */
	public void cancel(){
		startTime = -1;
		//normally we'd mark started racers as such, but we are don't know how many started.
	}
	
	/**
	 * @return long - startTime
	 */
	public long getStartTime(){
		if(startTime > 0){
			return startTime;
		}
		else{
			return -1;
		}
	}
	
	/**
	 * @return int - total number of racers (marked, unmarked, and DNF)
	 */
	public int getTotalNumberFinished(){
		return playersFinished.size()+playersFinishedMarked.size() + DNFs.size();
	}
	
	/**
	 * @return List<Player> - list of all racers
	 */
	public List<Player> getPlayerList(){
		List<Player> pList = new ArrayList<>();
		pList.addAll(playersFinishedMarked);
		pList.addAll(playersFinished);
		
		return pList;
	}
	
	/**
	 * @return List<Player> - list of all racers
	 */
	public List<Player> getEndList(){//list to print
		List<Player> pList = new ArrayList<>();
		//pList.addAll(playersFinishedMarked);
		pList.addAll(playersFinished);
		pList.addAll(DNFs);
		
		return pList;
	}
	
}
