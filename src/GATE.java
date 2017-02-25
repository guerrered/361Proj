
public class GATE extends Sensors{
public boolean Pushed;
int ChannelNum;	
RaceIndependent race;

public GATE(int num,RaceIndependent race)
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
