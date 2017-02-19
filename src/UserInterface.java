import java.util.*;

public class UserInterface {
	Console console;
	
	public UserInterface(){
		console = new Console();
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter 'C' for console input or 'F' for file input\n");
		String choice = scan.nextLine();
		while(!choice.equalsIgnoreCase("c") && !choice.equalsIgnoreCase("F")){
			System.out.println("Enter 'C' for console input or 'F' for file input\n");
			choice = scan.nextLine();
		}
		if(choice.equalsIgnoreCase("f")){
			System.out.println("Enter the name of the input file");
			String fileName = scan.nextLine();
			readFromFile(fileName);
			scan.close();
		}
		else{
			System.out.println("Reading from console");
			readFromConsole(scan);
		}
	}
	
	public void exit(){
		// dont want to make it so abrupt
		System.exit(1);//exit sim
	}
	
	/*
	 * Input format <TIMESTAMP> <CMD> <ARGUMENT LIST> <EOL>
	 * timestamp for console is time entered
	 */
	public void readFromFile(String fileName){
		//open file and read from each line;
		//when end is reached prompt to exit program?
	}
	
	public void readFromConsole(Scanner scan){
		//whole system exits with exit command
	}
}
