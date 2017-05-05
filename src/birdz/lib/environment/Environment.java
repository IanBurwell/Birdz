package birdz.lib.environment;

import java.awt.Graphics;
import java.util.ArrayList;

public class Environment {

	private static final long serialVersionUID = 8786961387046987953L;
	
	private ArrayList<EnvObject> objects;
	private int width, height;
	
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 500;
	
	public Environment(ArrayList<EnvObject> objects, int width, int height) {
		this.objects = objects;
		this.width = width;
		this.height = height;
	}
	
	public Environment(ArrayList<EnvObject> objects) {
		this(objects, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public void paint(Graphics g) {
		for(EnvObject o : objects)
			o.paint(g);
	}
	
	public Environment copy() {
		return new Environment(objects); //TODO
	}
	
}
