
public class Channels {
    
	Boolean power=false;
	  
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
	  
	 
	 
	 
	 public static Channel getCh(int num)
	 {
		 Channel ch=null;
		 for(int i=0;i<Channels.length;i++)
	      {
	    	  if(Channels[i].ChNum==num)
	    	  {
	    		  ch=Channels[i];
	    	  }
	      } 
		 
		 return ch;
	 }
	 
	 public static void Tog(int ChNum)
	    {
	    for(int i=0;i<Channels.length;i++)
	      {
	    	  if(Channels[i].ChNum==ChNum)
	    	  {
	    		  Channels[i].connect=!Channels[i].connect;
	    	  }
	      }
	    	
	    }
	
	
	
	public class Channel
	{
    public Boolean connect=false;
	public int ChNum;
    
    public Channel(int ChNum,Boolean connect)
    {
    	this.ChNum=ChNum;
    	this.connect=connect;
    }
    
    public void connect()
	{
		
		 if(this.connect=true)
		{
			System.out.println("Channel had been connected");
		}
		else
		{
			this.connect=true;
			System.out.println("Channel connected");
		}
		
	}
	public void disconnect()
	{
		if(this.connect==false)
		{
			System.out.println("Channel had been disconnected");
		}
		else
		{
			this.connect=false;
			System.out.println("Channel disconnected");
			
		}
	}
    public void getTime()
    {
    	
    	
    }
	
    
    
    
	}
	
	
}