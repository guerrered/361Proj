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
public boolean Pushed;
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
	if(armed=true)
	{
		System.out.println("Sensor is armed");
	}
	armed=true;
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
	armed=true;
}

/**
 * Method that will trig the channel, it will be implement in spring 2 
 *
 */
	public void trig()
	{
		//Channels.Channel ch=Channels.getCh(ChannelNum);
		//ch.trig(race);
		
	}
	
	

	
	
	
}
