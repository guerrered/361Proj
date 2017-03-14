package Chronotimer;
/**
 * 
 * @author HiddenBit
 *
 */
public class runnableTimer implements Runnable{
	public Time timer;
	/**
	 * Sets concurrent time object that will keep track of time.
	 * When ran it will start counting the seconds 
	 * @param t - a time object
	 */
	public runnableTimer(Time t){
		this.timer = t;
	}
	
	/**
	 * Execute timer
	 */
	public void run(){
		timer.count();
	}
}
