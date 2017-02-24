

public class EYE extends Sensors{
boolean LightBeam;
	int ChannelNum;
	Race race;
public EYE(int num,Race race)
{
	this.ChannelNum=num;
	this.race=race;
}

public void unarmed(){
	if(armed==false)
	{
		System.out.println("Sensor is unarmed");
	}
	else{
		armed=false;
	    }
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
