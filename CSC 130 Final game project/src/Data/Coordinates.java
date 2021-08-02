package Data;

public class Coordinates {
	
	public Integer x;
	public Integer y;
	
	public Coordinates(Integer a, Integer b)
	{
		x = a;
		y = b;
	}
	
	public Coordinates(Coordinates c)
	{
		x = c.x;
		y = c.y;
	}

}
