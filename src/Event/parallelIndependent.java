package Event;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class parallelIndependent  extends Event{
	List <Player> players = new ArrayList<>(9999);
	List <Player> lane1 = new ArrayList<>();
	List <Player> lane2 = new ArrayList<>();
	
	int queueStartNum = 0;//general start
	int queue1Start = 0;//start lane 1
	int queue2Start=0;
	int queue1EndNum = 0;
	int queue2EndNum = 0;
	
	/**
	 * Constructor initiates a list with 9999 players
	 */
	public parallelIndependent(){
		for(int i = 0; i < 9999; i++){
			Player newPlayer = new Player(i);
			players.add(newPlayer);
		}
	}
	
	/**
	 * sets runner id as next to start in race
	 */
	public void next(int id){
		Player temp;
		for (int i =0; i < players.size(); i++){
			if(players.get(i).getID()==id && !players.get(i).participated() &&!players.get(i).isRunning()){//cant run again if already ran
				temp = players.get(i);
				players.remove(i);
				players.add(queueStartNum, temp);
			}
		}
	}
	
	/**
	 * starts the next racer setting its start time to the time var passed
	 * furthermore it starts the players into a certain lane. EX.
	 * if channel 1 was triggered player starts in lane 1 if channel 3
	 * was triggered player starts in lane 2
	 * 
	 * @param time	current system time 
	 * @param lane	the lane in which runner will run
	 */
	public void start(long time, int lane){
		if(queueStartNum < players.size()){
			if(!players.get(queueStartNum).isRunning()){//breaks in raceInd
				if(!players.get(queueStartNum).participated()){
					Player p = players.get(queueStartNum++);
					p.start(time);
					if(lane == 1){
						lane1.add(p);
						queue1Start++;
					}
					else if(lane == 2){
						lane2.add(p);
						queue2Start++;
					}
				}
			}
		}
	}
	
	/**
	 * will end the next runners race in the specified lane if 
	 * there is currently a runner there.
	 * @param time	system time "finish time" for runner
	 * @param lane	lane where runner is running
	 */
	public void finish(long time, int lane){
		if(lane == 1){
			if(lane1.size() > queue1EndNum){
				if(lane1.get(queue1EndNum).isRunning()){//breaks in raceInd
					lane1.get(queue1EndNum++).end(time);//preemptively point spot to next however if nothing is present it wont be able to execute if it gets here
				}
			}
		}
		else if(lane == 2){
			if(lane2.size() > queue2EndNum){
				if(lane2.get(queue2EndNum).isRunning()){
					lane2.get(queue2EndNum++).end(time);
				}
			}
		}
	}
	
	/**
	 * 	sets the next runner to finish in a specific lane as DNF 
	 * @param lane	the lane in which the racer is participating
	 */
	public void DNF(int lane){
		if(lane == 1){
			if(lane1.size() > queue1EndNum){
				if(lane1.get(queue1EndNum).isRunning()){//breaks in raceInd
					lane1.get(queue1EndNum++).DNF();//preemptively point spot to next however if nothing is present it wont be able to execute if it gets here
				}
			}
		}
		else if(lane2.size() > queue2EndNum){
			if(queue2Start > 0){
				if(lane2.get(queue2EndNum).isRunning()){
					lane2.get(queue2EndNum++).DNF();
				}
			}
		}
	}
	
	/**
	 * 	cancels the next runner to finish in a specific lane
	 * 	furthermore the runner is removed from the lane list
	 * 	as their next start may be in a different lane
	 * @param lane
	 */
	public void cancel(){//cancel last to start UNFINISHED next to finish in particular lane not necessarily last to start
		if(queueStartNum > 0){
			Player canceled = players.get(--queueStartNum);
			canceled.cancel();
			lane1.remove(canceled);//we don't know which lane they were in if not in one it wont remove anything
			lane2.remove(canceled);
			
			/*
			int startInd;
			if(lane == 1){
				if(queue1EndNum < lane1.size()){
					if(lane1.get(queue1EndNum).isRunning()){
						Player canceled = lane1.get(queue1EndNum);
						canceled.cancel();
						startInd = players.indexOf(canceled);
						players.remove(startInd);
						players.add(queueStartNum, canceled );
						lane1.remove(canceled);//possible for player to start in different lane
					}
				}
			}
			else if(lane == 2){
				if(queue2EndNum < lane2.size()){
					if(lane2.get(queue2EndNum).isRunning()){
						Player canceled = lane2.get(queue2EndNum);
						canceled.cancel();
						startInd = players.indexOf(canceled);
						players.remove(startInd);
						players.add(queueStartNum, canceled );
						lane2.remove(canceled);//possible for player to start in different lane
					}
				}
			}*/
		}
	}
	
	public void swap(int lane){//swaps the next two runners to finish in a specific lane
		if(lane == 1){
			if(queue1EndNum < lane1.size()){//at least 2 players active
				if(lane1.get(queue1EndNum +1).isRunning()){
					if(lane1.get(queue1EndNum).isRunning()){
						Player temp = lane1.get(queue1EndNum + 1);
						lane1.set(queue1EndNum + 1, lane1.get(queue1EndNum));
						lane1.set(queue1EndNum, temp);
					}
				}
			}
		}
		else if( lane == 2){
			if(queue2EndNum < lane2.size()){//at least 2 players active
				if(lane2.get(queue2EndNum + 1).isRunning()){
					if(lane2.get(queue2EndNum).isRunning()){
						Player temp = lane2.get(queue2EndNum + 1);
						lane2.set(queue2EndNum + 1, lane2.get(queue2EndNum));
						lane2.set(queue2EndNum, temp);
					}
				}
			}
		}
	}
	
	// not sure we want this anymore might as well remove from ind also
	public void swap(int ID1, int ID2, int lane){
		
	}
	

	 /**
		 * sortByTime() Sort method that sort the order by time.
		 * @param p - list of players to be sorted
		 * @return List of sorted players
		 */
	public List<Player> sortByFinishTime(List<Player> p){
		List<Player> sortedList = new ArrayList<>(p.size());
		sortedList.addAll(p);
	    
		sortedList.sort(timeComparator);
		return sortedList;
	}
	
	 /**
		 * Comparator that compare players time. 
		 * @return comparison value
		 */
	public static Comparator<Player> timeComparator = new Comparator<Player>(){
		public int compare(Player p1,Player p2){
			if(p1.DNF&&p2.DNF)return 0;
			if(p1.DNF)return 1;
			if(p2.DNF)return -1;
			return (int) (p1.endTime - p2.endTime);
		}
	};
	
	public List<Player> getPlayerList(){//returns in order of finish like the raceIndepent did
		List<Player> printList = sortByFinishTime(players);
		return printList;
	}
}
