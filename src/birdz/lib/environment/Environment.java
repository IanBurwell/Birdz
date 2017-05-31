package birdz.lib.environment;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JComponent;

import birdz.lib.genetic.Individual;

public class Environment extends JComponent{

	private static final long serialVersionUID = 8786961387046987953L;

	private ArrayList<EnvObject> objects;
	private int width, height;
	private HashMap<Bird, Individual> iMap = null;
	private EnvThread envThread = new EnvThread();

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
				System.out.println(Arrays.toString(outputs));
				b.accelerate((outputs[0]-0.5)*2);
				b.rotate((outputs[1]-0.5)*2);
				b.update();
			}
		this.repaint();
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
	
	/**
	 * should make first bird in objects find an obstacle
	 * @param i
	 * @return
	 */
	public double getFitness(Individual i) {
		Bird b = new Bird();
		for(EnvObject e : objects)
			if(e instanceof Bird) b = (Bird)e;
		
		double[] outputs;
		double j;
		for(j = 0; j < 1000 && b.getInputs(objects)[2] == -1; j++) {
			//System.out.println(Arrays.toString(b.getInputs(objects)));
			outputs = i.fire(b.getInputs(objects));
			b.accelerate((outputs[0]-0.5)*2);
			b.rotate((outputs[1]-0.5)*2);
			b.update();
		}
		EnvObject obj = objects.get(1);
		for(EnvObject e : objects)
			if(!(e instanceof Bird)) obj = e;
		
		return (0-j) - b.getRoundedPosition().distance(obj.getRoundedPosition());
	}

	public void runEnvironment(int delay){
		envThread.startDelay(delay);
	}

	public void stopEnvironment(int delay){
		envThread.interrupt();
	}
	
	private class EnvThread extends Thread{
		int delay = 10;

		EnvThread(){
			//Environment.this;
		}

		void startDelay(int delay){
			this.delay = delay;
			this.start();
		}

		@Override
		public void run() {
			//while(!interrupted()){
			for(int i = 0; i < 1000; i++){	
				Environment.this.updateIndividuals();//TODO make it update pos n stuffs
				try {
					sleep(delay);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
		}

	}

}
