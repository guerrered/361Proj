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
public class EYE extends Sensors{
boolean trigState;
	int ChannelNum;
	Observer obs;

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

public void register(Observer o){
	obs = o;
}
public void notifyObserver(){
	obs.update(1);
}

public void trigStateReset()
{
	trigState=false;
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
	notifyObserver();
	return trigState=!trigState;//return not needed?
		
}
	
	
}
