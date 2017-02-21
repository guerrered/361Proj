import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
			scan.close();
			readFromFile(fileName);	
		}
		else{
			System.out.println("Reading from console");
			readFromConsole(scan);
		}
	}
	
	public void exit(){
		// dont want to make it so abrupt
		System.out.println("Exiting...");
		System.exit(1);
	}
	
	/*
	 * Input format <TIMESTAMP> <CMD> <ARGUMENT LIST> <EOL>
	 * timestamp for console is time entered
	 */
	public void readFromFile(String fileName){//use RaceData/testBuff.txt
		try(BufferedReader buff = new BufferedReader(new FileReader(fileName))){
			String currentLine;
			while((currentLine = buff.readLine()) != null){
				commandExec(currentLine);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void readFromConsole(Scanner scan){
		//whole system exits with exit command
		String command;
		long timeStamp;
		String toExec;
		System.out.println("Enter Command\n");
		while(scan.hasNext()){
			command = scan.nextLine();
			timeStamp = System.currentTimeMillis();
			toExec = timeStamp + " " + command;
			commandExec(toExec);
			System.out.println("Enter Command\n");
		}
	}
	
	public void commandExec(String command){
		System.out.println(command);
		String [] instructions = command.split(" ");
		switch(instructions[1]){
		case("Power"):
			console.Power();
			break;
		case("Reset"):
			console.Reset();
			break;
		case("Exit"):
			this.exit();
			break;
		case("Time"):
			console.Time();
			break;
		case("newRun"):
			console.newRun();
			break;
		case("endRun"):
			console.endRun(Integer.parseInt(instructions[2]));//might have to find and remove run with this name/ number ;
			break;
		case("Swap"):
			console.Swap(Integer.parseInt(instructions[2]), Integer.parseInt(instructions[3]));
			break;
		case("DNF"):
			console.DNF(Integer.parseInt(instructions[2]));
			break;
		case("Clear"):
			console.Clear(Integer.parseInt(instructions[2]));
			break;
		case("Cancel"):
			console.Cancel();
			break;
		case("Print"):
			console.Print();
			break;
		case("Connect"):
			console.Connect();
			break;
		case("Disconnect"):
			console.Disconnect();
			break;
		case("Tog"):
			console.Tog();
			break;
		case("Trig"):
			console.Trig();
			break;
		case("Start"):
			console.Start();
			break;
		case("Finish"):
			console.Finish();
			break;
		default:
			System.out.println("Not a valid command\n");
			break;
		}
	}
}
