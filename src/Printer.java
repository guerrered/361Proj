import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Printer {
	
	public void exportToFile(List <Player> p, File file){
		//print data line for each player 
		BufferedWriter output;
		Player tempP;
		try {
			//write to file
			output = new BufferedWriter(new FileWriter (file));
			
			for(int i =0; i <p.size(); i++){
				tempP = p.get(i);
				if(tempP.DNF){
					output.write("ID:"+tempP.getID() + "\t start:" + tempP.startTime + "\t end:DNF"  + "\t place:" + (i+1) + "\n");

				}
				else{
					output.write("ID:"+tempP.getID() + "\t start:" + tempP.startTime + "\t end:" + tempP.endTime + "\t place:" + (i+1) + "\n");
				}
			}
			
			output.close();
		} catch (IOException e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		
	}
	
	public List<Player> sortByTime(List<Player> p){
		List<Player> sortedList = new ArrayList<>();
		
		return sortedList;
	}
}
