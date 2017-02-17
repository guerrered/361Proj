import java.util.ArrayList;
import java.util.List;

public class Race {
	public double clockTime;
	public int runNumber;
	public int numRunners;
	List <Player> players = new ArrayList<>();
	
	
	public boolean addRunner(int runnerID){
		for(int i =0; i<players.size(); i++){
			if(players.get(i).getID()==runnerID)return false;
		}
		players.add(new Player(System.nanoTime(), runnerID));
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
}
