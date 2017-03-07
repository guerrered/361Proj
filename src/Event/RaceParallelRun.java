package Event;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RaceParallelRun extends Event {
	
	Queue<Player> q1 = new LinkedList<Player>();
	Queue<Player> q2 = new LinkedList<Player>();	
	int queue1StartNum = 0;
	int queue1EndNum = 0;
	int queue2StartNum = 0;
	int queue2EndNum = 0;
	
	public RaceParallelRun()
	{
		
	}
	
	public boolean addRunner(int runnerID){
	
		return false;
	}
	
	public void remove(int runnerID){
		
	}
	
	public void DNF(){
		
	}
	
	public void start(long time){
		
	}
	
	public void finish(long time){
		
	}
	
	public  void swap(int p1, int p2){
		
	}
	
	public void swap(){
		
	}
	
	public void next(int id){
		
	}
	
	public void printRace(){
		
	}
	
	public void cancel(){
		
	}
	
	public void clear(){
		
	}
	
	public List<Player> getPlayerList(){
		
		return players;
	}
	
}
