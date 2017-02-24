
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
		Channels.Channel ch=Channels.getCh(ChannelNum);
		ch.trig(race);
		
	}
	
	

	
	
	
}
