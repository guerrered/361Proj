package Chronotimer;

public class ChannelListener implements Runnable{
	Channels ch;
	
	public ChannelListener(Channels channels){
		ch = channels;
	}
	
	public void run(){
		while(true){
			try{
				Thread.sleep(1);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
}
