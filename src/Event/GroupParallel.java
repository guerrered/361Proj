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
	
	public GroupParallel()
	{
		startTime = -1;
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
		else if(playersInGp.get(0)!=null)
		{
			if(playersInGp.get(0).isRunning())
			{
				playersInGp.get(0).end(time);
			}
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
		if(lane==1&&playersInGp.get(0)!=null)
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
		
		
		return true;
	}
	
	/**
	 * Method that add runner into the race
	 *
	 */
	public boolean setPlayerID(int runnerID)
	{
		if(playersInGp.size()<8)
		{	
			
		for(int i =0; i<playersInGp.size(); i++){
			if(playersInGp.get(i).getID()==runnerID)return false;
		}
		playersInGp.add(new Player(runnerID));
		return true;
		}
		else
		{
			return false;
		}
		
	}
	/**
	 * Method that set Runner does not finish
	 *
	 */
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
	
	
	/**
	 * Method that cancel runner in a race;
	 *
	 */
	
	//does cancel = DNF?
	// cancel line or ID num?
	public void cancel(int line)
	{
		if(playersInGp.get(line-1)!=null)
		{
			if(playersInGp.get(line-1).isRunning())
			{
				playersInGp.get(line-1).cancel();
			}
			
		}
		
	}
	
	/**
	 * Method to end the event
	 *
	 */
	public void endRace()
	{
		startTime = -1;
		for(Player p:playersInGp)
		{
			if(!p.participated())
			{
				p.DNF();
			}
		}
		
	}
	
	/**
	 * Players list that will be displayed on the console screen
	 * 
	 * @return List<Player>
	 */
	
	public List<Player> getList()
	{
		return playersInGp;
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
	
}
