package birdz.lib.environment;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

public class Environment extends JComponent {

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
	
	
	
	
	
	public Environment copy() {
		return new Environment(objects); //TODO
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		for(int i = 0; i < objects.size(); i++)
			objects.get(i).paint(g);
	}
	
}
