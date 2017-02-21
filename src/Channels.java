import java.util.ArrayList;
import java.util.List;

public class Channels {
    
	Boolean power=false;
	 static List <Channel> Channels = new ArrayList<>();
	
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
    public Boolean connect;
	public int ChNum=1;
    
    public Channel(int ChNum,Boolean connect)
    {
    	this.ChNum=ChNum;
    	this.connect=connect;
    	Channels.add(this);
    	ChNum++;
    }
    
   
 
    
	}
	
	
}
