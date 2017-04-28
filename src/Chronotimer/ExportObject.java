package Chronotimer;
/**
 * 
 * @author HiddenBit
 *
 */
public class ExportObject {
	private String timestamp;
	private String eventType;
	private String ID;
	private String totaltime;
	private String eventCode;
	private long timeRaw;

	/**
	 *  
	 * 
	 */
	public ExportObject(String timestamp, String eventType, String string,String eventCode, String totalTime, long tR){
		this.timestamp=timestamp;
		this.eventType=eventType;
		this.ID=string;
		this.totaltime=totalTime;
		this.eventCode=eventCode;
		timeRaw = tR;
		
		printEO();
	}


	/**
	 * 
	 * 
	 */
	public void printEO() {
		// TODO Auto-generated method stub
		System.out.println(timestamp+" "+eventType + "\n" + ID + " " +eventCode + " " + totaltime+"\n" );
		
	}
	
	public String getID(){
		return ID;
	}
	
	public String getCode(){
		return eventCode;
	}
	
	public long getTimeRaw(){
		return timeRaw;
	}
	
}
