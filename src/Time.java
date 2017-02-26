

/*
 * This class needs to run concurrrently to the rest of the program
 */
public class Time {
	int seconds;
	int hundreths;
	int millis;
	
	public Time(){
		this.seconds = 720;
		this.hundreths = 0;
		this.millis = 0;
	}
	
	public Time(int seconds, int hundreths, int millis){
		this.seconds =  seconds;
		this.hundreths = hundreths;
		this.millis = millis;
	}
	
	
	public void setTime(String s){//min:sec:hund.milli
		
		String[] timegetter = s.split(" ");
		String[] time = timegetter[0].split(":");
		this.seconds = Integer.parseInt(time[0]) * 60 + (Integer.parseInt(time[1]));
		String[] time2 = time[2].split("\\.");
		this.hundreths = Integer.parseInt(time2[0]);
		this.millis = Integer.parseInt(time2[1]);
	}
	
	public void count(){//is counting seconds;
		while( seconds < 9999){
			hundreths = 0;
			while(hundreths < 99){
				hundreths++;
				try {
					Thread.sleep(10);//sleeps 10 millis
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			seconds++;
		}
		System.out.println("Time OverFlow");
	}
	
	public long getTime(){//return in millis
		return (long) ((seconds * 1000) +  (hundreths * 10) + millis);
	}
}
