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
public class EYE extends Sensors{
boolean LightBeam;
	int ChannelNum;

	/**
	 * Constructor for linking channel number to sensor
	 *
	 */
public EYE(int num)
{
	this.ChannelNum=num;
	
}

/**
 * Method that unarmed the sensor on the channel 
 *
 */
public void unarmed(){
	if(armed==false)
	{
		System.out.println("Sensor is unarmed");
	}
	else{
		armed=false;
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
