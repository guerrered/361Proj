package Chronotimer;

public class menuStates {	
	int[][] menu;
	int stateIndex;
	int stringIndex;
	String currentState;
	String[] menuItems;
	
	public menuStates(){
		//main menu
		stateIndex= 0;
		String s1 = "1.Event<\n2.RaceOperations\n3.Run\n4.Print\n5.FileExport\n";
		String s2 = "1.Event\n2.RaceOperations<\n3.Run\n4.Print\n5.FileExport\n";
		String s3 = "1.Event\n2.RaceOperations\n3.Run<\n4.Print\n5.FileExport\n";
		String s4 = "1.Event\n2.RaceOperations\n3.Run\n4.Print<\n5.FileExport\n";
		String s5 = "1.Event\n2.RaceOperations\n3.Run\n4.Print\n5.FileExport<\n";
		//event submenu
		String s6 = "1.IND<\n2.PARIND\n3.GRP";
		String s7 = "1.IND\n2.PARIND<\n3.GRP";
		String s8 = "1.IND\n2.PARIND\n3.GRP<";
		//raceop submenu
		String s9 = "1.DNF<\n2.CANCEL";
		String s10 = "1.DNF\n2.CANCEL<";
		//run SubMenu
		String s11 = "1.NEWRUN<\n2.ENDRUN";
		String s12 = "1.NEWRUN\n2.ENDRUN<";
		String s13 = "function";
		
		menuItems = new String[22];
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
		menuItems[13] = s13;
		menuItems[14] = s13;
		menuItems[15] = s13;
		menuItems[16] = s13;
		menuItems[17] = s13;
		menuItems[18] = s13;
		menuItems[19] = s13;
		menuItems[20] = s13;
		menuItems[21] = s13;
				
		currentState = s1;
		
		// 0-up  1-down 2-right 3-left
		menu = new int[4][12];//4*12 grid of menu states
		menu[0][0] = 4;//the answer is the vertical index of state
		menu[0][1] = 0;
		menu[0][2] = 1;
		menu[0][3] = 2;
		menu[0][4] = 3;
		
		menu[0][5] = 7;
		menu[0][6] = 5;
		menu[0][7] = 6;
		
		menu[0][8] = 9;
		menu[0][9] = 8;
		
		menu[0][10] =11;
		menu[0][11] = 10;
		
		
		//--going down
		menu[1][0] = 1;
		menu[1][1] = 2;
		menu[1][2] = 3;
		menu[1][3] = 4;
		menu[1][4] = 0;
		
		menu[1][5] = 6;
		menu[1][6] = 7;
		menu[1][7] = 5;
		
		menu[1][8] = 9;
		menu[1][9] = 8;
		
		menu[1][10] = 11;
		menu[1][11] = 10;
		
		//--moving right
		menu[2][0] = 5;
		menu[2][1] = 8;
		menu[2][2] = 10;
		menu[2][3] = 12;//print
		menu[2][4] = 13;//export
		
		menu[2][5] = 14;//EVENT(IND)
		menu[2][6] = 15;//EVENT(PARIND)
		menu[2][7] =  16;//EVENT(GRP)
		
		menu[2][8] =17;//DNF
		menu[2][9] = 18;//CANCEL
		
		menu[2][10] =19;//NEWRUN
		menu[2][11] = 20;//ENDRUN
		
		//--moving left
		menu[3][0] = 21;//exit menu
		menu[3][1] = 21;//exit menu
		menu[3][2] = 21;//exit menu
		menu[3][3] = 21;//exit menu
		menu[3][4] = 21;//exit menu
		
		menu[3][5] = 0;//main menu
		menu[3][6] = 0;//main menu
		menu[3][7] = 0;//main menu
		
		menu[3][8] = 0;//main menu
		menu[3][9] = 0;//main menu
		
		menu[3][10] = 0;//main menu
		menu[3][11] = 0;//main menu
		
	}
	
	public void next(){//2
		int nextState = menu[2][stateIndex];
		String nextStateVal = menuItems[nextState];
		if(nextState <= 11){
			stateIndex = nextState;//if < 11 then no function was executed 
		}
		currentState = nextStateVal;
	}
	
	public void prev(){//3
		int nextState = menu[3][stateIndex];
		String nextStateVal = menuItems[nextState];
		if(nextState <= 11){
			stateIndex = nextState;//if < 11 then no function was executed 
		}
		currentState = nextStateVal;
	}
	
	public void up(){//0
		int nextState = menu[0][stateIndex];
		String nextStateVal = menuItems[nextState];
		if(nextState <= 11){
			stateIndex = nextState;//if < 11 then no function was executed 
		}
		currentState = nextStateVal;
	}
	
	public void down(){//1
		int nextState = menu[1][stateIndex];
		String nextStateVal = menuItems[nextState];
		if(nextState <= 11){
			stateIndex = nextState;//if < 11 then no function was executed 
		}
		currentState = nextStateVal;
	}
	
	public String getCurrentState(){
		return currentState;
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
