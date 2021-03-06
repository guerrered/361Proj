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
	Boolean PrinterPower=false;
	
	public void Power()
	{
		PrinterPower=!PrinterPower;
	}
	
	public boolean onCheck(){
		return PrinterPower;
	}
	
	
	/**
	 * print() outputs the race times following the format: 
	 *
	 * <TIMESTAMP> <EVENT>
	 * <NUMBER> <EVENT> <EVENT	TIME>
	 * 
	 * @param p - the list of players that need to be printed.
	 * @param event - the event name 
	 */
	public String print(List<Player> p, String event){///might want to sort finish times with parind//
		Player tempP;
		String ret = "";
		for(int i =0; i <p.size(); i++){
			tempP = p.get(i);
			//if(tempP.participated()){
			/*
			 * Format 
			 * <START> <EVENT>
			 * <NUMBER> <FINISH> <EVENT	TIME>
			 */
				if(tempP.isCanceled()){
					ret+="<" + tempP.toString() + ">\tCANCELED\n";
				}
				else if(!tempP.DNF){
					//ret += "Start: "+timeFormat(tempP.getStartTime()) + " Event: " + event + '\t'+ "ID: "+  
					//	p.get(i).getID() + " Finish: " + timeFormat(tempP.endTime) + " Total: " + timeFormat(tempP.getTotalTime()) +"\n";
					ret += "<" +tempP.toString() +">\t" + timeFormat(tempP.getTotalTime()) + "\n";
				}
				else{
						//ret += "Start: "+timeFormat(tempP.getStartTime()) + " Event: " + event + '\t'+ "ID: "  
						//+p.get(i).getID() + " DNF\n";	
						ret += "<" +tempP.toString() +">\tDNF\n";
					}
			//}
			/*else{
				break;//since in order can just break here to save time
			}*/
		}
		System.out.println(ret);
		return ret;
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
		    
		    return String.format("%d:%02d:%02d:%03d",hours, minutes, seconds, millis);
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
