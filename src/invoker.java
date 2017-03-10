


import Chronotimer.Console;
import Sensors.*;

public class invoker {


Console console;
	
	
    public invoker(Console R)	
     {
    	this.console=R;
		
     }
	
    public void Invoke(Sensors sen)
    {
    	int chanNum=sen.getPairNum();
    	
    	switch(chanNum)
    	{
    	case(1):	
    	console.Trig(1);
    	break;
    	case(2):	
    	console.Trig(2);	
    	break;  
    	case(3):
    	console.Trig(3);
    	break;
    	case(4):
    	console.Trig(4);
    	break;
    	}
    
    		
    		
     }
    	
 }
    
    
    
	

