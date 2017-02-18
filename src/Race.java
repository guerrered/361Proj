import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Race {
	public long clockTime;
	public int runNumber;
	public int numRunners;
	List <Player> players = new ArrayList<>();
	File curRaceData;
	static int fileNumber = 1;
	
	public Race(){
		String temp = "RaceData/Race" + fileNumber + ".txt";
		
		File directory = new File("RaceData");
		if(!directory.exists()){
			System.out.println("Directory doesn't exist. New directory has been created.");
			directory.mkdirs();
		}
		curRaceData = new File(temp);
		BufferedWriter output;
		try {
			curRaceData.createNewFile();
			//write to file
			output = new BufferedWriter(new FileWriter (curRaceData));
			output.write("Race #" + fileNumber);
			fileNumber++;
			output.close();
		} catch (IOException e) {
			System.out.println("failed");
			e.printStackTrace();
		}
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
	
	/*
	 * Not used for IND
	 */
	public void startRace(){
		for(int i =0; i<players.size(); i++){
			players.get(i).start(System.nanoTime());
		}
	}
	
	
	public boolean swapRacers(int p1, int p2){
		//TODO
		Player temp;
		int tempID;
		boolean found1 = false;
		for(int i = 0; i < players.size(); i++){
			tempID = players.get(i).getID();
			if(tempID == p1 || tempID == p2){
				temp = players.get(i);
			}
		}
		//swap not successful
		return false;
	}
	
	public void printRace(){
		Printer p = new Printer();
		p.exportToFile(players, curRaceData);
	}
	
}
