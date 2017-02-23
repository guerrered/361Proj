//For Commands CONN<SENSOR><NUMBER>
public class Sensors {
    boolean EYE=false;
    boolean GATE=false;
    boolean PAD=false;
	
	public void disconnect(){
		EYE=false;
		GATE=false;
		PAD=false;
		
	}
	
	
	public void EYE()
	{
		EYE=true;
	}
	
	public void GATE()
	{
		GATE=true;
	}
	
	public void PAD()
	{
	    PAD=true;	
	}
	
}


