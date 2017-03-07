package Sensors;
/**
 * 
 * @author HiddenBit
 *
 */


public class PAD extends Sensors {
boolean trigState;
int ChannelNum;

/**
 * Constructor for linking channel number to sensor
 *
 */
public PAD(int num)
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
	armed=false;
	this.ChannelNum=-1;
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
