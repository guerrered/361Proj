import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Printer {
	
	public void exportToFile(List <Player> p, File file){
		//print data line for each player 
		BufferedWriter output;
		Player tempP;
		//start times
		String start;
		//finish times
		String finish;
		
		int place = 1;
		try {
			//write to file
			output = new BufferedWriter(new FileWriter (file, true));
			
			p = sortByTime(p);
			for(int i =0; i <p.size(); i++){
				tempP = p.get(i);
//				minS = (int) TimeUnit.NANOSECONDS.toMinutes(tempP.startTime);
//				minF = (int) TimeUnit.NANOSECONDS.toMinutes(tempP.endTime);
//				secS = (int) TimeUnit.NANOSECONDS.toSeconds(tempP.startTime);
//				secF = (int) TimeUnit.NANOSECONDS.toSeconds(tempP.endTime);
//				milliS = (int) TimeUnit.NANOSECONDS.toMillis(tempP.startTime)-(secS*1000);
//				milliF = (int) TimeUnit.NANOSECONDS.toMillis(tempP.endTime) - (secF*1000);
				
				if(!tempP.DNF){
					
					output.write("ID:"+tempP.getID() + "\t start:" + timeFormat(tempP.startTime) + "\t end:"+  
							timeFormat(tempP.endTime)+  "\t elapsed time: "+ timeFormat(tempP.endTime-tempP.startTime)
							+ "\t place:" + place + "\n");
					
					//increment the place number
					place++;
				}
				else{
					output.write("ID:"+tempP.getID() + "\t start:"+ timeFormat(tempP.startTime)+ "\t end:DNF" + "\n");
				}
			}
			
			output.close();
		} catch (IOException e) {
			System.out.println("failed");
			e.printStackTrace();
		}
		
		
	}
	 public static String timeFormat( long duration ) {
		    final TimeUnit scale = TimeUnit.NANOSECONDS;
		    
		    long days = scale.toDays(duration);
		    duration -= TimeUnit.HOURS.toNanos(days);
		    long hours = scale.toHours( duration );
		    duration -= TimeUnit.HOURS.toNanos( hours );
		    long minutes = scale.toMinutes( duration );
		    duration -= TimeUnit.MINUTES.toNanos( minutes );
		    long seconds = scale.toSeconds( duration );
		    duration -= TimeUnit.SECONDS.toNanos( seconds );
		    long millis = scale.toMillis( duration );
		    duration -= TimeUnit.MILLISECONDS.toNanos( seconds );
		    long nanos = scale.toNanos( duration );

		    return String.format(
		      "%d h, %d m, %d s, %d ms, %d ns",
		       hours, minutes, seconds, millis, nanos );
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
