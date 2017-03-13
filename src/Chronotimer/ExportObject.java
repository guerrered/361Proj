package Chronotimer;

public class ExportObject {
	String timestamp;
	String eventType;
	String ID;
	String totaltime;
	String eventCode;
	
	public ExportObject(String timestamp, String eventType, String string,String eventCode, String totalTime){
		this.timestamp=timestamp;
		this.eventType=eventType;
		this.ID=string;
		this.totaltime=totalTime;
		this.eventCode=eventCode;
		
	}

	public void printEO() {
		// TODO Auto-generated method stub
		System.out.println(timestamp+" "+eventType + "\n" + ID + " " +eventCode + " " + totaltime+"\n" );
		
	}
}
