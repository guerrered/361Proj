
public class test {
	public static void main(String[] args){
		System.out.println("Hello World!");
		
		Race a = new Race();
		Race b = new Race();
		Race c = new Race();
		
		a.addRunner(23);
		a.addRunner(34);
		a.addRunner(56);
		a.addRunner(32);
		a.addRunner(20);
		for(int i =0; i <5; i++){
			a.startRace();
		}
		
		for(int i =0; i <5; i++){
			a.players.get(i).end(System.nanoTime());
		}
		a.printRace();
	}
}
