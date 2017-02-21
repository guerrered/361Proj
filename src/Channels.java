import java.util.ArrayList;
import java.util.List;

public class Channels {
    
	Boolean power=false;
	 static List <Channel> Channels = new ArrayList<>();
	
	 public static Channel getCh(int num)
	 {
		 Channel ch=null;
		 for(int i=0;i<Channels.size();i++)
	      {
	    	  if(Channels.get(i).ChNum==num)
	    	  {
	    		  ch=Channels.get(i);
	    	  }
	      } 
		 
		 return ch;
	 }
	 
	 public static void Tog(int ChNum)
	    {
	    for(int i=0;i<Channels.size();i++)
	      {
	    	  if(Channels.get(i).ChNum==ChNum)
	    	  {
	    		  Channels.get(i).connect=!Channels.get(i).connect;
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
    
    
    
    
	}
	
	
}
