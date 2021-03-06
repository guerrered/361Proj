import java.util.*;

import Chronotimer.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * 
 * @author HiddenBit
 *
 */
public class UserInterface {
	Console console;
	/**
	 * UserInterface constructor asks user if they want to use the
	 * console for input or if they have a testing file
	 */
	public UserInterface(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter 'C' for console input , 'F' for file input, and 'G' for GUI\n");
		String choice = scan.nextLine();
		while(!choice.equalsIgnoreCase("c") && !choice.equalsIgnoreCase("F")&&!choice.equalsIgnoreCase("G")){
			System.out.println("Enter 'C' for console input or 'F' for file input, and 'G' for GUI\n");
			choice = scan.nextLine();
		}
		if(choice.equalsIgnoreCase("f")){
			System.out.println("Enter the name of the input file");
			String fileName = scan.nextLine();
			File nameChecker  = new File(fileName);
			while(!nameChecker.exists()){
				System.out.println("Enter the name of the input file");
				fileName = scan.nextLine();
				nameChecker = new File(fileName);
			}
			scan.close();
			console = new Console();
			readFromFile(fileName);	
		}
		else if(choice.equalsIgnoreCase("c"))
		{
			System.out.println("Reading from console");
			console = new Console();
			readFromConsole(scan);
		}
		else
		{
			System.out.println("GUI is opening");
			console=new Console();
			new GUI(console);
			
		}
	}
	
	/**
	 * exit() terminates the program
	 */
	public void exit(){
		System.out.println("Exiting...");
		console.exit();
	}
	
	/**
	 * readFromFile(String) reads from a given file and executes its instructions in real time
	 * real time being the time the console reads. commandExec is then called with the instructions.
	 * <h2>NOTE THE FILE IS READ IN REAL CONSOLE TIME</h2>
	 * 
	 * @param fileName - passed by the constructor fileName is the name of  file that will be read/executed
	 */
	public void readFromFile(String fileName){//use RaceData/testBuff.txt
		try(BufferedReader buff = new BufferedReader(new FileReader(fileName))){
			String currentLine;
			while((currentLine = buff.readLine()) != null){
				String[] timegetter = currentLine.split("\t");
				String toExec = currentLine.replaceAll("\t",  " ");
				console.time.setTime(timegetter[0]);
				commandExec(toExec);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * readFromConsole() takes user input and prepends the time the console is reading 
	 * it then calls commandExec to execute the instructions
	 * 
	 * @param scan - The scanner that interacts with the user 
	 */
	public void readFromConsole(Scanner scan){
		String command;
		String toExec;
		System.out.println("Enter Command\n");
		while(scan.hasNext()){
			command = scan.nextLine();
			toExec = console.getTimeAsString() + " " + command;
			commandExec(toExec);
			System.out.println("Enter Command\n");
		}
	}
	
	/**
	 * commandExec() takes a string command that will be executed by the machine by using a switch
	 * to find the right command
	 * 
	 * @param command - the instruction to be executed by the machine
	 */
	public void commandExec(String command){
		System.out.println(command);
		String [] instructions = command.split(" ");
		instructions[1] = instructions[1].toUpperCase();
		switch(instructions[1]){
		case("POWER"):
			if(instructions.length == 2){
				console.Power();
			}
			else{
				System.out.println("Too many arguments");
			}
			break;
		case("PRINTPOWER"):
			if(instructions.length == 2){
				console.printerPower();
			}
			else{
				System.out.println("Too many arguments");
			}
			break;
		case("RESET"):
			if(instructions.length == 2){
				console.Reset();
			}
			else{
				System.out.println("Too many arguments");
			}
			break;
		case("EXIT"):
			if(instructions.length == 2){
				this.exit();
			}
			else{
				System.out.println("Too many arguments");
			}
			break;
		case("TIME"):
			if(instructions.length != 3){
				System.out.println("Format for time command is <hour>:<min>:<sec>.<hundreths>");
			}
			else{
				console.Time(instructions[2]);
			}
			break;
		case("NEWRUN"):
			if(instructions.length == 2){
				console.newRun();
			}
			else{	
				System.out.println("too Many Arguments");
			}
			break;
		case("ENDRUN"):
			if(instructions.length ==2){
				console.endRun();
			}
			else{
				System.out.println("Too many Arguments");
			}
			break;
		case("NUM"):
			if(instructions.length != 3){
				System.out.println("Enter a runnerId with NUM command");
			}
			else{
				console.Num(Integer.parseInt(instructions[2]));
			}
			break;
		case("SWAP"):
			if(instructions.length  == 4 ){//if extra arguments contained then they are the swap places
				console.Swap(Integer.parseInt(instructions[2]), Integer.parseInt(instructions[3]));
			}
			else if(instructions.length == 3)//swaps at lane
				console.Swap(Integer.parseInt(instructions[2]));
			else if(instructions.length == 2){
				console.Swap();
			}
			else{
				System.out.println("Too many arguments");
			}
			break;
		case("DNF"):
			if(instructions.length == 2){
				console.DNF();
			}	
			else if(instructions.length == 3){
				console.DNF(Integer.parseInt(instructions[2]));
			}
			else{
				System.out.println("Too many arguments");
			}
			break;
		case("CLEAR"):
			if(instructions.length != 3){
				System.out.println("Enter a runnerId with CLEAR command");
			}
			else{
				console.Clear(Integer.parseInt(instructions[2]));
			}
			break;
		case("CANCEL"):
			if(instructions.length == 2){
				console.Cancel();
			}	
			else if(instructions.length == 3){
				console.Cancel(Integer.parseInt(instructions[2]));
			}
			else{
				System.out.println("too many arguments");
			}
			break;
		case("PRINT"):
			if(instructions.length == 2){
				console.Print();
			}
			else{
				System.out.println("Too many argunments");
			}
			break;
		case("EXPORT"):
			if(instructions.length == 2){
				try {
					console.export();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println("too Many arguments");
			}
			break;
		case("CONN"):
			if(instructions.length == 4){
				console.Connect(instructions[2],Integer.parseInt(instructions[3]));
			}
			else{
				System.out.println("Enter a sensor type as well as a channel number");
			}
			break;
		case("DIS"):
			if(instructions.length == 3){
				console.Disconnect(Integer.parseInt(instructions[2]));
			}
			else{
				System.out.println("Enter channel num");
			}
			break;
		case("TOG"):
			if(instructions.length != 3){
				System.out.println("Enter a channel number with TOG command");
			}
			else{
				console.Tog(Integer.parseInt(instructions[2]));
			}
			break;
		case("TRIG"):
			if(instructions.length != 3){
				System.out.println("Enter a channel number with TRIG command");
			}
			else{
				//console.Trig(Integer.parseInt(instructions[2]));
				console.getChannels().getCh(Integer.parseInt(instructions[2])).Trig();
			}
			break;
		case("START"):
			if(instructions.length == 2){
				console.Trig(1);
			}
			else{
				System.out.println("Too many arguments");
			}
			break;
		case("FINISH"):
			if(instructions.length == 2){
				console.Trig(2);
			}
			else{
				System.out.println("Too many arguments");
			}
			break;
		case("EVENT"):
			if(instructions.length != 3){
				System.out.println("Enter a String with Event command");
			}
			else{
				console.Event(instructions[2]);
			}
			break;
		default:
			System.out.println("Not a valid command\n");
			break;
		}
	}
	
}
