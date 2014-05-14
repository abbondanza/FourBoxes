

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JApplet;





public class DestroyTheBox extends Applet implements MouseListener, Runnable, MouseMotionListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Line FLOOR = new Line( new Point(0, 700), new Point(1200, 700) );
	public static final Line ROOF = new Line( new Point(1200, 0) ,  new Point(0, 0));
	
	public static final Line RIGHT_BOUNDARY = new Line(new Point(1200, 700), new Point(1200, 0)  );
	public static final Line LEFT_BOUNDARY = new Line(new Point(0, 0), new Point(0, 700));
	public static final Font font = new Font("Sanserif", Font.BOLD, 30);
	
	boolean[] isPressed = new boolean[256];
	boolean[] touchedBox = new boolean[4];

	Circle c;
	
	Circle goal;
	
	Line line ; 
	
	Random rand ; 
	
	Rectangle2D[] box;   // box1, box2, box3, box4, controlled;
	
	Rectangle2D controlled;
	
	boolean controlledMoveable ;
	
	int points;

	public void init()
	{
		
		rand = new Random();
		
		setSize(1200,750);
		setLayout(null);
		
		Lookup.generate();
		
		for(int i = 0; i < isPressed.length ; i++)
		{
			isPressed[i] = false;
		}
		
		
		Point[] box1_p = { 
				new Point( 40 , -40 ),
				new Point (40, 40),		
				new Point( -40, 40),
				new Point(- 40, - 40 )	
		};
		
		Point[] box2_p = { 
				new Point( 40 , -40 ),
				new Point (40, 40),		
				new Point( -40, 40),
				new Point(- 40, - 40 )	
		};
		Point[] box3_p = { 
				new Point( 40 , -40 ),
				new Point (40, 40),		
				new Point( -40, 40),
				new Point(- 40, - 40 )	
		};
		Point[] box4_p = { 
				new Point( 40 , -40 ),
				new Point (40, 40),		
				new Point( -40, 40),
				new Point(- 40, - 40 )	
		};
		
		
		c = new Circle( new Point( 600,10) ,10);
		
		int rx = rand.nextInt(1200);
		int ry = rand.nextInt(700);
		int rr = rand.nextInt(20 - 5) + 5;
		
		goal = new Circle( new Point(rx, ry) , rr, Color.YELLOW);

		
		box = new Rectangle2D[4];
		
		box[0] = new Rectangle2D( box1_p, new Point(420, 300));
		box[1] = new Rectangle2D( box2_p, new Point(120, 300));
		box[2] = new Rectangle2D( box3_p.clone(), new Point(220, 300));
		box[3] = new Rectangle2D( box4_p.clone(), new Point(320, 300));
		
		controlled = box[0];
		

		
		requestFocus();
		
		controlledMoveable = false;
		points = 0;
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
	
		Thread t = new Thread(this);
	
	
		t.start();
		
	}
	
	public void run()
	{
		//Our code can run in this thread
		
		
		while(true)
		{
			
			for (int i = 0 ; i < 4 ; i++)
			{
				c.handleCollisionWith(box[i]) ; 
			
			}
				
			if (c.hasCollidedWith(FLOOR))
				c.bounceOff(FLOOR);
			
		
			if ( c.hasCollidedWith(goal) )
			{
				points += 100.0/goal.r;
				int rx = rand.nextInt(1200);
				int ry = rand.nextInt(700);
				int rr = rand.nextInt(20 - 5) + 5;
				goal = new Circle(new Point(rx, ry), rr, Color.YELLOW );
				reset();
				
			}
				
			
			if(isPressed[KeyEvent.VK_RIGHT])
				controlled.rotateRightBy(2);
			
			if(isPressed[KeyEvent.VK_LEFT])
				controlled.rotateLeftBy(2);
			
			if(isPressed[KeyEvent.VK_R])
			{
				reset();
			}
			
			
			points += Math.abs(c.v.y + c.v.x);			
			repaint();
			
			
			try{
				
				Thread.sleep(16);
			}
			catch(Exception ex){}
		
		}
	
		
	}
	


	public void keyTyped(KeyEvent e) 
	{

		
	}


	public void keyPressed(KeyEvent e) 
	{
	
		isPressed[e.getKeyCode()] = true;
	
	}

	public void keyReleased(KeyEvent e) 
	{
		
		isPressed[e.getKeyCode()] = false;
		
		
	}
	
	public void mouseClicked(MouseEvent e)
	{
		Point p = new Point ( e.getX(), e.getY() );
		
		for(int i = 0 ; i < 4 ; i ++ )
		{
			if ( p.isWithin(box[i]))
			{
				controlled = box[i];
			}
		}

		if(p.isWithin(c))
			c.gravity_on = true;
		
	
	}
	
	public void mouseReleased(MouseEvent e){
	
		//System.out.println("Released at : " + px + ",  " + py );
		controlledMoveable = false;
	}
	
	public void mousePressed(MouseEvent e){
		Point p = new Point(e.getX(), e.getY());
		for(int i = 0 ; i < 4 ; i ++ )
		{
			if ( p.isWithin(box[i]))
			{
				controlled = box[i];
			}
		}
		if(p.isWithin(controlled)) controlledMoveable = true;
	}
	
	public void mouseEntered(MouseEvent e) {
		System.out.println("Entered");
	}

	public void mouseExited(MouseEvent e) {
		System.out.println("Exited");
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(controlledMoveable)
			controlled.moveTo(e.getX(), e.getY());
		
			
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("Mouse Moved");		
		
	}
	
	public void reset()
	{
		c = new Circle(new Point(600, 10), 10);
	}
	


	
	
	public void paint(Graphics g)
	{
		
		//g.setColor(Color.BLACK);
		this.setBackground(Color.BLACK);
		
		g.setColor(Color.WHITE);
		ROOF.draw(g);
		FLOOR.draw(g);
		
		c.draw(g);
		goal.draw(g);
		
		for(int i = 0 ; i < 4 ; i ++ )
		{
			box[i].draw(g);
		}
	
		
		g.setFont(font);
		g.setColor(Color.yellow);
		g.drawString("Points: " + points, 10, 40);
		

	
	}

	

}