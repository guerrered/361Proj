package Event;
import java.util.ArrayList;
import java.util.List;

import Chronotimer.*;
/**
 * 
 * @author HiddenBit
 *
 */


public class Independent extends Event{
	List <Player> players = new ArrayList<>(9999);
	int queueStartNum = 0;
	int queueEndNum = 0;
	List<Player> endList = new ArrayList<>();
	boolean twoRunning =false;//used so we can tell if 2 people are running if true dont let more people start
							 //max 2 people at a time
	
	/**
	 * Constructor 
	 *
	 */
	public Independent(){
		for(int i =0; i<9999; i++){//adds 9999 racers to list
			Player n = new Player(i);
			players.add(n);
		}
	}
	
	/**
	 * Method that add runner into the race
	 *
	 */
	public boolean addRunner(int runnerID){
		//check to make sure runner doesn't already exist in the system.
		for(int i =0; i<players.size(); i++){
			if(players.get(i).getID()==runnerID)return false;
		}
		players.add(new Player(runnerID));
		numRunners++;
		return true;
	}
	
	/**
	 * Method that remove Runner
	 * @return true if successful
	 *
	 */
	public boolean remove(int runnerID){
		for(int i =0; i<players.size(); i++){
			if(players.get(i).getID()==runnerID){
				players.remove(i);
				numRunners--;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method that set Runner does not finish
	 *
	 */
	public void DNF(){
		if(players.size()>queueEndNum){
			if(players.get(queueEndNum).isRunning()){//check if active
				endList.add(players.get(queueEndNum));
				players.get(queueEndNum++).DNF();
				/*if(twoRunning){
					twoRunning = false;
				}*/
			}
		}
		else{
			System.out.println("all racers have finished");
		}
	}
	/*
	 * starts player who is next in line
	 */
	public boolean start(long time){
		if(players.size()>queueStartNum){
			//if(!twoRunning){
				if(!players.get(queueStartNum).participated()){//check if already ran
					players.get(queueStartNum++).start(time);
					/*if(queueStartNum-2 == queueEndNum){
						twoRunning = true;// we dont want more than 2 people running at once
					}*/
					return true;
				}
			//}
		}
	
		return false;
			//System.out.println("all racers have started");
	}
	
	/**
	 *Player who finished a race will get their own time to the file
	 */
	public boolean finish(long time){
		if(players.size()>queueEndNum){
			if(players.get(queueEndNum).isRunning()){//check if active
				endList.add(players.get(queueEndNum));
				players.get(queueEndNum++).end(time);
				/*if(twoRunning){
					twoRunning = false;//just ended a race therefore no longer 2 people running
				}*/
				return true;
			}
		}
		return false;
		
		//System.out.println("all racers have finished");
		
	}
	
	/**
	 * Method that swap the Runner
	 *
	 */
	public  void swap(int p1, int p2){
			Player temp = null;
			int tempID;
			int foundIndex = 0;
			boolean found1 = false;
			int i;
			for(i = 0; i < players.size(); i++){
				tempID = players.get(i).getID();
				if(tempID == p1 || tempID == p2){
					if(!found1){
						found1 = true;
						foundIndex=i;
						temp = players.get(i);
					}
					else{
						if(temp.isRunning() && players.get(i).isRunning()){//ensures runners are currently participating
						players.set(foundIndex, players.get(i));
						players.set(i, temp);
						break;
						//		return true;
						}
					}
				}
			}
			if(i == players.size()){
				System.out.println("Players Can't be Swapped");
			}//swap not successful
		//return false;
	}
	
	/**
	 * Swaps the last 2 runners to start race if they are racing
	 *
	 */
	public boolean swap(){
		if(queueStartNum >= 2){
			if(players.get(queueStartNum - 1).isRunning() && players.get(queueStartNum - 2).isRunning()){ 
				Player temp = players.get(queueStartNum -1);
				players.set(queueStartNum - 1, players.get(queueStartNum - 2));
				players.set(queueStartNum - 2, temp);
				return true;
			}
		}
		return false;
	}
	 
	/*
	 * player arg is next to run
	 */
	public boolean next(int id){
		Player temp;
		for (int i =0; i < players.size(); i++){
			if(players.get(i).getID()==id && !players.get(i).participated() && !players.get(i).isRunning()){//cant run again if already ran
				temp = players.get(i);
				players.remove(i);
				players.add(queueStartNum, temp);
				return true;
			}
		}
		return false;
	}
	public Player getRacer(int ID){
		return players.get(ID);
	}
	
	//Prints to console in specified format
	public void printRace(){
		Printer p = new Printer();
		p.print(players, "IND");
	}
	
	// back in the queue as next to start
	public void cancel(){
		if(queueStartNum > 0 && players.get(queueStartNum - 1).isRunning()){
			players.get(--queueStartNum).cancel();//cancel last runner to start and make it next in line to start
		}
	}
	
	public void clear(){//possibly unneeded
		players.clear();
	}
	
	/**
	 * Method that return the list of the players
	 *
	 */
	public List<Player> getPlayerList(){
		return players;
	}
	
	/**
	 * returns the list of printable players
	 */
	public List<Player> getEndList(){
		List<Player>  toRet = new ArrayList<>();
		toRet.addAll(endList);
		for(int i = queueStartNum; i < 9999; i++ ){
			if(players.get(i).isCanceled()){
				toRet.add(players.get(i));
			}
			//if others are added it will move them back so its possible for the next to not be canceled
			//therefore we have to check the whole list
		}
		return toRet;
	}
	
	/*
	 * players List that will be displayed on console screen 
	 */
	public List<Player> getDisplayList(){
		List<Player> dis = new ArrayList<>(5);
		int count =0;
		if(queueEndNum > 0){
			dis.add(players.get(queueEndNum -1));//last to finish
		}
		else{
			dis.add(players.get(queueEndNum));
			count++;
		}
		for(int i = count; i < 4; i++){
			dis.add(players.get(queueEndNum + i)); //add the next couple of players to the list;
		}
		return dis;
	}
	
	public void endRace(){
		for(int i = 0; i < queueStartNum; i++){
			if(players.get(i).isRunning()){//DNF all players not finished and add them to endList
				players.get(i).DNF();
				endList.add(players.get(i));
			}
		}
	}
}
