import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Printer {
	
	public void exportToFile(List <Player> p, File file){
		//print data line for each player 
		BufferedWriter output;
		Player tempP;
		int place = 1;
		try {
			//write to file
			output = new BufferedWriter(new FileWriter (file));
			p = sortByTime(p);
			for(int i =0; i <p.size(); i++){
				tempP = p.get(i);
				if(!tempP.DNF){
					output.write("ID:"+tempP.getID() + "\t start:" + tempP.startTime + "\t end:" + tempP.endTime +   "\t elapsed time: "+ tempP.totalTime + "\t place:" + place + "\n");
					place++;
				}
				else{
					output.write("ID:"+tempP.getID() + "\t start:" + tempP.startTime + "\t end:DNF" + "\n");
				}
			}
			
			output.close();
		} catch (IOException e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		
	}
	
	public List<Player> sortByTime(List<Player> p){
		List<Player> sortedList = new ArrayList<>(p.size());
		sortedList.addAll(p);
	    
		sortedList.sort(timeComparator);
		return sortedList;
	}
	
	public static Comparator<Player> timeComparator = new Comparator<Player>(){
		public int compare(Player p1,Player p2){
			return (int) (p1.totalTime - p2.totalTime);
		}
	};
}
