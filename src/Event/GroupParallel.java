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
		}/*
		else if(playersInGp.get(0)!=null)//this shouldn't be a case ever where we call start to finish a player
		{
			if(playersInGp.get(0).isRunning())
			{
				playersInGp.get(0).end(time);
			}
		}*/
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
		/*
		if(lane==1&&playersInGp.get(0)!=null)//redundant
		{
			if(playersInGp.get(0).isRunning())
			{
				playersInGp.get(0).end(time);
			}
			else
			{
				return false;
			}
			
		}
		else if(lane==2&&playersInGp.get(1)!=null)
		{
			if(playersInGp.get(1).isRunning())
			{
				playersInGp.get(1).end(time);
			}
			else
			{
				return false;
			}
			
		}
		else if(lane==3&&playersInGp.get(2)!=null)
		{
			if(playersInGp.get(2).isRunning())
			{
				playersInGp.get(2).end(time);
			}
			else
			{
				return false;
			}
			
		}
		else if(lane==4&&playersInGp.get(3)!=null)
		{
			if(playersInGp.get(3).isRunning())
			{
				playersInGp.get(3).end(time);
			}
			else
			{
				return false;
			}
			
		}
		else if(lane==5&&playersInGp.get(4)!=null)
		{
			if(playersInGp.get(4).isRunning())
			{
				playersInGp.get(4).end(time);
			}
			else
			{
				return false;
			}
			
		}
		else if(lane==6&&playersInGp.get(5)!=null)
		{
			if(playersInGp.get(5).isRunning())
			{
				playersInGp.get(5).end(time);
			}
			else
			{
				return false;
			}
			
		}
		else if(lane==7&&playersInGp.get(6)!=null)
		{
			if(playersInGp.get(6).isRunning())
			{
				playersInGp.get(6).end(time);
			}
			else
			{
				return false;
			}
			
		}
		else if(lane==8&&playersInGp.get(7)!=null)
		{
			if(playersInGp.get(7).isRunning())
			{
				playersInGp.get(7).end(time);
			}
			else
			{
				return false;
			}
			
		}
		
		
		return true;*/
	}
	
	/**
	 * Method that add runner into the race
	 *
	 */
	public boolean setPlayerID(int runnerID)//can only run 8 players at a time
	{
		if(playersInGp.size()<8){	
			
			for(int i =0; i<tempLane; i++){//players have been initialized up to templane
				if(playersInGp.get(i).getID()==runnerID)return false;
			}
			//playersInGp.add(new Player(runnerID));
			playersInGp.add(tempLane++, new Player(runnerID));//add them to their specific lane
			return true;
		}
		return false;
			
	}
	/**
	 * Method that set Runner does not finish
	 *
	 */
	public boolean DNF(int lane){// probably going by lane would make it simpler
		if(lane > 8 || lane < 0){
			return false; //invalid lane num
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
	/*
	public boolean DNF(int num)
	{ int i=0;
		for(Player p: playersInGp)
		{
			if(p.getID()==num&&p.isRunning())
			{
				p.DNF();
				i++;
			}
			
		}
		if(i>0){
		return true;
		}
		else
		{ 
			return false;
		}
	}
	*/
	
	/**
	 * Method that cancel runner in a race;
	 *
	 */
	
	//does cancel = DNF?
	// cancel line or ID num?
	public void cancel()
	{
		startTime = -1;
		for(Player p: playersInGp){// we want to cancel and preserve racer numbers in list
			p.cancel();
		}
		//playersInGp = new ArrayList<>(8);
	}
	
	/**
	 * Method to end the event
	 *
	 */
	public void endRace()//any racer still running at end gets DNFd
	{
		for(Player p:playersInGp)
		{
			//if(!p.participated())all 
			if(p.isRunning())//player still running when race is over means DNF
			{
				p.DNF();
				System.out.println(p.DNF);
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
				return toPrint;
			}
		}
		return null;
	}
	
	/**
	 * @return long - startTime
	 */
	public long getStartTime(){
		return startTime;
		/*
		if(startTime > 0){redundant
			return startTime;
		}
		else{
			return -1;
		}*/
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
