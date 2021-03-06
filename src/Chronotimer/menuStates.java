package Chronotimer;

public class menuStates {	
	int[][] menu;
	int stateIndex;
	int stringIndex;
	String lastValid;
	String currentState;
	String[] menuItems;
	/**
	 * instantiates a menu and the movements between its states
	 */
	//We gotta make sure we test all the movements between states
	public menuStates(){
		//main menu
		stateIndex= 0;
		String s1 = "1.Event<\n2.RaceOperations\n3.Run\n4.Print\n5.FileExport\n6.Reset\n7.Time\n";
		String s2 = "1.Event\n2.RaceOperations<\n3.Run\n4.Print\n5.FileExport\n6.Reset\n7.Time\n";
		String s3 = "1.Event\n2.RaceOperations\n3.Run<\n4.Print\n5.FileExport\n6.Reset\n7.Time\n";
		String s4 = "1.Event\n2.RaceOperations\n3.Run\n4.Print<\n5.FileExport\n6.Reset\n7.Time\n";
		String s5 = "1.Event\n2.RaceOperations\n3.Run\n4.Print\n5.FileExport<\n6.Reset\n7.Time\n";
		String s6 = "1.Event\n2.RaceOperations\n3.Run\n4.Print\n5.FileExport\n6.Reset<\n7.Time\n";
		String s7 = "1.Event\n2.RaceOperations\n3.Run\n4.Print\n5.FileExport\n6.Reset\n7.Time<\n";
		//event submenu
		String s8 = "1.IND<\n2.PARIND\n3.GRP\n4.PARGRP";
		String s9 = "1.IND\n2.PARIND<\n3.GRP\n4.PARGRP";
		String s10 = "1.IND\n2.PARIND\n3.GRP<\n4.PARGRP";
		String s11 = "1.IND\n2.PARIND\n3.GRP\n4.PARGRP<";
		//raceop submenu
		String s12 = "1.DNF<\n2.CANCEL";
		String s13 = "1.DNF\n2.CANCEL<";
		//run SubMenu
		String s14 = "1.NEWRUN<\n2.ENDRUN";
		String s15 = "1.NEWRUN\n2.ENDRUN<";
		
		String s16 = "print";
		String s17 = "export";
		String s18 = "ind";
		String s19 = "parind";
		String s20 = "grp";
		String s21 = "paragrp";
		String s22 = "dnf";
		String s23 = "cancel";
		String s24 = "newrun";
		String s25 = "endrun";
		String s26 = "exit";
		String s27 = "reset";
		String s28 = "time";
		
		menuItems = new String[28];
		menuItems[0] = s1;
		menuItems[1] = s2;
		menuItems[2] = s3;
		menuItems[3] = s4;
		menuItems[4] = s5;
		menuItems[5] = s6;
		menuItems[6] = s7;
		menuItems[7] = s8;
		menuItems[8] = s9;
		menuItems[9] = s10;
		menuItems[10] = s11;
		menuItems[11] = s12;
		menuItems[12] = s13;
		menuItems[13] = s14;
		menuItems[14] = s15;
		menuItems[15] = s16;
		menuItems[16] = s17;
		menuItems[17] = s18;
		menuItems[18] = s19;
		menuItems[19] = s20;
		menuItems[20] = s21;
		menuItems[21] = s22;
		menuItems[22] = s23;
		menuItems[23] = s24;
		menuItems[24] = s25;
		menuItems[25] = s26;
		menuItems[26] = s27;
		menuItems[27] = s28;
				
		currentState = s1;
		lastValid = s1;
		
		// 0-up  1-down 2-right 3-left
		menu = new int[4][15];//4*12 grid of menu states
		menu[0][0] = 6;//the answer is the vertical index of state
		menu[0][1] = 0;
		menu[0][2] = 1;
		menu[0][3] = 2;
		menu[0][4] = 3;
		menu[0][5] = 4;
		menu[0][6] = 5;
		
		menu[0][7] = 10;
		menu[0][8] = 7;
		menu[0][9] = 8;
		menu[0][10] = 9;
		
		menu[0][11] = 12;
		menu[0][12] = 11;
		
		menu[0][13] = 14;
		menu[0][14] = 13;
		
		
		//--going down
		menu[1][0] = 1;
		menu[1][1] = 2;
		menu[1][2] = 3;
		menu[1][3] = 4;
		menu[1][4] = 5;
		menu[1][5] = 6;
		menu[1][6] = 0;
		
		menu[1][7] = 8;
		menu[1][8] = 9;
		menu[1][9] = 10;
		menu[1][10] = 7;
		
		menu[1][11] = 12;
		menu[1][12] = 11;
		
		menu[1][13] = 14;
		menu[1][14] = 13;
		
		
		//--moving right
		menu[2][0] = 7;
		menu[2][1] = 11;
		menu[2][2] = 13;
		menu[2][3] = 15;
		menu[2][4] = 16;
		menu[2][5] = 26;
		menu[2][6] = 27;
		
		menu[2][7] = 17;
		menu[2][8] = 18;
		menu[2][9] = 19;
		menu[2][10] = 20;
		
		menu[2][11] = 21;
		menu[2][12] = 22;
		
		menu[2][13] = 23;
		menu[2][14] = 24;
		
		//--moving left
		menu[3][0] = 25;//exit menu
		menu[3][1] = 25;//exit menu
		menu[3][2] = 25;//exit menu
		menu[3][3] = 25;//exit menu
		menu[3][4] = 25;//exit menu
		menu[3][5] = 25;//main menu
		menu[3][6] = 25;//main menu
		
		menu[3][7] = 0;//main menu
		menu[3][8] = 0;//main menu
		menu[3][9] = 0;//main menu
		menu[3][10] = 0;//main menu
		
		menu[3][11] = 1;//main menu
		menu[3][12] = 1;
		
		menu[3][13] = 2;
		menu[3][14] = 2;
		
	}
	
	
	/**
	 * moves the current menu state to the next according to a right button click
	 */
	public void next(){//2
		int nextState = menu[2][stateIndex];
		String nextStateVal = menuItems[nextState];
		if(nextState <= 14){
			stateIndex = nextState;
			lastValid = menuItems[nextState];
		}
		currentState = nextStateVal;
	}
	/**
	 * moves the current menu state to the next according to a left button click
	 */
	public void prev(){//3
		int nextState = menu[3][stateIndex];
		String nextStateVal = menuItems[nextState];
		if(nextState <= 14){
			stateIndex = nextState; 
			lastValid = menuItems[nextState];
		}
		currentState = nextStateVal;
	}
	/**
	 * moves the current menu state to the next according to a up button click
	 */
	public void up(){//0
		int nextState = menu[0][stateIndex];
		String nextStateVal = menuItems[nextState];
		if(nextState <= 14){
			stateIndex = nextState; 
			lastValid = menuItems[nextState];
		}
		currentState = nextStateVal;
	}
	
