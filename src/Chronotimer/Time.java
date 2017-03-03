package Chronotimer;


/**
 * 
 * @author HiddenBit
 *
 */
public class Time {
	long hours;
	long min;
	long seconds;
	long hundreths;
	long millis;
	
	/**
	 *constructor initiates time to current system time
	 */
	public Time(){
		long curr = System.nanoTime() / 1000000;
		this.hours = curr / 3600000;
		curr = curr % 3600000;
		this.min = curr / 60000;
		curr = curr % 60000;
		this.seconds = curr / 1000;
		curr = curr % 1000;
		this.hundreths = curr / 10;
		curr = curr % 10;
		this.millis = curr;
	}
	
	/**
	 * initiates time to seconds/60:seconds%60:hundreths.millis
	 * @param seconds 
	 * @param hundreths - can't be greater than 99
	 * @param millis - can't be greater than 9 
	 */
	public Time(long seconds, long hundreths, long millis){
		this.seconds =  seconds;
		this.hundreths = hundreths;
		this.millis = millis;
	}
	
	
	/**
	 * Sets current time to the given time
	 * 
	 * @param time - a string in the form   hour:min:sec.hundreth
	 * 
	 */
	public void setTime(String time){
		
		String[] timegetter = time.split(" ");
		String[] timeSplit = timegetter[0].split(":");
		this.hours = Long.parseLong(timeSplit[0]);
		this.min = Long.parseLong(timeSplit[1]);
		String[] time2 = timeSplit[2].split("\\.");
		this.seconds = Long.parseLong(time2[0]);
		this.hundreths = Long.parseLong(time2[1]) * 10;
	}
	
	/**
	 * keeps track of time after being instantiated preferably concurrently to the machine
	 *  
	 */
	public void count(){
		while(true){
			while(this.hours < 24){
				while(this.min < 60){
					while(this.seconds < 60){
						while(this.hundreths < 99){
							while(this.millis < 9){
								try {
									Thread.sleep(1);//sleeps 1 milli
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								this.millis++;
							}
							millis = 0;
							this.hundreths++;
						}
						hundreths = 0;
						this.seconds++;
					}
					seconds = 0;
					this.min++;
				}
				min = 0;
				this.hours++;
			}
			hours = 0;
		}
	}
	
	/**
	 * 
	 * @return current time in millis as a long type  
	 */
	public long getTime(){
		return (this.hours * 3600000) + (this.min * 60000) + (this.seconds * 1000) + (this.hundreths * 10) + this.millis;
	}
	
	/**
	 * 
	 * @return current Time as a string with format <hours>:<minutes>:<seconds>.<hundreths>
	 */
	public String getTimeFancy(){
		String hoursString;
		String minString;
		String secString;
		String hundString;
		if(this.hours < 10){
			hoursString = "0" + this.hours+":";
		}
		else{
			hoursString = this.hours + ":";
		}
		if(this.min < 10){
			minString = "0" + this.min+":";
		}
		else{
			minString = this.min + ":";
		}
		if(this.seconds < 10){
			secString = "0" + this.seconds + ".";
		}
		else{
			secString = this.seconds + ".";
		}
		if(this.hundreths < 10){
			hundString = "0" + this.hundreths;
		}
		else{
			hundString = "" + this.hundreths;
		}
		String ret = hoursString + minString  + secString + hundString;
		return ret;
	}
}
