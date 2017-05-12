package birdz.lib.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Bird extends EnvObject {

	private static final int DEFAULT_SIZE = 15;
	private static final Color DEFAULT_COLOR = Color.BLUE;
	private static final int HITBOX_POINTS = 4;
	private static final boolean DEBUG = true;

	private int fov = 10;//TODO make constructor also
	private int sightDist = 100;
	private int degRotation;
	private int size;
	private Color color;

	public Bird() {
		this(0, 0, 0, DEFAULT_SIZE, DEFAULT_COLOR);
	}

	public Bird(int x, int y) {
		this(x, y, 0, DEFAULT_SIZE, DEFAULT_COLOR);
	}
	
	public Bird(int x, int y, int rot, int size, Color c) {
		this.setPosition(x, y);
		this.degRotation = rot;
		this.size = size;
		color = c;
	}
	
	public double[] getInputs() {
		return null; //FIXME
	}

	@Override
	public boolean isTouching(Point p) {
		return ((p.x - getRoundedPosition().x)*(p.x - getRoundedPosition().x) + (p.y - getRoundedPosition().y)*(p.y - getRoundedPosition().y) < size); //TODO include beak
	}

	@Override
	public Point[] getHitbox() {
		Point[] hitbox = new Point[HITBOX_POINTS];
		for(int i = 0; i < HITBOX_POINTS; i++)
			hitbox[i] = new Point((int) (size * Math.cos(((double) i / HITBOX_POINTS) * Math.PI * 2)), (int) (size * Math.sin(((double)(i) / HITBOX_POINTS) * Math.PI * 2)));
		return hitbox;
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		g.setColor(color);
		g.fillOval(x-size, y-size, size*2, size*2);

		g.fillPolygon(new int[] {x+(int)(size*Math.cos(Math.toRadians(degRotation-45))),
							x+(int)(size*Math.cos((Math.PI/2)+Math.toRadians(degRotation-45))),
							x+(int)(2*size*Math.cos((Math.PI/4)+Math.toRadians(degRotation-45)))}, 	  	  
					  new int[] {y+(int)(size*Math.sin(Math.toRadians(degRotation-45))),
						  	y+(int)(size*Math.sin((Math.PI/2)+Math.toRadians(degRotation-45))),
							y+(int)(2*size*Math.sin((Math.PI/4)+Math.toRadians(degRotation-45)))}, 
				3);
		
		if(DEBUG){ 
			g.setColor(Color.LIGHT_GRAY);
			
			Point base = this.getRoundedPosition();
			
			Point left = new Point(base.x+(int)(sightDist*Math.cos(Math.toRadians(degRotation-((double)fov/2)))),
					base.y+(int)(sightDist*Math.sin(Math.toRadians(degRotation-((double)fov/2)))));
			
			Point right = new Point(base.x+(int)(sightDist*Math.cos(Math.toRadians(degRotation+((double)fov/2)))),
					base.y+(int)(sightDist*Math.sin(Math.toRadians(degRotation+((double)fov/2)))));
			
			g.fillPolygon(new int[] {base.x, left.x, right.x}, 	  	  
		  new int[] {base.y, left.y, right.y}, 
		  3);
		}
	}

	public void moveForward(int dist){
		translate(Math.cos(Math.toRadians(degRotation))*dist*(size), Math.sin(Math.toRadians(degRotation))*dist*(size));
	}
	
	public void rotate(int degrees){
		degRotation += degrees;
		degRotation %= 360;
	}

	public double[] getSight(int numSections, ArrayList<EnvObject> objects){
		double[] sight = new double[numSections];
		Point base = this.getRoundedPosition();
		
		Point left = new Point(base.x+(int)(Math.cos(Math.toRadians(degRotation-fov))),
				base.y+(int)(sightDist*Math.sin(Math.toRadians(degRotation-fov))));

		Point right = new Point(base.x+(int)(size*Math.cos((Math.PI/2)+Math.toRadians(degRotation-fov))),
				base.y+(int)(sightDist*Math.sin((Math.PI/2)+Math.toRadians(degRotation-fov))));
		
		
		
		return sight;
	}
}
