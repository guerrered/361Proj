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
/**
 * 
 * @author HiddenBit
 *
 */


public class Printer {
	
	/**
	 * print() outputs the race times following the format: 
	 *
	 * <TIMESTAMP> <EVENT>
	 * <NUMBER> <EVENT> <EVENT	TIME>
	 * 
	 * @param p - the list of players that need to be printed.
	 * @param event - the event name 
	 */
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
	
	/**
	 * export() exports data to file (not used until Sprint 2)
	 * @param p - the list of players that will have their data exported.
	 * @param file - the file that will be written to.
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
	
	 /**
		 * idFormat() Format of the Player ID (adds zeros if number isn't 3 digits)
		 * @param num - the ID number to be formatted
		 * @return the ID zero filled
		 */
	public static String idFormat(int num){
		return String.format("%03d", num);
	}
	
	 /**
		 * timeFormat() converts milliseconds into hours minutes seconds and milliseconds.
		 * @param duration - In milliseconds
		 * @return properly formatted time
		 */
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
		    
		    return String.format("%d h, %02d m, %02d s, %03d hs",hours, minutes, seconds, millis);
	 }
	
	 /**
		 * sortByTime() Sort method that sort the order by time.
		 * @param p - list of players to be sorted
		 * @return List of sorted players
		 */
	public List<Player> sortByTime(List<Player> p){
		List<Player> sortedList = new ArrayList<>(p.size());
		sortedList.addAll(p);
	    
		sortedList.sort(timeComparator);
		return sortedList;
	}
	
	 /**
		 * Comparator that compare players time. 
		 * @return comparison value
		 */
	public static Comparator<Player> timeComparator = new Comparator<Player>(){
		public int compare(Player p1,Player p2){
			if(p1.DNF&&p2.DNF)return 0;
			if(p1.DNF)return 1;
			if(p2.DNF)return -1;
			return (int) (p1.totalTime - p2.totalTime);
		}
	};
}
