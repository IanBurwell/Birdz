package birdz.lib.environment;

import java.awt.Dimension;
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
		this.setPreferredSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
	}
	
	public Environment(HashMap<Bird, Individual> hm, ArrayList<EnvObject> objects, int width, int height) {
		this(objects, width, height);
		iMap = hm;
	}


	public void updateIndividuals(){
		if(iMap == null)return;

		for(EnvObject o : objects)
			if(o instanceof Bird && iMap.containsKey(o)){
				Bird b = (Bird)o;
				double[] outputs = iMap.get(o).fire(b.getInputs(objects));		//TODO consolidate code with getFitness function
				updateBird(outputs, b);
			}
		this.repaint();
	}

	private void updateBird(double[] outputs, Bird b){
		//System.out.println(Arrays.toString(outputs));
		b.accelerate(outputs[0]);
		b.rotate(outputs[1]*10);
		b.update();
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
		return new Environment(objectsCopy, width, height); //TODO
	}

	int getNum(Class<? extends EnvObject> c) {
		int num = 0;
		for(EnvObject o : objects)
			if(c.isInstance(o))
				num++;
		return num;
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
		double score = 0;
		EnvObject obj = objects.get(1);
		for(EnvObject e : objects)
			if(!(e instanceof Bird)) obj = e;
		for(j = 0; j < 1000; j++) {
			//System.out.println(Arrays.toString(b.getInputs(objects)));
			outputs = i.fire(b.getInputs(objects));
			updateBird(outputs, b);
			score -= Math.pow(b.getRoundedPosition().distance(obj.getRoundedPosition()),2);
		}
		return score;
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
