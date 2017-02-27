import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserInterface {
	Console console;
	
	public UserInterface(){
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
			console = new Console();
			readFromFile(fileName);	
		}
		else{
			System.out.println("Reading from console");
			console = new Console();
			readFromConsole(scan);
		}
	}
	
	public void exit(){
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
				String[] timegetter = currentLine.split("\t");
				String[] time = timegetter[0].split(":");
				String[] time2 = time[2].split("\\.");//min:sec:hun.milli
				String[] mills = time2[1].split("\t");
				long commandTime = (Integer.parseInt(time[0]) * 60000) + (Integer.parseInt(time[1]) * 1000) + (Integer.parseInt(time2[0]) * 10) + (Integer.parseInt(mills[0]));
				while(commandTime > console.getTime()){//should wait till time is caught up
					try {
						Thread.sleep(1);//check up every milli could be slower/faster 
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
				String toExec = currentLine.replaceAll("\t"," ");
				commandExec(toExec);
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
			toExec = console.time.getTimeFancy() + " " + command;
			commandExec(toExec);
			System.out.println("Enter Command\n");
		}
	}
	
	public void commandExec(String command){
		System.out.println(command);
		String [] instructions = command.split(" ");
		instructions[1] = instructions[1].toUpperCase();
		switch(instructions[1]){
		case("POWER"):
			console.Power();
			break;
		case("RESET"):
			console.Reset();
			break;
		case("EXIT"):
			this.exit();
			break;
		case("TIME"):
			console.Time(instructions[2]);
			break;
		case("NEWRUN"):
			console.newRun();
			break;
		case("ENDRUN"):
			console.endRun();//might have to find and remove run with this name/ number ;
			break;
		case("NUM"):
			console.Num(Integer.parseInt(instructions[2]));
			break;
		case("SWAP"):
			console.Swap(Integer.parseInt(instructions[2]), Integer.parseInt(instructions[3]));
			break;
		case("DNF"):
			console.DNF();
			break;
		case("CLEAR"):
			console.Clear(Integer.parseInt(instructions[2]));
			break;
		case("CANCEL"):
			console.Cancel();
			break;
		case("PRINT"):
			console.Print();
			break;
		case("CONN"):
			console.Connect(instructions[1],Integer.parseInt(instructions[2]));
			break;
		case("DIS"):
			console.Disconnect(Integer.parseInt(instructions[2]));
			break;
		case("TOG"):
			console.Tog(Integer.parseInt(instructions[2]));
			break;
		case("TRIG"):
			console.Trig(Integer.parseInt(instructions[2]));
			break;
		case("START"):
			console.Start();
			break;
		case("FINISH"):
			console.Finish();
			break;
		case("EVENT"):
			console.Event(instructions[2]);
			break;
		default:
			System.out.println("Not a valid command\n");
			break;
		}
	}
}
