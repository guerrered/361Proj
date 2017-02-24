//For Commands CONN<SENSOR><NUMBER>
public class Sensors {
  Boolean armed;
	
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
	
}
	



