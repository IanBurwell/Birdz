package birdz.lib.environment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Bird extends EnvObject {

	private static final int DEFAULT_SIZE = 15;
	private static final Color DEFAULT_COLOR = Color.BLUE;
	private static final int HITBOX_POINTS = 4;
	private static final boolean DEBUG = true;

	private int fov = 45; //TODO make constructor also
	private int sightDist = 100;
	
	private int size;
	private Color color;
	
	private double degRotation;
	private double speed = 0;
	//TODO make rotational velocity
	
	public Bird() {
		this(0, 0, 0, DEFAULT_SIZE, DEFAULT_COLOR);
	}

	public Bird(int x, int y) {
		this(x, y, 0, DEFAULT_SIZE, DEFAULT_COLOR);
	}

	public Bird(int x, int y, double rot, int size, Color c) {
		this.setPosition(x, y);
		this.degRotation = rot;
		this.size = size;
		color = c;
	}

	@Override
	public void update(){
		moveForward(speed);
	}
	
	public double getSpeed(){
		return speed;
	}
	
	public double[] getInputs(ArrayList<EnvObject> objects) {
		double[] sight = getSight(3, objects);
		double[] inputs = new double[sight.length + 1];
		
		inputs[0] = speed;
		for(int i = 1; i < inputs.length; i++){
			inputs[i] = sight[i-1];
		}
		
		return inputs; //TODO add more inputs and maybe change quality of sight
	}

	@Override
	public boolean isTouching(Point p) {
		return ((p.x - getRoundedPosition().x)*(p.x - getRoundedPosition().x) + (p.y - getRoundedPosition().y)*(p.y - getRoundedPosition().y) < size*size); //TODO include beak
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
			//Draws FOV
			Point base = this.getRoundedPosition();
			Point left = new Point(base.x+(int)(sightDist*Math.cos(Math.toRadians(degRotation-((double)fov/2)))),
					base.y+(int)(sightDist*Math.sin(Math.toRadians(degRotation-((double)fov/2)))));

			Point right = new Point(base.x+(int)(sightDist*Math.cos(Math.toRadians(degRotation+((double)fov/2)))),
					base.y+(int)(sightDist*Math.sin(Math.toRadians(degRotation+((double)fov/2)))));

			int numSections = 3;

			for(int i = 0; i < numSections+1; i++){
				Point cLeft = new Point((int)((1-((double)i/numSections))*left.x + ((double)i/numSections)*right.x),
						(int)((1-((double)i/numSections))*left.y + ((double)i/numSections)*right.y));
				g.fillOval(cLeft.x, cLeft.y, 2, 2);
			}

			g.setColor(new Color(50, 50, 50, 125));
			g.fillPolygon(new int[] {base.x, left.x, right.x}, 	  	  
					new int[] {base.y, left.y, right.y}, 
					3);
		}
	}

	public void moveForward(double dist){
		translate(Math.cos(Math.toRadians(degRotation))*dist*(size), Math.sin(Math.toRadians(degRotation))*dist*(size));
	}

	public void rotate(double degrees){
		degRotation += degrees;
		degRotation %= 360;
	}

	public double getRotation() {
		return degRotation;
	}

	public double[] getSight(int numSections, ArrayList<EnvObject> objects){
		double[] sight = new double[numSections];
		final Point base = this.getRoundedPosition();
		final Point bLeft = new Point(base.x+(int)(sightDist*Math.cos(Math.toRadians(degRotation-((double)fov/2)))),
				base.y+(int)(sightDist*Math.sin(Math.toRadians(degRotation-((double)fov/2)))));
		final Point bRight = new Point(base.x+(int)(sightDist*Math.cos(Math.toRadians(degRotation+((double)fov/2)))),
				base.y+(int)(sightDist*Math.sin(Math.toRadians(degRotation+((double)fov/2)))));


		for(int i = 0; i < numSections; i++){
			Point cLeft = new Point((int)((1-((double)i/numSections))*bLeft.x + ((double)i/numSections)*bRight.x),
					(int)((1-((double)i/numSections))*bLeft.y + ((double)i/numSections)*bRight.y));
			Point cRight = new Point((int)((1-((double)(i+1)/numSections))*bLeft.x + ((double)(i+1)/numSections)*bRight.x),
					(int)((1-((double)(i+1)/numSections))*bLeft.y + ((double)(i+1)/numSections)*bRight.y));//TODO test

			double minDist = sightDist;
			for(EnvObject o : objects){
				if(o == this) continue;
				for(Point p : o.getHitbox()){
					Point absP = new Point(o.getRoundedPosition().x+p.x, o.getRoundedPosition().y+p.y);
					if(pointInTriangle(absP,base,cLeft,cRight) && distanceBetween(absP,base) < minDist)//TODO was returning 10, 0 for hitbox point
						minDist = distanceBetween(absP,base);
				}
			}
			sight[i] = (minDist == sightDist) ? -1 : minDist;
		}		

		return sight;

	}

	private double distanceBetween(Point a, Point b) {
		return Math.sqrt(((a.x-b.x)*(a.x-b.x))+((a.y-b.y)*(a.y-b.y)));
	}

	private boolean pointInTriangle(Point point, Point a, Point b, Point c){
		int x = point.x, y = point.y;
		int x1 = a.x, y1 = a.y;
		int x2 = b.x, y2 = b.y;
		int x3 = c.x, y3 = c.y;

		double ABC = Math.abs (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
		double ABP = Math.abs (x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2));
		double APC = Math.abs (x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y));
		double PBC = Math.abs (x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2));

		return ABP + APC + PBC == ABC;
	}

	@Override
	public EnvObject copy() {
		return new Bird(getRoundedPosition().x, getRoundedPosition().y, degRotation, size, color);
	}
}
