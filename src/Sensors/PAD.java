package Sensors;


public class PAD extends Sensors {
boolean pressure;
int ChannelNum;


public PAD(int num)
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