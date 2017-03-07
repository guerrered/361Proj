package Sensors;
//For Commands CONN<SENSOR><NUMBER>
/**
 * 
 * @author HiddenBit
 *
 */


/**
 * Super class 
 *
 */
public abstract class Sensors {
  Boolean armed;
	
	public abstract void unarmed();
	public abstract void armed();
	public abstract boolean trigState();
	public abstract void trigStateReset();
	
}
	



