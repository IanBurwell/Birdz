package birdz.lib.environment;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;

import birdz.lib.genetic.Individual;

public class Environment extends JComponent{

	private static final long serialVersionUID = 8786961387046987953L;

	private ArrayList<EnvObject> objects;
	private int width, height;
	private HashMap<Bird, Individual> iMap = null;

	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 500;

	public Environment(ArrayList<EnvObject> objects, int width, int height) {
		this.objects = objects;
		this.width = width;
		this.height = height;
	}

	public Environment(HashMap<Bird, Individual> hm, ArrayList<EnvObject> objects) {
		this(objects, DEFAULT_WIDTH, DEFAULT_HEIGHT);
		iMap = hm;
	}

	public Environment(ArrayList<EnvObject> objects) {
		this(objects, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	
	public void updateIndividuals(){
		if(iMap == null)return;

		for(EnvObject o : objects)
			if(o instanceof Bird && iMap.containsKey(o)){
				Bird b = (Bird)o;
				double[] outputs = iMap.get(o).fire(b.getInputs(objects));
				b.accelerate(outputs[0]);
				b.rotate(outputs[1]);
			}
	}

	@Override
	public void paintComponent(Graphics g) {
		synchronized(objects){
			for(EnvObject o : objects)
				o.paint(g, o.getRoundedPosition().x, o.getRoundedPosition().y);
		}
	}

	public Environment copy() {
		ArrayList<EnvObject> objectsCopy = new ArrayList<EnvObject>();
		for(EnvObject o : objects)
			objectsCopy.add(o.copy());
		return new Environment(objectsCopy); //TODO
	}

	int getNum(Class<? extends EnvObject> c) {
		int num = 0;
		for(EnvObject o : objects)
			if(c.isInstance(o))
				num++;
		return num;
	}

	@Deprecated
	public double getFitness2(Individual i) {
		Bird b = new Bird();
		int j;
		Point p = b.getRoundedPosition();
		p.translate(1,0);	//Sketchyyyyy
		for(j = 0; j < 100 && !b.getRoundedPosition().equals(p); j++) {				//TODO make bird find rock...
			double[] outputs = i.fire(new double[]{1,1});
			p = b.getRoundedPosition();
			b.moveForward((int)outputs[0]);
			b.rotate((int)outputs[1]);
		}
		return j;
	}
	
	public double getFitness(Individual i) {
		Bird b = new Bird();
		double[] outputs;
		double j;
		for(j = 0; j < 100 && b.getInputs(objects)[0] == -1; j++) {
			outputs = i.fire(b.getInputs(objects));
			b.accelerate(outputs[0]);
			b.rotate(outputs[1]);
			b.update();
		}
		return 0-j;
	}



}
