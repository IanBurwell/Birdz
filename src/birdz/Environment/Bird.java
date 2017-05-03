package birdz.Environment;

import java.awt.Point;

import birdz.lib.genetic.Individual;

public class Bird {

	public Point position;
	public double degRotation;
	
	public Bird(Point position, double degRotation) {
		this.position = position;
		this.degRotation = degRotation;
	}
	
	double getLinearAccelleration(boolean[] inputs){
		//TODO implement for UI
		return 0.0;
	}
	
	double getRotationalAcelleration(boolean[] inputs){
		//TODO implement for UI
		return 0.0;
	}
	
}
