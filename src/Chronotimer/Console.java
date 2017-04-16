package Chronotimer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Event.*;
/**
 * 
 * @author HiddenBit
 *
 */

public class Console implements Observer{
	public boolean powerState = false;
	
	boolean CurRunOn = false;
	boolean displayState = false;//used to discern between list being displayed and menu
	public Printer printer;//true is the displayList false is menu;
	public Event race;
	public Time time;
	public menuStates menu;
	public boolean menuOn = false;
	Channels channels;
	String eventType;
	File log;
	public FileWriter logWriter;
	BufferedWriter bufferedLogWrite;
	
	/**
	 * instantiates a console with a time class that is counting concurrently to it
	 * also channels are available
	 */
	public Console(){
		time = new Time();
		channels = new Channels();
		channels.register(this);
		printer = new Printer();
		instantiateMenu();
		Runnable r1 = new runnableTimer(time);
		Thread timer = new Thread(r1);
		timer.start();
		log = new File ("RaceData/log.txt");
		try{
			log.createNewFile();
		}catch(IOException e){
			e.printStackTrace();
		}
		try {
			logWriter = new FileWriter(log,true);
			bufferedLogWrite = new BufferedWriter(logWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *  Method that implement from Observer class that to trigger channels
	 *  
	 */
	public void update(int ChNum){
		Trig(ChNum);
	}
	
	/**
	 * <p>if the machine is off it turns it on creating an independent race along with it 
	 * also turn on printer</p> 
	 * <p>else if the machine is on if no events are currently happening it will clear the race
	 *  and turn the printer off. else it states event is happening in case of accidental shutdown</p>
	 * 
	 */
	public boolean Power(){
		if(powerState)
		{
			for(int i = 1; i <= 8; i++){
				if(channels.isConnected(i)){
					Disconnect(i);
				}
			}
		}
		powerState = !powerState;
		if(powerState == true){
			this.eventType = "IND";//default
			//instantiateMenu();//we want to see the menu so turn it on
			openMenu();
			//this.race = new Independent();//default race is not instantiated right away
			//CurRunOn = true;
		}
		else{
			//save race contents first
			if(printer.PrinterPower){//if printer is on shut it off
				printer.Power();
			}
			closeMenu();
			displayState =false;
			CurRunOn =false;
			this.race = null;
		}
		writeToLog("Power");
		return powerState;
		
	}
	
	/**
	 *  exits system closes log beforehand
	 *  
	 */
	public void exit(){
			try {
				bufferedLogWrite.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				logWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		System.exit(1);
	}
	/**
	 *  Checks if machine is on and resets the time to 0 and creates a brand new race
	 *  setting the event to "IND" if it had changed also turns 
	 */
	public boolean Reset(){
		//start everything over
		if(onCheck()){
			time.setTime("0:0:0.0");
			for(int i = 1; i < 9;i++){//disconnect all channels
				if(channels.isConnected(i)){
					Disconnect(i);
				}
			}
			eventType = "IND";//default type of event;
			
			displayState = false;//no printing currentList
			openMenu();//we want to see menu
			
			if(race!=null){
				race.setFileNumber(1);
			}
			this.race = null;
			CurRunOn =false;
			//CurRunOn = true;
			if(printerOnCheck()){//turn printer off
				printerPower();
			}
			clearSavedData();
			log.delete();
			try {
				log.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				logWriter = new FileWriter(log);
				bufferedLogWrite = new BufferedWriter(logWriter);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
  
	/**
	 * if the machine is on it sets the current time to that of newtime
	 * @param newTime
	 */
	public void Time(String newTime){//sets time
		if(onCheck()){
			this.time.setTime(newTime);
		}
		writeToLog("Time " + newTime);
	}
	
	/**
	 * if the machine is on and there isnt an event currently happening it sets the 
	 * type of event to event otherwise it asks the user to end the current event
	 * @param event
	 */
	public boolean Event(String event){
		if(onCheck()){
			writeToLog("Event " + event);
			if(!curRunCheck()){
				this.eventType = event;
				//new event need to be created
				System.out.println("Event has changed to "+ event);
				return true;
			}
			else{
				System.out.println("An event is ongoing end it first.");//might want to return this as string
				return false;
			}
		}
		return false;
	}
	
	/**
	 * if the machine is on and there isnt an event currently it creates a new event of eventType 
	 */
	public boolean newRun(){
		if(onCheck()){
			writeToLog("newrun");
			if(!curRunCheck()){
				CurRunOn = true;		
				switch(eventType){//creates different types of races 
					case("IND"):
						this.race = new Independent();
						break;
					case("PARIND"):
						this.race = new parallelIndependent();
						break;
					case("GROUP"):
						this.race = new Group();
						break;
				}
				displayState = true;//can display a list from race
				return true;
			}
			else{
				System.out.println("End the current run first");
				return false;
			}
		}
		return false;
	}
	
	/**
	 * if the machine is on and there is an event currently running it ends it
	 */
	public boolean endRun(){
		if(onCheck()){//log old race
			writeToLog("endrun");
			
//			if(eventType.equals("GROUP")){
//				race.endRace();
//			}
			
			if(curRunCheck()){
				try {
					export();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				displayState = false;//cant diplay list anymore
				this.race = null;
				CurRunOn=false;
				return true;
			}
		}
		return false;
	}
	/**
	 * if the machine is on and an event is happening it 
	 * sets the racer associated with ID1 as next to start in the race
	 * @param ID1
	 */
	public void Num(int ID1){
		if(eventType.equals("GROUP")){
			race.setPlayerID(ID1);
		}
		
		else if(onCheck() && curRunCheck()){
			this.race.next(ID1);
		}
		writeToLog("Num " + ID1);
	}
	
	//deprecate
	/**
	 * if the machine is on and and event is happening it swaps the ending order of the
	 * racer associated with ID1 and ID2 
	 * @param ID1
	 * @param ID2
	 */
	public void Swap(int ID1, int ID2){
		if(onCheck() && curRunCheck()){
			this.race.swap(ID1, ID2);
		}
		writeToLog("Swap " + ID1 +" "+  ID2);
	}
	
	/**
	 *  Method that swap the first two runner
	 *  
	 */
	public void Swap(){
		if(onCheck() && curRunCheck()){
			switch(eventType){
				case("IND"):
					this.race.swap();
					break;
				case("PARIND")://if we just get a swap from the console it will swap the players in the first lane
					this.race.swap(1);
					break;
			}
			
		}
		writeToLog("Swap");
	}
	
	//deprecate
	/**
	 *  Method that swaps the next two runners to finish in a specific lane
	 *  
	 */
	public void Swap(int lane){
		if(onCheck() && curRunCheck()){
			if(eventType.equals("PARIND")){///check so it does not call event that is not parallel
				this.race.swap(lane);
			}
		}
		writeToLog("Swap " + lane);
	}
	
	/**
	 * if the machine is on and an event is currently happening
	 * the next runner to finish will get a DNF
	 */
	public void DNF(){
		if(onCheck() && curRunCheck()){
			switch(eventType){
			case("IND"):
				this.race.DNF();
				break;
			case("PARIND")://if we just get a swap from the console it will swap the players in the first lane
				this.race.DNF(1);
				break;
			}
		}
		writeToLog("DNF");
	}
	
	//deprecate
	/**
	 *  Method that sets the next runner to finish in a specific lane as DNF 
	 *  
	 */
	public void DNF(int lane){
		if(onCheck() && curRunCheck()){
			if(eventType.equals("PARIND")){
					this.race.DNF(lane);
			}
		}
		writeToLog("DNF " + lane);
	}
	
	/**
	 * if the machine is on and event is currently happening the runner associated with runnerID 
	 * will be removed from event
	 * @param runnerID
	 */
	public void Clear(int runnerID){
		if(onCheck() && curRunCheck()){
			this.race.remove(runnerID);
		}
		writeToLog("Clear " + runnerID);
	}
	/**
	 * if the machine is on and and event is currently happening the last runner to start will 
	 * be his start time cleared and will be place back as next to start
	 */
	public void Cancel(){
		if(onCheck() && curRunCheck()){
			this.race.cancel();
		}
		writeToLog("Cancel");
	}
	
	//deprecate
	public void Cancel(int lane){
		if(onCheck() && curRunCheck()){
			if(eventType.equals("PARIND")){
				this.race.cancel();
			}
		}
	}
	/**
	 * if machine is on and and event is currently happening the machine will print 
	 * all the participating runners
	 */
	public String Print(){
		if(onCheck() && curRunCheck()){
			writeToLog("Print");
			return printer.print(this.race.getEndList(), this.eventType);
		}
		return "";
	}
	
	/**
	 * swicthes the power of the printer
	 */
	public void printerPower(){
		if(onCheck()){
			writeToLog("PrinterPower");
			this.printer.Power();
		}
	}
	/**
	 * connects to channel chNum an sensor of type
	 * @param type
	 * @param ChNum
	 */
	public void Connect(String type,int ChNum){
		if(onCheck()){
			writeToLog("Connect " + type + " "+ChNum);
			channels.connect(type, ChNum);
		}
	}
	
	public boolean isConnected(int i){
		if(onCheck()){
			return channels.isConnected(i);
		}
		return false;
	}
	/**
	 * disconnect the sensor at chNum
	 * @param chNum
	 */
	public void Disconnect(int chNum){
		if(onCheck()){
			writeToLog("Disconnect " + chNum);
			channels.disconnect(chNum);
		}
	}
	/**
	 * if on turn the channel chNum off and vice versa
	 * @param chNum
	 */
	public boolean Tog(int chNum){
		if(onCheck()){
			writeToLog("Tog " + chNum);
			channels.Tog(chNum);
			return channels.getCh(chNum).connect; //true or false depending on channel state
		}										 // For testing purposes
		return false;//failed cause off
		
	}
	
	/** 
	 * triggers the channel chNum
	 * since it is an independent race trig 1 will start next in line
	 * trig 2 will end the current runner
	 * @param chNum
	 */
	public boolean Trig(int chNum){
		if(onCheck()){	
			writeToLog("Trig " + chNum);
			if(curRunCheck()){
				if(channels.isConnected(chNum)){
					switch(eventType){
						case("IND"):
							switch(chNum){
								case(1):
									race.start(this.time.getTime());
									break;
								case(2):
									race.finish(this.time.getTime());
									break;
								default:
									System.out.println("Not a Channel");
							}
							break;
						case("PARIND"):
							switch(chNum){
								case(1):
									race.start(this.time.getTime(),1);//lane 1
									break;
								case(2):
									race.finish(this.time.getTime(),1);
									break;
								case(3):
									race.start(this.time.getTime(),2);//lane 2
									break;
								case(4):
									race.finish(this.time.getTime(),2);
									break;
								default:
									System.out.println("Not a Channel");
							}
							break;
						case("GROUP"):
							switch(chNum){
								case(1):
									race.start(this.time.getTime());
									break;
								case(2):
									race.finish(time.getTime());
									break;
								default:
									System.out.println("Not a Channel");
							}
						}
						return true;
					}
					return false;
				}
				return false;
			}
			return false;
	}
	
	/**
	 * method that return time in long format
	 * 
	 */
	public long getTime(){
		return time.getTime();
	}
	
	/**
	 * method that return time in String format
	 * 
	 */
	public String getTimeAsString(){
		return time.getTimeFancy();
	}
	/**
	 * 
	 * @return boolean regarding the powerstate of the machine
	 * whether it is on or off
	 */
	public boolean onCheck(){
		return powerState;
	}
	
	public boolean printerOnCheck(){
		return printer.onCheck();
	}
	/**
	 * 
	 * @return boolean regarding an event currently being held
	 */
	private boolean curRunCheck(){
		return CurRunOn;
	}
	
	/**
	 * 
	 * @return string of event type
	 */
	public String getRaceType(){
		return eventType;
	}
	
	/**
	 * 
	 * @return int run number
	 */
	public int getRaceNum(){
		return race.runNumber;
	}
	
	/**
	 * 
	 * @return getter for channels
	 */
	public Channels getChannels(){
		return channels;
	}
	
	/**
	 * export() exports data to file
	 * @param file - the file that will be written to.
	 * @throws IOException 
	 */
	public File export() throws IOException{
		//TODO
		List<Player> p = race.getPlayerList();
		List<ExportObject> eo = new ArrayList<>();
		File file = race.createRaceOutputFile();
		FileWriter fw = new FileWriter(file);
		String data="";
		
		Gson gson = new Gson();
		
		
		for(Player player: p){
			if(player.participated()||player.cancel||player.running){
				if(player.DNF){
					eo.add(new ExportObject(time.getTimeFancy(), eventType, idFormat(player.getID()),"DNF" ,""));	
				}
				else if(player.isRunning()){
					eo.add(new ExportObject(time.getTimeFancy(), eventType, idFormat(player.getID()),"TRIG", ""));
				}
				else if(player.cancel){
					eo.add(new ExportObject(time.getTimeFancy(), eventType, idFormat(player.getID()),"CANCEL", ""));
				}
				else{
					eo.add(new ExportObject(time.getTimeFancy(), eventType, idFormat(player.getID()),"ELAPSED", timeFormat(player.getTotalTime())));
				}
				
			}
		}
		
		if(eo.size()==0){
			race.setFileNumber(race.getFileNumber()-1);
			fw.close();
			return null;
		}
		data = gson.toJson(eo);
		fw.write(data);
		fw.close();
		return file;
		
		
	}
	
	/**
	 * method that load file of a form of Json and set it back to Gson and return it
	 * 
	 */
	public List<ExportObject> load(File file){
		List<ExportObject> eo= new ArrayList<>();
		String str="";
		//File file = new File("USB/RUN"+idFormat(file1)+".txt");
		try {
			Scanner read = new Scanner(file);
			while(read.hasNext()){
				str+=read.next();
			}
			read.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		eo = gson.fromJson(str, new TypeToken<ArrayList<ExportObject>>() {}.getType());
		
		
		
		return eo;
	}
	/*
	 * clears USB folder
	 * 
	 * returns success
	 */
	public boolean clearSavedData(){
		File dir = new File("USB");
		if(dir.exists()){
			for(File file:dir.listFiles()){
				file.delete();
			}
			return true;
		}
		return false;
		
	}
	
	/**
	 * method that return string in certain format
	 * 
	 */
	public String toString(Player p){
		String str=time.getTimeFancy()+"\t"+eventType+ "\n" + idFormat(p.getID())+ "\t" + eventType + "\t" + timeFormat(p.totalTime)+"\n";
		return str;
		
	}
	 /**
		 * timeFormat() converts milliseconds into hours minutes seconds and milliseconds. Helper method for export.
		 * @param duration - In milliseconds
		 * @return properly formatted time
		 */
	public static String timeFormat( long duration ) {
		    final TimeUnit scale = TimeUnit.MILLISECONDS;
		    
		    long days = scale.toDays(duration);
		    duration -= TimeUnit.HOURS.toMillis(days);
		    long hours = scale.toHours( duration );
		    duration -= TimeUnit.HOURS.toMillis( hours );
		    long minutes = scale.toMinutes( duration );
		    duration -= TimeUnit.MINUTES.toMillis( minutes );
		    long seconds = scale.toSeconds( duration );
		    duration -= TimeUnit.SECONDS.toMillis( seconds );
		    long millis = scale.toMillis( duration );
		    
		    return String.format("%d:%02d:%02d:%03d",hours, minutes, seconds, millis);
	 }
	 /**
	 * idFormat() Format of the Player ID (adds zeros if number isn't 3 digits)
	 * @param num - the ID number to be formatted
	 * @return the ID zero filled
	 */
	public static String idFormat(int num){
		return String.format("%03d", num);
	}
	
	/**
	 * 
	 * 
	 */
	public void writeToLog(String toLog){
		try {
			bufferedLogWrite.write(getTimeAsString() + " " + toLog + " \n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @return the list that will be displayed on console display
	 */
	public List<Player> getDisplayList(){
		if(onCheck() && curRunCheck()){
			return race.getDisplayList();
		}
		return null;
	}
	

	/**
	 * 
	 * @return string in the format that it will be printed in display
	 * F is printed aside if runner finished
	 * R if they are currently running
	 * < if they are the next on the queue
	 */
	public String DisplayListString(){
		if(onCheck() && curRunCheck()){
		List<Player> displayList = getDisplayList();
		String asString = "";
		int count = 0;//count is 0 for group since only 1 runner is printed at a time
		if(eventType.equals("PARIND")){
			count = 2;
		}
		if(eventType.equals("IND")){
			count = 1;
		}
		for(int i = 0; i < displayList.size(); i++){//if finished use finish time else currenttime
			Player temp = displayList.get(i);
			String tempString = "";
			if(temp.participated()){
				if(temp.DNF){
					tempString += temp.toString() + "\tDNF\tF\n";
				}
				else{
					tempString += temp.toString() + "\t" + timeConvert(temp.getEndTime() - temp.getStartTime()) + "\tF\n";
				}
			}
			
			else if(temp.isRunning()){
				tempString += temp.toString() + "\t" + timeConvert(time.getTime() - temp.getStartTime()) + "\tR\n";
			}
			else{
				if(count > 0){
					if(temp.wasCanceled()){
						tempString += temp.toString() + "\tCanceled<\n";//usually next in queue might as well check
					}
					else{
						tempString += temp.toString() + "\t" + timeConvert(time.getTime()) + "<\n";//currentTime next in queue
					}
					count--;
				}
				else{
					tempString += temp.toString() + "\t" + timeConvert(time.getTime()) + "\n";//currentTime
				}
			}
			tempString = tempString.concat(asString);
			asString = tempString;
		}
		return asString;
		}
		return "";//emptyString
	}
	
	
	/**
	 * instantiates a menu 
	 * @return the first state in the menu
	 */
	public String instantiateMenu(){
		menu = new menuStates();
		return menu.getCurrentState();
	}
	
	/**
	 * moves the menu according to an up click
	 */
	public void menuUP(){
		if(onCheck()){
			if(isMenuOn()){
				menu.up();
			}
		}
	}
	
	/**
	 * moves the menu according to an down click
	 */
	public void menuDOWN(){
		if(onCheck()){
			if(isMenuOn()){
				menu.down();
			}
		}
	}
	
	/**
	 * moves the menu according to a left click
	 */
	public void menuLEFT(){
		if(onCheck()){
			if(isMenuOn()){
				menu.prev();
			}	
		}
	}
	
	/**
	 * moves the menu according to an down click
	 */
	public void menuRIGHT(){
		if(onCheck()){
			if(isMenuOn()){
				menu.next();
			}
		}
	}
	/**
	 * 
	 * @return the current menu state 
	 */
	public String getMenu(){
		if(onCheck()){
			if(isMenuOn()){
				return menu.getCurrentState();
			}
		}
		return "";
	}
	
	/**
	 * closes the menu 
	 */
	public void closeMenu(){//discerned by machine running console
		menuOn = false;
	}
	/**
	 * allows the menu to be viewed again and resets to default state
	 */
	public void openMenu(){
		menuOn = true;
		menu.resetState();
	}
	
	/**
	 * 
	 * @return true if menu is available
	 */
	public boolean isMenuOn(){
		return menuOn;
	}
	/**
	 * 
	 * @param currentTime a long variable denoting a time value
	 * @return the time value as a string
	 */
	public String timeConvert(long currentTime){
			long hours = currentTime / 3600000;
			currentTime = currentTime % 3600000;
			long min = currentTime / 60000;
			currentTime = currentTime % 60000;
			long seconds = currentTime / 1000;
			currentTime = currentTime % 1000;
			long hundreths = currentTime / 10;
			//currentTime = currentTime % 10;
			
			String hoursString;
			String minString;
			String secString;
			String hundString;
			if(hours < 10){
				hoursString = "0" + hours+":";
			}
			else{
				hoursString = hours + ":";
			}
			if(min < 10){
				minString = "0" +min+":";
			}
			else{
				minString = min + ":";
			}
			if(seconds < 10){
				secString = "0" + seconds + ".";
			}
			else{
				secString = seconds + ".";
			}
			if(hundreths < 10){
				hundString = "0" + hundreths;
			}
			else{
				hundString = "" + hundreths;
			}
			String ret = hoursString + minString  + secString + hundString;
			return ret;
		
	}
	
	/**
	 * 
	 * @return boolean if the display List can be displayed
	 */
	public boolean getDisplayState(){
		return displayState;
	}
	
	
	/**
	 * switches the current displayState
	 */
	public void changeDisplayState(){
		displayState = !displayState;
	}
}
