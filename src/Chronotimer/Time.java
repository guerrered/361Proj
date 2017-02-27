package Chronotimer;


/**
 * 
 * @author HiddenBit
 *
 */
public class Time {
	int seconds;
	int hundreths;
	int millis;
	
	/**
	 *constructor initiates time at 0:0:0.0  where format is min:sec:hundreths.millis
	 */
	public Time(){
		this.seconds = 0;
		this.hundreths = 0;
		this.millis = 0;
	}
	
	/**
	 * initiates time to seconds/60:seconds%60:hundreths.millis
	 * @param seconds 
	 * @param hundreths - can't be greater than 99
	 * @param millis - can't be greater than 9 
	 */
	public Time(int seconds, int hundreths, int millis){
		this.seconds =  seconds;
		this.hundreths = hundreths;
		this.millis = millis;
	}
	
	
	/**
	 * Sets current time to the given time
	 * 
	 * @param time - a string in the form min:sec:hundreths.millis
	 * where hundreths <= 99 and millis <= 9
	 */
	public void setTime(String time){
		
		String[] timegetter = time.split(" ");
		String[] timeSplit = timegetter[0].split(":");
		this.seconds = Integer.parseInt(timeSplit[0]) * 60 + (Integer.parseInt(timeSplit[1]));
		String[] time2 = timeSplit[2].split("\\.");
		this.hundreths = Integer.parseInt(time2[0]);
		this.millis = Integer.parseInt(time2[1]);
	}
	
	/**
	 * keeps track of time after being instantiated preferably concurrently to the machine
	 *  
	 */
	public void count(){
		while( seconds < 9999){
			hundreths = 0;
			while(hundreths < 99){
				hundreths++;
				millis = 0;
				while(millis < 9){
					try {
						Thread.sleep(1);//sleeps 1 milli
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					millis++;
				}
			}
			seconds++;
		}
		System.out.println("Time OverFlow");
	}
	
	/**
	 * 
	 * @return current time in millis as a long type  
	 */
	public long getTime(){
		return (long) ((seconds * 1000) +  (hundreths * 10) + millis);
	}
	
	/**
	 * 
	 * @return current Time as a string
	 */
	public String getTimeFancy(){
		String ret = seconds  /60 + ":" + seconds% 60+ ":" + hundreths;
		return ret;
	}
}
