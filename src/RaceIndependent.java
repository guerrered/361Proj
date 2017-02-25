import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RaceIndependent extends Event {
	
	static int fileNumber = 1;
	static int queueStartNum = 0;
	static int queueEndNum = 0;
	
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
	}
	
	public boolean addRunner(int runnerID){
		//check to make sure runner doesn't already exist in the system.
		return super.addRunner(runnerID);
	}
	
	public boolean removeRunner(int runnerID){
		return super.removeRunner(runnerID);
	}
	
	public void setDNF(){
		/*
		for(int i =0; i<players.size(); i++){
			if(players.get(i).getID()==runnerID){
				players.get(i).DNF();
				break;
			}
		}*/
		players.get(queueEndNum++).DNF();
		//DNF shouldnt take parameters
		//also since technically done w/ incrementing qend# race prevents collision in finish IND
	}
	/*
	 * starts player who is next in line
	 */
	public void startIND(){
		if(players.size()>queueStartNum){
			players.get(queueStartNum).start(System.nanoTime());
			queueStartNum++;
		}
	}
	
	public void finishIND(){
		players.get(queueEndNum).end(System.nanoTime());
		queueEndNum++;
	}
	
	
	public boolean swapRacers(int p1, int p2){
		return super.swapRacers(p1, p2);
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
		super.printRace();
	}
	
	// back in the queue as next to start
	public void cancel(){
		players.get(--queueStartNum).cancel();//cancel last runners start and make it next in line to start
	}
	
	public void clear(){//possibly unneeded
		super.clear();
	}
	
	public List<Player> getPlayerList(){
		return super.players;
	}
	
	public File getRaceData(){
		return super.curRaceData;
	}
}
