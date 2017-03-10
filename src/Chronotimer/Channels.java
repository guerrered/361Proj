package Chronotimer;
import Sensors.*;


/**
 * 
 * @author HiddenBit
 *
 */
public class Channels {
    /*Channels section that load all the channels for the future use.
	 *
	 *
	 */
	Boolean power=false;
	  public static Sensors[] SensorSet = new Sensors[8];
		  static Channel Channels[] = new Channel[8];
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
			
			
		}
		
	 public static Sensors[] getSensorSet()
	 {
		 return SensorSet;
	 }
	 /**
		 * Called from the console to connect the channel with type of senser
		 * @param type
		 * @param num
		 */
	 public static void connect(String type,int num)
	 {
		 Channels[num-1].connect(type);
		 
	 }
	 
	 /**
		 * Return Channel base on the channel number as input
		 * @param num
		 */
	 
	 public static Channel getCh(int num)
	 {
		 return Channels[num-1];
	 }
	 
	 /**
		 * Tog(int ChNum)
		 * @param ChNum
		 * Change the power state of the channel, connect/disconnect
		 */
	 
	 public static void Tog(int ChNum)
	    {
	    Channels[ChNum-1].connect=!Channels[ChNum-1].connect;
	    }
	
	 /**
		 * Inner class channel
		 * 
		 */ 
	public class Channel
	{
    boolean connect=false;
    Sensors sens;
    
	public int ChNum;
    
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
	 * connect method for this channel to connect type of the senser
	 * @param type
	 */
    public void connect(String type)
	{
		
//		 if(this.connect=true)
	//	{
		//	System.out.println("Channel had been connected");
		//}
		//else
		//{
			this.connect=true;
			switch(type.toUpperCase())
			{
			case("EYE"):
				sens=new EYE(this.ChNum);
				SensorSet[this.ChNum-1]=sens;
			break;
			case("GATE"):
				sens=new GATE(this.ChNum);
				SensorSet[this.ChNum-1]=sens;
			break;
			case("PAD"):
				sens=new PAD(this.ChNum);
			    SensorSet[this.ChNum-1]=sens;
			break;
			default:
				System.out.println("Not such sensor\n");
				break;
			}
			
		//	System.out.println("Channel connected");
	//	}
		
	}
 
   /* 
    public void Notify()
    {
    	if(sens!=null)
    	{
    		if(sens.trigState())
    		{
    			//do something that trig the event start or finish
    			
    			sens.trigStateReset();
    			
    		}
    	}
    	
    	
    }
    */
    
    public Sensors getSens()
    {
    	return this.sens;
    }
    
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
    /*
	public void trig(RaceIndependent race)
	{
		if(this.connected())
		{
			switch(this.ChNum){
			case(1):
				race.startIND();
				break;
			case(2):
				race.finishIND();
				break;
			}
			
		}
		else
		{
			System.out.println("Channel has not been connected");
		}
		
	}
	*/
	
    
	}
	
	
	
}
