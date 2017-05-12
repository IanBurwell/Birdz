package birdz.lib.environment;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComponent;

import birdz.lib.genetic.Individual;

public class Environment extends JComponent{

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

	@Override
	public void paintComponent(Graphics g) {
		synchronized(objects){
			for(EnvObject o : objects)
				o.paint(g, o.getRoundedPosition().x, o.getRoundedPosition().y);
		}
	}

	public Environment copy() {
		return new Environment(objects); //TODO
	}
	
	int getNum(Class<? extends EnvObject> c) {
		int num = 0;
		for(EnvObject o : objects)
			if(c.isInstance(o))
				num++;
		return num;
	}

	public double getFitness(Individual i) {
		Bird b = new Bird();
		int j;
		Point p = b.getRoundedPosition();
		p.translate(1,0);	//Sketchyyyyy
		for(j = 0; j < 100 && !b.getRoundedPosition().equals(p); j++) {
			double[] outputs = i.fire(new double[]{1,1});
			p = b.getRoundedPosition();
			b.moveForward((int)outputs[0]);
			b.rotate((int)outputs[1]);
		}
		return j;
	}

	
	
}
