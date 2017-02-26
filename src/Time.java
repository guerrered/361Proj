

/*
 * This class needs to run concurrrently to the rest of the program
 */
public class Time {
	int seconds;
	int hundreths;
	int millis;
	
	public Time(){
		seconds = 720;
		hundreths = 0;
	}
	
	public Time(int seconds, int hundreths){
		this.seconds =  seconds;
		this.hundreths = hundreths;
	}
	
	public Time(int minutes, int seconds, int hundreths){
		this.seconds = 60 * minutes  + seconds;
		this.hundreths = hundreths;
	}
	
	public void setTime(String s){
		String[] timegetter = s.split(" ");
		String[] time = timegetter[0].split(":");
		this.seconds = Integer.parseInt(time[0]) * 60 + (Integer.parseInt(time[1]));
		this.hundreths = Integer.parseInt(time[2]);
	}
	
	public void count(){//is counting seconds;
		while( seconds < 9999){
			hundreths = 0;
			while(hundreths < 99){
				hundreths++;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			seconds++;
		}
		System.out.println("Time OverFlow");
	}
	
	public long getTime(){
		return (long) seconds * 100 +  hundreths;
	}
}
