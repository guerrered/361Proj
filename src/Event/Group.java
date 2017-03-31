package Event;

import java.util.ArrayList;
import java.util.List;

public class Group extends Event{
	
	List <Player> players = new ArrayList<>(9999);
	
	int queueStartNum = 0;
	int queueEndNum = 0;
	private long startTime; //start time is shared
	
	public Group(){

	}
	
	
}
