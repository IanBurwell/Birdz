package birdz.lib.environment;

import java.awt.Graphics;
import java.awt.Point;

public abstract class EnvObject {
	Point position;
	
	public void translate(int x, int y) {
		position.translate(x, x);
	}
	
	public void setPosition(int x, int y) {
		position.setLocation(x, y);
	}
	
	public void setPosition(Point p) {
		position.setLocation(p);
	}
	
	public Point getPosition() {
		return position;
	}
	
	public abstract boolean isTouching(Point p);
	
	public abstract Point[] getHitbox();
	
	public abstract void paint(Graphics g, int x, int y);
	
	public static boolean areTouching(EnvObject a, EnvObject b) {
		for(Point p : a.getHitbox())
			if(b.isTouching(p)) return true;
		for(Point p : b.getHitbox())
			if(a.isTouching(p)) return true;
		return false;
	}
	
}
