package Event;

import java.util.List;
import java.util.ArrayList;

/**
 * 
 * @author HiddenBit
 *
 */

public class GroupParallel extends Event {

	List <Player> playersInGp = new ArrayList<>(8);
	
	private long startTime;
	int tempLane;
	
	public GroupParallel()
	{
		startTime = -1;
		tempLane = 0;
	}
	
	
	/**
	 * sets start time
	 * 
	 * @param time - long that will be the start time for all players
	 * @return boolean - based on success
	 */
	public boolean start(long time){
		if(startTime==-1&&!playersInGp.isEmpty()){
			startTime = time;
			for(Player p:playersInGp)
			{
				p.start(time);
			}
			return true;
		}
		//there is currently a race in progress.
		return false;
	}
	
	/**
	 * sets finish time
	 * 
	 * @param finish - long that will be the end time for one player
	 * @return boolean - based on success 
	 */
	public boolean finish(long time, int lane)
	{
		if(startTime==-1)
		{
			return false;
		}
		if(lane > 8 || lane < 0){ //only valid lanes are 0-8
			return false;
		}
		if(lane > tempLane){
			return false;//no player assigned to lane can't finish
		}
		Player temp = playersInGp.get(lane -1);
		if(temp != null){//if there is a runner there
			if(temp.isRunning()){//and they are running
				temp.end(time);//end their race
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method that add runner into the race
	 *
	 */
	public boolean setPlayerID(int runnerID)//can only run 8 players at a time
	{
		if(startTime == -1){
			if(playersInGp.size()<8){	
			
				for(int i =0; i<tempLane; i++){//players have been initialized up to templane
					if(playersInGp.get(i).getID()==runnerID)return false;
				}
				//playersInGp.add(new Player(runnerID));
				playersInGp.add(tempLane++, new Player(runnerID));//add them to their specific lane
				return true;
			}
		}
		return false;
	
			
	}
	/**
	 * Method that set Runner does not finish
	 *
	 */
	public boolean DNF(int lane){// probably going by lane would make it simpler
		if(lane > 8 || lane <= 0){
			return false; //invalid lane num
		}
		if(lane > tempLane){//not used
			return false;
		}
		Player temp = playersInGp.get(lane -1);
		if(temp!= null){
			if(temp.isRunning()){
				temp.DNF();
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Method that cancel runner in a race;
	 *
	 */
	public void cancel()
	{
		startTime = -1;
		for(Player p: playersInGp){// we want to cancel and preserve racer numbers in list
			p.cancel();
		}
	}
	
	/**
	 * Method to end the event
	 *
	 */
	public void endRace()//any racer still running at end gets DNFd
	{
		for(Player p:playersInGp)
		{
			if(p.isRunning())//player still running when race is over means DNF
			{
				p.DNF();
			}
		}
		
	}
	
	/**
	 * 
	 * 
	 * @return list of racers that participated(finished or DNF)
	 */
	
	public List<Player> getEndList()
	{
		List<Player> toPrint = new ArrayList<>();
		if(!playersInGp.isEmpty()){
			for(Player p: playersInGp){
				if(p.participated()){//might want to sort by time
					toPrint.add(p);
				}
			}
			return toPrint;
		}
		return null;
	}
	
	public List<Player> getPlayerList(){
		return playersInGp;
	}
	/**
	 * @return long - startTime
	 */
	public long getStartTime(){
		return startTime;
	}
	
	
	/**
	 * return list for the display
	 */
	public List<Player> getDisplayList(){
		List<Player> displayList = new ArrayList<>();
		if(!playersInGp.isEmpty()){
			for(Player p: playersInGp){
				displayList.add(p);
			}
			return displayList;
		}
		return null;
	}
	
}
