package birdz.lib.environment;

import java.awt.Graphics;
import java.awt.Point;

public class Rock extends EnvObject {

	private static final int HITBOX_POINTS = 4;
	private final int radius = 10;
	
	public Rock(int x, int y) {
		this.setPosition(x, y);
	}
	
	@Override
	public boolean isTouching(Point p) {
		return ((p.x - getRoundedPosition().x)*(p.x - getRoundedPosition().x) + (p.y - getRoundedPosition().y)*(p.y - getRoundedPosition().y) < radius*radius);
	}

	@Override
	public Point[] getHitbox() {
		Point[] hitbox = new Point[HITBOX_POINTS];
		for(int i = 0; i < HITBOX_POINTS; i++)
			hitbox[i] = new Point((int) (radius * Math.cos(((double) i / HITBOX_POINTS) * Math.PI * 2)), (int) (radius * Math.sin(((double)(i) / HITBOX_POINTS) * Math.PI * 2)));
		return hitbox;
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		g.fillOval(x-radius, y-radius, radius*2, radius*2);
	}

}
