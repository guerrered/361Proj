package Sensors;
import Chronotimer.Subject;
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
public abstract class Sensors implements Subject {
  Boolean armed;
	
	public abstract void unarmed();
	public abstract void armed();

}
	



