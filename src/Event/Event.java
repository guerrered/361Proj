package Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.*;
/**
 * 
 * @author HiddenBit
 *
 */

/**
 * Super class of all the event that keep the current race data, players list, number of runners and the time
 * 
 *
 */
public class Event {
	public static int fileNumber = 1;
	File curRaceData;
	List <Player> players = new ArrayList<>(9999);
	public long clockTime;
	public int runNumber=1;
	public int numRunners;
	
	/**
	 * Method that write data to the file for export 
	 *
	 */
	public File createRaceOutputFile(){
		String zeroLead = String.format("%03d", fileNumber);
		String temp = "USB/RUN" + zeroLead + ".txt";
		
		File directory = new File("USB");
		if(!directory.exists()){
			System.out.println("Directory doesn't exist. New directory has been created.");
			directory.mkdirs();
		}
		curRaceData = new File(temp);
//		BufferedWriter output;
		try {
			curRaceData.createNewFile();
			//write to file
			//output = new BufferedWriter(new FileWriter (curRaceData));
			//output.write("Race #" + fileNumber + '\n');
			fileNumber++;
			//output.close();
		} catch (IOException e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		return curRaceData;
	}
	
	/**
	 * Super class' method that record start time.
	 *
	 */
	public void start(long time){
	}
	public void start(long time, int lane){
	}
	
	/**
	 * Super class' method that record finish time
	 *
	 */
	public void finish(long time){	
	}
	public void finish(long time, int lane){
	}
	
	public void swap(){
	}
	
	public void swap(int lane){
	}
	/**
	 * Super class' method that swap the order between two player with ID number. 
	 *
	 */
	public void swap(int ID1, int ID2){
	}
	
	/**
	 * Super class' method that return player list
	 *
	 */
	public List<Player> getPlayerList(){//for indepent
		return players;
	}
	
	/**
	 * Super class' method to cancel current runner time and reset his/her run
	 *
	 */
	public void cancel(){
	}
	
	public void cancel(int lane){
	}
	
	/**
	 * Super class' method calling the new runner for the race
	 *
	 */
	public void next(int ID){
	}
	
	/**
	 * Super class' method that set current player status of does not finish 
	 *
	 */
	public void DNF(){
	}
	
	public void DNF(int lane){
	}
	
	/**
	 * Super class' method that remove specify player with the same ID number
	 *
	 */
	public void remove(int id){
		
	}
	/**
	 * Super class' method that return current race data
	 *
	 */
	public File getRaceData(){
		return curRaceData;
	}
}
