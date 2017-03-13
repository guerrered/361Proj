package Sensors;
/**
 * 
 * @author HiddenBit
 *
 */

import Chronotimer.Observer;

/**
 * Child class extends super class
 *
 */
public class GATE extends Sensors{
	
Observer obs;

/**
 * Constructor for linking channel number to sensor
 *
 */
public GATE()
{
	
	
}
public void register(Observer o){
	obs = o;
}
public void notifyObserver(){
	obs.update(1);
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


/**
 * Method that will trig the channel, it will be implement in spring 2 
 *
 */

 
	public boolean trig()
	{
		notifyObserver();
		
		return true;
	}
	
	

	
	
	
}
