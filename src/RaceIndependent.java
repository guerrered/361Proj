import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RaceIndependent {
	public long clockTime;
	public int runNumber=1;
	public int numRunners;
	List <Player> players = new ArrayList<>(9999);
	File curRaceData;
	static int fileNumber = 1;
	int queueStartNum = 0;
	int queueEndNum = 0;
	
	public RaceIndependent(){
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
		
		for(int i =0; i<9999; i++){//adds 9999 racers to list
			Player n = new Player(i);
			players.add(n);
		}
	}
	
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
	
	public void setDNF(){
		players.get(queueEndNum++).DNF();
	}
	/*
	 * starts player who is next in line
	 */
	public void startIND(long time){
		if(players.size()>queueStartNum){
			players.get(queueStartNum).start(time);
			queueStartNum++;
		}
	}
	
	public void finishIND(long time){
		players.get(queueEndNum).end(time);
		queueEndNum++;
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
						if(temp.isRunning() && players.get(i).isRunning()){//ensures runners are currently participating
						players.set(foundIndex, players.get(i));
						players.set(i, temp);
						return true;
						}
					}
				}
			}
		System.out.println("Players Can't be Swapped");
		//swap not successful
		return false;
	}
	/*
	 * player arg is next to run
	 */
	public void nextUp(int id){
		Player temp;
		for (int i =0; i < players.size(); i++){
			if(players.get(i).getID()==id){
				temp = players.get(i);
				players.remove(i);
				players.add(queueStartNum, temp);
			}
		}
	}
	
	//Prints to console in specified format
	public void printRace(){
		Printer p = new Printer();
		p.print(players, "IND");
	}
	
	// back in the queue as next to start
	public void cancel(){
		players.get(--queueStartNum).cancel();//cancel last runners start and make it next in line to start
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
