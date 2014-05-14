
import java.awt.Color;
import java.awt.Graphics;

public class Line {
	
	Point p1;
	Point p2;
	
	double vx;
	double vy;
	
	Color color;
	
	private double length;
	public double yFriction;
	
	private final Vector2D floor = new Vector2D(1, 0);
	
	
	public Line(Point p1, Point p2)
	{
		this.p1 = p1;
		this.p2 = p2; 
		
		color = Color.white;
		
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		
		length = Math.sqrt(dx * dx + dy * dy);
		
		vx = dx / length;
		vy = dy / length;
		
		double a = getAngle();
		//yFriction = (a < 90 || a > 270)? Lookup.cos[(int)Math.toDegrees(a)] * 0.8 :  1 ;
		yFriction = 1;
		
	}
	

	public Line(Point p1, Point p2, Color color)
	{
		this.p1 = p1;
		this.p2 = p2; 
		
		this.color = color; 
		
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		
		length = Math.sqrt(dx * dx + dy * dy);
		
		vx = dx / length;
		vy = dy / length;
		
		
	}
	
	public double getLength()
	{
		return length ; 
	}
	
	/*
	 * Returns a double angle in radians, between the line and the x-axis;
	 */
	
	public double getAngle()
	{
		
		return getVector().angleBetween(floor);
		
	}
	
	public double distanceTo(Point p )
	{
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
	
		
		vx = dx / length;
		vy = dy / length;
		
		return (vy  * ( p.x - p1.x)) - (vx * ( p.y - p1.y));
		
	}
	
	public double distanceTo(Circle c)
	{
		return distanceTo(c.center) - c.r + 1;
	}
	
	public double segmentDistanceTo(Point p )
	{
		double leftMost; 
		double rightMost;
		
		double lowest;
		double highest;
		
		if (p1.x < p2.x)
		{
			leftMost = p1.x;
			rightMost = p2.x;
		}
		else
		{
			leftMost = p2.x;
			rightMost = p1.x;
		}
		
		if (p1.y < p2.y )
		{
			highest = p1.y;
			lowest = p2.y;
		}
		
		else
			
		{
			highest = p2.y;
			lowest = p1.y;
		}
		
		if ((p.y > lowest && p.y < highest) && (p.x > leftMost && p.x < rightMost) )
		{
			return distanceTo(p);
		}
		else
		{
			return p.distanceTo(p2);
		}
		
		
		
		
		
		
	}
	
	
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.drawLine( (int) p1.x , (int) p1.y, (int) p2.x, (int) p2.y);
		g.setColor(Color.BLACK);
		
	}
	
	public void setColor( Color color)
	{
		this.color = color;
	}
	
	public Vector2D getVector()
	{
		return new Vector2D( vx, vy );
	}
	
	public Vector2D getNVector()
	{
		
		Vector2D v = new Vector2D( vx, vy );
		Vector2D n = v.getNormalLeft();
		return n;
	}
	
	
	

}
