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
	List <Player> endList = new ArrayList<>();
	List <Player> displayList = new ArrayList<>();
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
	public Player getRacer(int ID){
		return players.get(ID);
	}
	/**
	 * Super class' method that record start time.
	 *
	 */
	public boolean start(long time){
		return true;
	}
	public boolean start(long time, int lane){
		return true;
	}
	
	/**
	 * Super class' method that record finish time
	 *
	 */
	public boolean finish(long time){	
		return true;
	}
	public boolean finish(long time, int lane){
		return true;
	}
	
	public boolean swap(){
		return false;
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
	 * @return 
	 *
	 */
	public boolean next(int ID){
		return false;
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
	public boolean remove(int id){
		return true;
	}
	/**
	 * Super class' method that return current race data
	 *
	 */
	public File getRaceData(){
		return curRaceData;
	}
	
	public int getFileNumber(){
		return fileNumber;
	}
	
	public void setFileNumber(int i){
		fileNumber = i;
	}
	
	public boolean contains(int id){
		for(int i=0; i<players.size(); i++){
			if(players.get(i).getID()==id)
				return true;
		}
		return false;
	}
	public List<Player> getEndList(){
		return endList;
	}
	
	/**
	 * 
	 * @returns the list of players that will be displayed on console screen
	 */
	public List<Player> getDisplayList(){
		/*
		 * we want to create a method in the console which will take a list and
		 * create a string with the relevant data player start / finish / current
		 * time which will then get passed to the relevant JTextArea in the console
		 * display
		 */
		return displayList;
	}
	public void endRace() {
		
	}
	public boolean setPlayerID(int iD1) {
		return false;
	}
	
}
