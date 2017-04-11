import javax.swing.JTextArea;

import Chronotimer.Console;

public class displayTextUpdater implements Runnable{
	JTextArea T;
	Console console;
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
