package birdz.Environment;

import java.awt.Point;

import birdz.lib.genetic.Individual;

public class Bird {

	public Point position;
	public double degRotation;
	private Individual individual;//TODO implement so individual can be assigned to bird
	
	public Bird(Point position, double degRotation) {
		this(null, position, degRotation);
	}
	
	public Bird(Individual i,Point position, double degRotation) {
		this.individual = i;
		this.position = position;
		this.degRotation = degRotation;
	}
	
	double getLinearAccelleration(){
		//TODO implement
		return 0.0;
	}
	
	double getRotationalAcelleration(){
		//TODO implement
		return 0.0;
	}
	
}
