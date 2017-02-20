import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserInterface {
	Console console;
	
	public UserInterface(){
		//console = new Console();
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
		console.Exit();
	}
	
	/*
	 * Input format <TIMESTAMP> <CMD> <ARGUMENT LIST> <EOL>
	 * timestamp for console is time entered
	 */
	public void readFromFile(String fileName){//use testBuff.txt in RaceData folder
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
		while(scan.hasNext()){
			command = scan.nextLine();
			timeStamp = System.currentTimeMillis();
			toExec = timeStamp + " " + command;
			commandExec(toExec);
		}
	}
	
	public void commandExec(String command){
		System.out.println(command);
		
	}
}
