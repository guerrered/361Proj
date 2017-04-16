import javax.swing.JTextArea;

import Chronotimer.Console;

public class displayTextUpdater implements Runnable{
	JTextArea T;
	Console console;
	boolean interrupt;//used so we can tell if the thread is currently interrupted
	
	/**
	 * 
	 * @param ta jText area to alter
	 * @param cons console
	 */
	public displayTextUpdater(JTextArea ta, Console cons){
		T = ta;
		console = cons;
	}
	
	/**
	 * displays the current list of runners finished running in queue in real time
	 */
	// These threads should finish when the state is changed
	public void run() {
		while(console.getDisplayState()){//display state must be true aka listDisplay
			T.setText(console.DisplayListString());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				interrupt = true;//interrupt cycle for when we want other inputs on the screen and were on display list
				while(interrupt){
					try {
						Thread.sleep(1);
					} catch (InterruptedException e1) {
						System.out.println("All Good");
					}
				}
			}
		}
	}
	
	/**
	 * the thread may be interrupted while running in order to display the menu 
	 * when this is called we return to displaying the running list
	 */
	public void ExitInterrupt(){//exiting the interrupt cycle
		interrupt = false;
	}

}
