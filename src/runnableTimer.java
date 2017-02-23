
public class runnableTimer implements Runnable{
	public Time timer;
	
	public runnableTimer(Time t){
		this.timer = t;
	}
	public void run(){
		timer.count();
	}
}
