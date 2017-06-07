package birdz.lib.environment;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Goal extends EnvObject {
	
	private int size = 50;

	public Goal(int x, int y, int size) {
		this.setPosition(x,y);
		this.size = size;
	}
	
	@Override
	public boolean isTouching(Point p) {
		if(p.x >= this.getRoundedPosition().x && p.x < this.getRoundedPosition().x + size &&
				p.y >= this.getRoundedPosition().y && p.y < this.getRoundedPosition().y + size) return true;
		return false;
	}

	@Override
	public Point[] getHitbox() {
		// TODO Auto-generated method stub
		return new Point[]{new Point(this.getRoundedPosition().x + (size/2), this.getRoundedPosition().y + (size/2))};
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		g.setColor(Color.CYAN);
		g.fillRect(this.getRoundedPosition().x, this.getRoundedPosition().y, size, size);

	}

	@Override
	public EnvObject copy() {
		return new Goal(this.getRoundedPosition().x, this.getRoundedPosition().y, size);
	}

}
