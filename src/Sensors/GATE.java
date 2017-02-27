package Sensors;


public class GATE extends Sensors{
public boolean Pushed;
int ChannelNum;	


public GATE(int num)
{
	this.ChannelNum=num;
	
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
		//Channels.Channel ch=Channels.getCh(ChannelNum);
		//ch.trig(race);
		
	}
	
	

	
	
	
}
