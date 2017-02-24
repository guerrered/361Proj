
public class GATE extends Sensors{
public boolean Pushed;
int ChannelNum;	
Race race;

public GATE(int num,Race race)
{
	this.ChannelNum=num;
	this.race=race;
}

public void unarmed(){
	if(armed=true)
	{
		System.out.println("Sensor is armed");
	}
	armed=true;
}
	
public void armed()
{
	if(armed=true)
	{
		System.out.println("Sensor is armed");
	}
	armed=true;
}
	

	public void trig()
	{
		switch(ChannelNum){
		case(1):
			race.startIND();
			break;
		case(2):
			race.finishIND();
			break;
		}
		
	}
	
	

	
	
	
}
