

public class Lookup 
{
	
	final static double[] cos = new double[360];
	final static double[] sin = new double[360];
	
	public static void generate()
	{
		for(int degrees = 0 ; degrees < 360 ; degrees ++)
		{
			cos[degrees] = Math.cos(degrees * Math.PI / 180);
			sin[degrees] = Math.sin(degrees * Math.PI / 180);
		}
	}
	

}
