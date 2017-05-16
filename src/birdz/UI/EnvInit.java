package birdz.UI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import birdz.lib.environment.Bird;
import birdz.lib.environment.EnvObject;
import birdz.lib.environment.Environment;

public class EnvInit {

	public static void main(String[] args){
		//spam();
		testGetSight();
	}

	private static void testGetSight() {
		ArrayList<EnvObject> objects = new ArrayList<EnvObject>();
		//objects.add(new Bird(100, 100));
		objects.add(new Bird(150, 200, 180, 10, Color.BLUE));
		objects.add(new Bird(100, 200, 45, 10, Color.BLACK));

		
		JFrame frame = new EnvFrame("--", 800, 500, new Environment(objects));
		
		for(int i = 0; i < 5000; i++){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {e.printStackTrace();}
			//System.out.println(Arrays.toString(((Bird)objects.get(0)).getSight(3, objects)));
			
			if(((Bird)objects.get(0)).getSight(1, objects)[0] != -1){
				((Bird)objects.get(0)).rotate(1);
			}
			
			frame.repaint();
		}
		
	}

	static void spam(){
		ArrayList<EnvObject> objects = new ArrayList<EnvObject>();
		//objects.add(new Bird(100, 100));
		//objects.add(new Bird(100, 200, -90, 10, Color.BLUE));
		
		JFrame frame = new EnvFrame("--", 1920, 1080, new Environment(objects));

		for(int i = 0; i < 500; i++){
			synchronized(objects){
				objects.add(new Bird((int)(Math.random() * 1920),
						(int)(Math.random() * 1080),
						(int)(Math.random() * 360),
						(int)(Math.random() * 40),
						new Color((int)(Math.random() * 255),
								(int)(Math.random() * 255),
								(int)(Math.random() * 255))
						));
			}
			frame.repaint();
			
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {e.printStackTrace();}

		for(int i = 0; i < 10; i++){
			moveForward(objects, 1);
			spin(objects, 1);
			frame.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}
	private static void spin(ArrayList<EnvObject> objects, int deg){
		for(EnvObject e : objects)
			if(e instanceof Bird)
				((Bird)e).rotate(deg);
	}
	private static void moveForward(ArrayList<EnvObject> objects, int dist){
		for(EnvObject e : objects)
			if(e instanceof Bird)
				((Bird)e).moveForward(dist);
	}


}