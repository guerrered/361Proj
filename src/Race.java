import java.util.ArrayList;
import java.util.List;

public class Race {
	public double clockTime;
	public int runNumber;
	List <Player> players = new ArrayList<>();
	
	
	public boolean addRunner(int runnerID){
		for(int i =0; i<players.size(); i++){
			if(players.get(i).getID()==runnerID)return false;
		}
		
		return true;
		
	}
}