	/**
	 * moves the current menu state to the next according to a down button click
	 */
	public void down(){//1
		int nextState = menu[1][stateIndex];
		String nextStateVal = menuItems[nextState];
		if(nextState <= 14){
			stateIndex = nextState;
			lastValid = menuItems[nextState];
		}
		currentState = nextStateVal;
	}
	
	
	/**
	 * resets the menu to initial state
	 */
	public void resetState(){
		currentState = menuItems[0];//go back to initial state
		lastValid = menuItems[0];
		stateIndex = 0;
	}
	
	/**
	 * 
	 * @return the current menu state as a string
	 */
	public String getCurrentState(){
		return currentState;
	}
	
	public String getLastValidState(){
		return lastValid;
	}
	/*	1-up	 2-down		3-next	4-prev
	 * 
	 * 12 menu states that can 
	 * 
	 * 
	 * mainMenu
	 * 0				1				2				3				4
	 * eventSub		raceOPSub		runSub			Print			export			
	 * 
	 * 1-export		1-eventSub		1-raceOPSub		1-runSub		1-print
	 * 2-raceOPSub	2-runSub		2-Print			2-export		2-eventSub
	 * 3-eventSubIN	3-raceOPSubin	3-runSubin		3-print()		3-export()
	 * 4-exitMENU	4-exitMENU		4-exitMENU		4-exitMENU		4-exitMENU
	 * 
	 * 
	 * 
	 * evenSubMENU
	 * 
	 * 5					6				7
	 * IND				PARIND				GRP
	 * 1-grp			1-IND				1-parIND
	 * 2-parIND			2-GRP				2-IND
	 * 3-event(ind)		3-event(PARIND)		3-event(GRP)
	 * 4-mainMenu		4-mainMenu			4-mainMEnu
	 * 
	 * 
	 * raceOPSubMENU
	 * 8					9
	 * DNF				CANCEL		
	 * 1-cancel			1-DNF
	 * 2-cancel			2-DNF
	 * 3-DNF()			3-CANCEL()
	 * 4-mainMEnu		4-mainMEnu	
	 * 	
	 * 
	 * runSubMENU
	 * 10				11
	 * endrun			newRun
	 * 1-newrun			1-endrun
	 * 2-newrun			2-endrun
	 * 3-endrun()		3-newRun()
	 * 4-mainMenu		4=-mainMENU
	 * 
	 * 
	 * DNF may need numpad in
	 * 
	 * newrun may call endrun
	 * 
	 * event may require endrun
	 * 
	 * 
	 */

}