package birdz.lib.environment;

import java.awt.Graphics;
import java.awt.Point;

public abstract class EnvObject {
	double posX;
	double posY;
	
	public void translate(int x, int y) {

		posX += x;
		posY += y;
	}
	
	public void translate(double x, double y) {
		posX += x;
		posY += y;
	}
	
	public void setPosition(int x, int y) {
		posX = x; 
		posY = y;
	}
	
	public void setPosition(double x, double y) {
		posX = x; 
		posY = y;
	}
	
	public Point getRoundedPosition() {
		return new Point((int)posX, (int)posY);
	}
	
	public double getX(){
		return posX;
	}
	
	public double getY(){
		return posY;
	}
	
	public abstract boolean isTouching(Point p);
	
	public abstract Point[] getHitbox();
	
	public abstract void paint(Graphics g, int x, int y);
	
	public void update(){}
	
	public abstract EnvObject copy();
	
	public static boolean areTouching(EnvObject a, EnvObject b) {
		for(Point p : a.getHitbox())
			if(b.isTouching(p)) return true;
		for(Point p : b.getHitbox())
			if(a.isTouching(p)) return true;
		return false;
	}
	
}
