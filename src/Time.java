

/*
 * This class needs to run concurrrently to the rest of the program
 */
public class Time {
	int seconds;
	int hundreths;
	
	public Time(){
		seconds = 0;
		hundreths = 0;
		count();
	}
	
	public Time(int seconds, int hundreths){
		this.seconds =  seconds;
		this.hundreths = hundreths;
	}
	
	public Time(int minutes, int seconds, int hundreths){
		this.seconds = 60 * minutes  + seconds;
		this.hundreths = hundreths;
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
	
	public String getTime(){
		int minutes = seconds / 60; 
		int secs = seconds % 60;
		return minutes + ":" + secs + ":" + hundreths;
	}
}
