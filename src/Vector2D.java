
import java.awt.Graphics;


public class Vector2D 
{
	
	double x;
	double y; 
	
	
	
	
	public Vector2D()
	{
		x = 0 ; 
		y = 0 ; 
	}
	
	public Vector2D ( double x , double y )

	{
		this.x = x; 
		this.y = y; 
	}
	public void add( Vector2D v)
	{
		
		x += v.x;
		y += v.y;
		
 
		
		
	}
	
	public double magnitude()
	{
		return Math.sqrt( x * x + y * y );
	}
	
	public void normalize()
	{
		
		double magnitude = magnitude();
		multiplyByScalar ( 1 / magnitude );
		
	}
	
	public double angleBetween ( Vector2D other )
	{
		
		double cosA = dot(other) / (magnitude() * other.magnitude());
		
		
		return Math.acos(cosA) ;
	}
	
	public Vector2D getNormalLeft ()
	{
		Vector2D nv = new Vector2D(y, -x);
		
		nv.normalize();
		
		return nv;
	}
	
	public Vector2D getNormalRight ()
	{
		Vector2D nv = new Vector2D(-y, x);
		
		nv.normalize();
		
		return nv;
	}
	
	public double dot ( Vector2D v )
	{
		return x * v.x + (y * v.y) ; 
	}
	
	public void  multiplyByScalar ( double scalar )
	{
		x = x *  scalar;
		y = y *  scalar;
	}
	
	
	
	public void draw(Graphics g , Point sPoint)
	{
		Point p = sPoint.clone();
		Line v = new Line( p, new Point ( p.x + x * 20, p.y - y * 20 ));
		
		v.draw(g);
	}

}
