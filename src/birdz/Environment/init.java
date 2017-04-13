package birdz.Environment;

import java.awt.Point;

public class init {

	public static void main(String[] args) {
		//TODO general strucure for init:
		//1. make Learner with individuals 
		//2. make environment give info to birdz when they request to getFitness
		//3. when stuff is learned run startDisplay to visulize best bird
		
		testSpinning();
	}

	private static void testSpinning() {
		Environment env = new Environment(800, 600);
		env.waitForOpen();
		
		for(int i = 0; i < 3600; i++){
			env.devUpdate(new Bird[] {new Bird(new Point(400,300), i+180)});
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {e.printStackTrace();}}		
	}

}
