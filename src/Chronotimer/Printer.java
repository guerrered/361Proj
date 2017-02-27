package Chronotimer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import Event.*;


public class Printer {
	
	public void print(List<Player> p, String event){
		Player tempP;
		for(int i =0; i <p.size(); i++){
			tempP = p.get(i);
			if(tempP.participated()){
			/*
			 * Format
			 * <TIMESTAMP> <EVENT>
			 * <NUMBER> <EVENT> <EVENT	TIME>
			 */
				if(!tempP.DNF){
					System.out.println(timeFormat(tempP.getStartTime()) + " " + event + '\t'+  
						p.get(i).getID() + " " + event + " " + timeFormat(tempP.getTotalTime()));
				}
				else{
						System.out.println(timeFormat(tempP.getStartTime()) + " " + event + '\t'+  
						p.get(i).getID() + " " + event + " DNF");	
					}
			}
			else{
				break;//since in order can just break here to save time
			}
		}
	}
	/*
	 * Not used in sprint 1?
	 */
	public void export(List <Player> p, File file){
		//print data line for each player 
		BufferedWriter output;
		Player tempP;
		int place = 1;
		
		try {
			//write to file
			output = new BufferedWriter(new FileWriter (file, true));
			p = sortByTime(p);
			for(int i =0; i <p.size(); i++){
				tempP = p.get(i);
				if(!tempP.DNF){
					output.write("ID:"+idFormat(tempP.getID()) + "\t start:" + timeFormat(tempP.startTime) + "\t end:"+  
							timeFormat(tempP.endTime)+  "\t elapsed time: "+ timeFormat(tempP.endTime-tempP.startTime)
							+ "\t place:" + place + "\n");
					//increment the place number
					place++;
				}
				else{
					output.write("ID:"+idFormat(tempP.getID()) + "\t start:"+ timeFormat(tempP.startTime)+ "\t end:DNF" + "\n");
				}
			}
			output.close();
		} catch (IOException e) {
			System.out.println("failed");
			e.printStackTrace();
		}	
	}
	
	public static String idFormat(int num){
		return String.format("%03d", num);
	}
	
	public static String timeFormat( long duration ) {
		    final TimeUnit scale = TimeUnit.MILLISECONDS;
		    
		    long days = scale.toDays(duration);
		    duration -= TimeUnit.HOURS.toMillis(days);
		    long hours = scale.toHours( duration );
		    duration -= TimeUnit.HOURS.toMillis( hours );
		    long minutes = scale.toMinutes( duration );
		    duration -= TimeUnit.MINUTES.toMillis( minutes );
		    long seconds = scale.toSeconds( duration );
		    duration -= TimeUnit.SECONDS.toMillis( seconds );
		    long millis = scale.toMillis( duration );
		    
		    return String.format("%d h, %02d m, %02d s, %03d ms",hours, minutes, seconds, millis);
	 }
	
	public List<Player> sortByTime(List<Player> p){
		List<Player> sortedList = new ArrayList<>(p.size());
		sortedList.addAll(p);
	    
		sortedList.sort(timeComparator);
		return sortedList;
	}
	
	public static Comparator<Player> timeComparator = new Comparator<Player>(){
		public int compare(Player p1,Player p2){
			if(p1.DNF&&p2.DNF)return 0;
			if(p1.DNF)return 1;
			if(p2.DNF)return -1;
			return (int) (p1.totalTime - p2.totalTime);
		}
	};
}