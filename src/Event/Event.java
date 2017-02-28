package Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class Event {
	static int fileNumber = 1;
	File curRaceData;
	List <Player> players = new ArrayList<>(9999);
	public long clockTime;
	public int runNumber=1;
	public int numRunners;
	
	public void createRaceOutputFile(){
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
			output.write("Race #" + fileNumber + '\n');
			fileNumber++;
			output.close();
		} catch (IOException e) {
			System.out.println("failed");
			e.printStackTrace();
		}
	}
	
	public void start(long time){
	}
	
	public void finish(long time){	
	}
	public void swap(int ID1, int ID2){
	}
	
	public List<Player> getPlayerList(){//for indepent
		return players;
	}
	
	public void cancel(){
	}
	
	public void next(int ID){
	}
	
	public void DNF(){
	}
	
	public void remove(int id){
		
	}
	public File getRaceData(){
		return curRaceData;
	}
}
