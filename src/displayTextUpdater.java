import javax.swing.JTextArea;

import Chronotimer.Console;

public class displayTextUpdater implements Runnable{
	JTextArea T;
	Console console;
	boolean interrupt;//used so we can tell if the thread is currently interrupted
	
	public displayTextUpdater(JTextArea ta, Console cons){
		T = ta;
		console = cons;
	}
	
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
	
	public void ExitInterrupt(){//exiting the interrupt cycle
		interrupt = false;
	}

}
