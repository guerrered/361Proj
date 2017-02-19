import java.util.Random;

public class test {
	public static void main(String[] args){
		System.out.println(System.nanoTime());
		Race a = new Race();
		Race b = new Race();
		Race c = new Race();
		a.addRunner(23);
		a.addRunner(34);
		a.addRunner(56);
		a.addRunner(32);
		a.addRunner(20);
		Random rand = new Random();
		for(int i =0; i <5; i++){
			a.startIND();
			try {
				Thread.sleep(rand.nextInt((3000) + 1) + 1000);

				System.out.println("starting "+i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i =0; i <4; i++){
			try {
				Thread.sleep(rand.nextInt((3000) + 1) + 1000);
				System.out.println("ending "+i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			a.finishIND();
		}
		a.players.get(4).DNF();
		a.printRace();
	}
}
