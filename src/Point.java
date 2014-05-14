


import java.awt.Graphics;

public class Point implements Cloneable{
	
	double x;
	double y;
	
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g)
	{
		g.drawLine((int)x, (int)y, (int)x, (int)y);
	}
	
	public void moveBy(double dx, double dy)
	{
		x += dx;
		y += dy;
		
	}
	
	public void moveTo ( Point p )
	{
		x = p.x;
		y = p.x;
	}
	
	public boolean isWithin ( Circle c )
	{
		return (x-c.center.x)* (x-c.center.x) + 
			   (y-c.center.y)* (y-c.center.y) 
			   < c.r * c.r ; 
	}
	
	public boolean isWithin ( Polygon2D p)
	{
		
		return this.isBetween(p.side[0], p.side[2]) && this.isBetween(p.side[1], p.side[3]); 
		
	}
	
	public boolean isBetween (Line l1, Line l2)
	{
		return l1.distanceTo(this) < 0  && l2.distanceTo(this) < 0;
	}
	public double distanceTo ( Point p )
	{
		return Math.sqrt( (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y) ); 
	}
	
	public Point clone()
	{
		
		return new Point(x, y );

	}
	


}
