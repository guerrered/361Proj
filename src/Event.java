import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Event {
	public long clockTime;
	public int runNumber=1;
	public int numRunners;
	List <Player> players = new ArrayList<>(9999);
	File curRaceData;
	
	
	
	
	public boolean addRunner(int runnerID){
		//check to make sure runner doesn't already exist in the system.
		for(int i =0; i<players.size(); i++){
			if(players.get(i).getID()==runnerID)return false;
		}
		players.add(new Player(runnerID));
		numRunners++;
		return true;
	}
	
	public boolean removeRunner(int runnerID){
		for(int i =0; i<players.size(); i++){
			if(players.get(i).getID()==runnerID){
				players.remove(i);
				numRunners--;
				return true;
			}
		}
		return false;
	}
	
	
	public boolean swapRacers(int p1, int p2){
		Player temp = null;
		int tempID;
		int foundIndex = 0;
		boolean found1 = false;
		for(int i = 0; i < players.size(); i++){
			tempID = players.get(i).getID();
			if(tempID == p1 || tempID == p2){
				if(!found1){
					found1 = true;
					foundIndex=i;
					temp = players.get(i);
				}
				else{
					players.set(foundIndex, players.get(i));
					players.set(i, temp);
				}
			}
		}
		//swap not successful
		return false;
	}
	
	public void printRace(){
		Printer p = new Printer();
		p.print(players, "IND");
	}
	
	public void clear(){//possibly unneeded
		players.clear();
	}
	
	public List<Player> getPlayerList(){
		return players;
	}
	
	public File getRaceData(){
		return curRaceData;
	}
	
}
