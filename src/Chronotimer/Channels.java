package Chronotimer;
import Sensors.*;

/**
 * 
 * @author HiddenBit
 *
 */
public class Channels implements Subject, Observer{
    /*Channels section that load all the channels for the future use.
	 *
	 *
	 */
		Boolean power=false;
		static Channel Channels[] = new Channel[8];
		Observer cons;
		int Triggered = -1;
		
		/**
		 * method that notifyObserver to do some action
		 * 
		 */
		public void update(int chNum){
			Triggered = chNum;
			notifyObserver();
		}
		
		/**
		 * method that register Observer for observing 
		 * 
		 */
		public void register(Observer o){
			cons = o;
		}
		

		/**
		 * method that notifyObserver and do action
		 * 
		 */
		public void notifyObserver(){
			cons.update(Triggered);
			Triggered = -1;
		}
		

		/**
		 * Constructor 
		 * 
		 */
		public Channels()  
		{

			Channel ch1=new Channel(1,false);
			Channel ch2=new Channel(2,false);
			Channel ch3=new Channel(3,false);
			Channel ch4=new Channel(4,false);
			Channel ch5=new Channel(5,false);
			Channel ch6=new Channel(6,false);
			Channel ch7=new Channel(7,false);
			Channel ch8=new Channel(8,false);
			Channels[0]=ch1; 
			Channels[1]=ch2;
			Channels[2]=ch3;
			Channels[3]=ch4;
			Channels[4]=ch5;
			Channels[5]=ch6;
			Channels[6]=ch7;
			Channels[7]=ch8;
			
			for(int i = 0; i < 7; i++){
				Channels[i].register(this);
			}
			
		}
		/**
		 * Called from the console to connect the channel with type of senser
		 * @param type
		 * @param num
		 */
	 
	 public void connect(String type,int num)
	 {
		 Channels[num-1].connect(type);
		 
	 }
	 

		/**
		 * method that disconnect channel and sensors based on the channel number
		 * 
		 */
	 public void disconnect(int num){
		 Channels[num - 1].disconnect();
		 Channels[num - 1].removeSens();
	 }
	 /**
		 * Return Channel base on the channel number as input
		 * @param num
		 */
	 
	 public  Channel getCh(int num)
	 {
		 return Channels[num-1];
	 }
	 
	 public boolean isConnected(int num){
		 return Channels[num -1].connect;
	 }
	 /**
		 * Tog(int ChNum)
		 * @param ChNum
		 * Change the power state of the channel, connect/disconnect
		 */
	 
	 public  void Tog(int ChNum)
	    {
	    Channels[ChNum-1].connect=!Channels[ChNum-1].connect;
	    }
	
	 /**
		 * Inner class channel
		 * 
		 */ 
	public class Channel implements Subject, Observer{
		public boolean connect=false;
		Sensors sens;
		public int ChNum;
		Observer obs;
		

		/**
		 * method that action  
		 * 
		 */
		public void update(int ChNum){
			Trig();
		}

		/**
		 * method that register Observer for observing 
		 * 
		 */
		public void register(Observer o){
			obs = o;
		}

		/**
		 * method that notify Observer to do action
		 * 
		 */
		public void notifyObserver(){
			obs.update(ChNum);
		}
    
		/**
		 * Constructor take the channel number and the initial state of connection
		 * @param ChNum
		 * @param connection
		 */
		public Channel(int ChNum,Boolean connect)//,String sensor type
		{
			this.ChNum=ChNum;
			this.connect=connect;
		}

		/**
		 * method that Notify the Observer
		 * 
		 */
		public void Trig(){
			notifyObserver();
		}
		/**
		 * connect method for this channel to connect type of the senser
		 * @param type
		 */
		public void connect(String type)
		{
		
			if(this.connect==true&&this.sens!=null)
			{
				System.out.println("Channel is currently connected");
			}
			else
			{
				this.connect=true;
				switch(type.toUpperCase())
				{
					case("EYE"):
						sens=new EYE();
						break;
					case("GATE"):
						sens=new GATE();
						break;
					case("PAD"):
						sens=new PAD();
						break;
					default:
						throw new IllegalArgumentException();
				}	
				sens.register(this);
			System.out.println("Channel connected");
		}
		
	}
	public String toString(){
		return "I'm " + ChNum;
	}
 

	/**
	 * method that return channel's sensor
	 * 
	 */
    public Sensors getSens()
    {
    	return this.sens;
    }
    
    public Boolean isSensor()
    {
       if(this.sens==null)
       {
    	return false;
       }
      else
       {
    	return true;
        }
    
    	
    }
    
	/**
	 * method that remove channel's sensor
	 * 
	 */
    public void removeSens()
    {
    	sens=null;
    }
    
    /**
	 * disconnect the Channel
	 * 
	 */
	public void disconnect()
	{
		if(this.connect==false)
		{
			System.out.println("Channel had been disconnected");
		}
		else
		{
			this.connect=false;
			//this.sens.unarmed();
			System.out.println("Channel " + this.ChNum + " disconnected");
			
		}
	}
	
	/**
	 * Return the state of this channel
	 * 
	 */
	public boolean connected(){
		return connect;
	}

    
	}
	
	
	
}
