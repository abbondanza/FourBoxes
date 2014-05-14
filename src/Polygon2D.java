
import java.awt.Graphics;


public class Polygon2D 
{
	int n ; 
	Point[] points;
	boolean[] hasTouched;
	Point   center;
	
	protected Line[] side;
	
	double vx;
	double vy;
	
	double angle;
	
	boolean on_ground = false;
	
	public Polygon2D ( Point[] points, Point center )
	{
		
		n = points.length;
		hasTouched = new boolean[n];
		
		this.points = new Point[n];
		side = new Line[n];
		
		for (int i = 0 ; i < n; i ++)
		{
			points[i].moveBy(center.x, center.y);
			hasTouched[i] = false;
			
			this.points[i] = points[i].clone();
			
		}

		this.center = center.clone(); 
		
		vx = 0;
		vy = 0;
	}
	
	public void rotateLeftAround ( Point p , double angle)
	{
		
		

		 double cosA = Lookup.cos[(int)(360-angle)];
		 double sinA = Lookup.sin[(int)(360-angle)];
		
		for (int i = 0 ; i < points.length ; i++)
		{
			double newX   = p.x + (points[i].x - p.x) * cosA - (points[i].y - p.y) * sinA  ; 
			double newY   = p.y + (points[i].x - p.x) * sinA + (points[i].y - p.y) * cosA  ;
			
			points[i].x = newX;
			points[i].y = newY;
			
					
			
		}
		
		double newX   = p.x + (center.x - p.x) * cosA - (center.y - p.y) * sinA  ; 
		double newY   = p.y + (center.x - p.x) * sinA + (center.y - p.y) * cosA  ;
		
		center.x = newX;
		center.y = newY;
		
		
	}
	
	public void rotateRightAround ( Point p , double angle)
	{	
		
		 double cosA = Lookup.cos[(int)angle];
		 double sinA = Lookup.sin[(int)angle];
		 
		 System.out.println("Center x " + p.x);
		 System.out.println("Center y " + p.y);
	
		for (int i = 0 ; i < points.length ; i++)
		{
			
			double newX   = p.x + (points[i].x - p.x) * cosA - (points[i].y - p.y) * sinA  ; 
			double newY   = p.y + (points[i].x - p.x) * sinA + (points[i].y - p.y) * cosA  ;
			
			points[i].x = newX;
			points[i].y = newY;
					
	
		}
		
		double newX   = p.x + (center.x - p.x) * cosA - (center.y - p.y) * sinA  ; 
		double newY   = p.y + (center.x - p.x) * sinA + (center.y - p.y) * cosA  ;
		
		center.x = newX;
		center.y = newY;
		
		
		
	}
	
	public void rotateRightBy ( double angle )
	{
		if(!on_ground) rotateRightAround( center , angle);
	}
	
	public void rotateLeftBy ( double angle )
	{
		if(!on_ground) rotateLeftAround( center , angle);
	}
	
	public void applyGravity()
	{
		
		vy += Physics.GRAVITY;
		
		for (int i = 0 ; i < n ; i ++ )
		{
			points[i].y += vy;
			points[i].x += vx;
			
		}
		
		center.y += vy;
		center.x += vx;
		
	}
	

	
	public void draw( Graphics g )
	{
		int n = points.length; 
		int[] xPoints = new int[n]; 
		int[] yPoints = new int[n];
		
		//hasCollidedWith(GameEngine.FLOOR);
		//if(!on_ground) applyGravity();
		
		
		for (int i = 0 ; i < n ; i ++ )
		{
			xPoints[i] = (int)points[i].x;
			yPoints[i] = (int)points[i].y;
			
		}
		
		g.drawPolygon(xPoints, yPoints, n);
		
		
		
	}
	
	public void moveUpBy ( int dy )
	{

		for (int i = 0 ; i < n ; i ++ )
		{
			points[i].y -= dy;

		}
		
		center.y -= dy;
		
	}
	
	public void moveDownBy ( int dy )
	{

		for (int i = 0 ; i < n ; i ++ )
		{
			points[i].y += dy;

		}
		
		center.y += dy;
		
	}
	
	public void moveRightBy ( int dx )
	{

		for (int i = 0 ; i < n ; i ++ )
		{
			points[i].y += dx;

		}
		
		center.y += dx;
		
	}
	
	public void moveLeftBy ( int dx )
	{

		for (int i = 0 ; i < n ; i ++ )
		{
			points[i].y -= dx;

		}
		
		center.y -= dx;
		
	}
	
	public void moveTo(int x, int y)
	{
		double dx = x - center.x ;
		double dy = y - center.y ;
		
		center.x = x;
		center.y = y;
		
		for (int i = 0 ; i < n; i ++)
		{
			//points[i].moveTo(new Point ( 0, 0 ));
			points[i].moveBy(dx, dy);
		}
	}
	
	
	
	
	
	
	
	

}
