import java.awt.Color;
import java.awt.Graphics;


public class Circle {
	
	Point center;
	
	double r;
	
	Line x_line;
	Line y_line;
	
	Vector2D v; 

	boolean gravity_on;
	
	Color color; 
	
	final double FLOOR = DestroyTheBox.FLOOR.p1.y;
	final double RIGHT = DestroyTheBox.RIGHT_BOUNDARY.p1.x;
	final double LEFT = DestroyTheBox.LEFT_BOUNDARY.p1.x;
	
	
	public Circle(Point center , double r, Color color)
	{
		this.center = center;
		this.r = r;
		this.color = color;
		
		gravity_on = false; 
		
		v = new Vector2D(0 , 0 );
		
		x_line = new Line(center, new Point( center.x + v.x, center.y));
		y_line = new Line(center, new Point( center.x, center.y + v.y));
	
		
	}
	
	public Circle(Point center , double r)
	{
		this.center = center;
		this.r = r;
		this.color = Color.RED;
		
		gravity_on = false; 
		
		v = new Vector2D(0 , 0 );
		
		x_line = new Line(center, new Point( center.x + v.x, center.y));
		y_line = new Line(center, new Point( center.x, center.y + v.y));
	
		
	}
	
	public boolean hasCollidedWith ( Circle other )
	{
		return (other.center.x - center.x) * (other.center.x - center.x)
				+ ( other.center.y - center.y) * ( other.center.y - center.y) 
				< ( other.r + r) * (other.r + r);
	}
	
	public void bounceOff( Circle other )
	{
		double temp_vx = v.x;
		v.x =  (other.v.x - v.x) ;
		other.v.x = (temp_vx - other.v.x) ;
	}
	

	
	
	public void shoot ( Vector2D dv )
	{
		/*
		vx += Lookup.cos[force.angle] * force.magnitude;
		System.out.println("vx = " + vx);
		vy -= Lookup.sin[force.angle] * force.magnitude;
		System.out.println("vy = " + vy);
		//*/
		
		v.x += dv.x; 
		System.out.println("vx = " + v.x);
		v.y -= dv.y;
		System.out.println("vy = " + v.y);


	}
	
	public void setVector(Vector2D v)
	{
		this.v = v;
	}
	
	public void bounceOff( Line l )
	{
		// -( 2 (n.v) n - v)
		double dot = l.getNVector().dot(v);
		double scalar = dot * ( -2 );
		
		Vector2D nv = l.getNVector();
		nv.multiplyByScalar(scalar);
		nv.add(v);
		
		nv.y *= l.yFriction;
		
		setVector(nv);
	}
	
	//returns true if collision occured
	public void handleCollisionWith( Polygon2D p)
	{
		
		if(p.side[0].distanceTo(center) > 0)
		{
			if( p.side[3].distanceTo(center) <= 0 && p.side[1].distanceTo(center) < 0)
			{
				if( p.side[0].distanceTo(this) <= 0 )
				{
					bounceOff(p.side[0]);
					
				}
			}
				
		}
		if(p.side[1].distanceTo(center) > 0)
		{
			if( p.side[0].distanceTo(center) <= 0 && p.side[2].distanceTo(center) < 0)
			{
				if( p.side[1].distanceTo(this) <= 0 )
				{
					bounceOff(p.side[1]);
				
				}
			}
				
		}
		if(p.side[2].distanceTo(center) > 0)
		{
			if( p.side[1].distanceTo(center) <= 0 && p.side[3].distanceTo(center) < 0)
			{
				if( p.side[2].distanceTo(this) <= 0 )
				{
					bounceOff(p.side[2]);
					
				}
			}
				
		}
		if(p.side[3].distanceTo(center) > 0)
		{
			if( p.side[2].distanceTo(center) <= 0 && p.side[0].distanceTo(center) < 0)
			{
				if( p.side[3].distanceTo(this) <= 0 )
				{
					bounceOff(p.side[3]);

				}
			}
				
		}
		
	
		
		
		
	}
	
	public boolean isBetween(Line l1, Line l2)
	{
		return l1.distanceTo(this) > 0 && l2.distanceTo(this) > 0;
	}
	
	
	public boolean hasCollidedWith(Line l )
	{
		
		double d = l.distanceTo(center);
		return d < r;
	}
	
	
	
	
	
	
	public void draw( Graphics g )
	{
		center.x+= v.x;
		center.y+= v.y;
		
		if(gravity_on)
		{
			
			
			v.y += Physics.GRAVITY ;
		
			
			if(  FLOOR - center.y < r )
			{
				center.y = FLOOR - r;
				v.y  =  -v.y * .8  ;
				v.x = v.x * .9;
				if ( Math.abs(v.y) < 8)
				{
					v.y = 0;
				}
			}
			
			if ( center.x + r > RIGHT || center.x - r < LEFT)
				v.x = -v.x;
			
			
			
		}
		
		x_line.p2.x = center.x + v.x*10;
		x_line.p2.y = center.y ;
		
		y_line.p2.y = center.y + v.y*10;
		y_line.p2.x = center.x ;
		
		//y_line.draw(g);
		//x_line.draw(g);
		
		g.setColor(color);
		g.fillOval((int)(center.x - r ) , (int)(center.y - r),(int) (r+r), (int)(r+r));
		g.setColor(Color.BLACK);
	}
	

}
