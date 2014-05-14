
import java.awt.Color;
import java.awt.Graphics;


public class Rectangle2D extends Polygon2D
{

	
	
	public Rectangle2D(Point[] points, Point center) {
		super(points, center);
		
		

		for (int i = 0 ; i < n ; i++)
		{
			Point p1 = points[i];
			Point p2 = (i+1 == n)? points[0] : points[i+1];
			
			side[i] = new Line(p1 , p2);
			
		}
		
		
		
	}
	
	public void draw( Graphics g )
	{
		int n = points.length; 
		int[] xPoints = new int[n]; 
		int[] yPoints = new int[n];
		
		//Must Write Gravity and collision functions
		
		for (int i = 0 ; i < n ; i ++ )
		{
			Point p1 = points[i];
			Point p2 = (i+1 == n)? points[0] : points[i+1];
			
			side[i].p1 = p1;
			side[i].p2 = p2;
			
			xPoints[i] = (int)points[i].x;
			yPoints[i] = (int)points[i].y;
			
			
			
		}
		
		g.setColor(Color.WHITE);
		g.fillPolygon(xPoints, yPoints, n);
		g.setColor(Color.BLACK);
		
		/*for(int i = 0; i < n ; i++)
		{
			int x1 = (int) side[i].p1.x;
			int y1 = (int) side[i].p1.y;
			
			int x2 = (int) side[i].p2.x;
			int y2 = (int) side[i].p2.y;
			
			
			g.drawLine(x1, y1, x2, y2);
			
			
		}*/
		
		
		
	}
	
	
	
	
	

}
