package Chronotimer;

public class ExportObject {
	String timestamp;
	String eventType;
	int ID;
	String totaltime;
	
	public ExportObject(String timestamp, String eventType, int ID, String totalTime){
		this.timestamp=timestamp;
		this.eventType=eventType;
		this.ID=ID;
		this.totaltime=totalTime;
	}

	public void printEO() {
		// TODO Auto-generated method stub
		System.out.println(timestamp+" "+eventType + "\n" + ID + " " +eventType + " " + totaltime );
		
	}
}
