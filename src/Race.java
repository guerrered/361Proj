import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Race {
	public long clockTime;
	public int runNumber;
	public int numRunners;
	List <Player> players = new ArrayList<>();
	File curRaceData;
	String fileNumber = "0";
	
	public Race(){
		System.out.println("x");
		curRaceData = new File("src/RaceData/" + fileNumber);
	}
	
	
	public boolean addRunner(int runnerID){
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
	
	public void startRace(){
		for(int i =0; i<players.size(); i++){
			players.get(i).start(System.nanoTime());
		}
	}
	
	public void endRace(){
		for(int i =0; i<players.size(); i++){
			players.get(i).end(System.nanoTime());
		}
	}
	
	public boolean swapRacers(){
		//TODO
		//swap not successful
		return false;
	}
	
}
