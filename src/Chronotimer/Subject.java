package Chronotimer;
/**
 * 
 * @author HiddenBit
 *
 */


/**
 * Interface
 */
public interface Subject {
	public void register(Observer o);
	public void notifyObserver();
}
