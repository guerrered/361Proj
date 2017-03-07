package Sensors;
/**
 * 
 * @author HiddenBit
 *
 */


/**
 * Child class extends super class
 *
 */
public class GATE extends Sensors{
public boolean trigState;
int ChannelNum;	

/**
 * Constructor for linking channel number to sensor
 *
 */
public GATE(int num)
{
	this.ChannelNum=num;
	
}

/**
 * Method that unarmed the sensor on the channel 
 *
 */
public void unarmed(){
	if(armed=false)
	{
		System.out.println("Sensor is armed");
	}
	else{
	armed=true;
	ChannelNum=-1;
	}
}


/**
 * Method that armed the sensor on the channel 
 *
 */
public void armed()
{
	if(armed=true)
	{
		System.out.println("Sensor is armed");
	}
	else{
	armed=true;
	}
}

public boolean trigState()
{
	  return trigState;
}



/**
 * Method that will trig the channel, it will be implement in spring 2 
 *
 */

 
	public boolean trig()
	{
		//Channels.Channel ch=Channels.getCh(ChannelNum);
		//ch.trig(race);
		
		return trigState=!trigState;
	}
	
	

	
	
	
}
