package birdz.Environment;

import java.awt.Point;

public class init {

	public static void main(String[] args) {
		//TODO general strucure for init:
		//1. make environment getFitness with birdz
		//2. when stuff is learned run startDisplay to visulize best bird
		
		
		
		testSpinning();
	}

	private static void testSpinning() {
		BirdzUI env = new BirdzUI("", 800, 600);
		env.waitForOpen();
		
		for(int i = 0; i < 3600; i++){
			env.devUpdate(new Bird[] {new Bird(new Point(400,300), i+180)});
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {e.printStackTrace();}}		
	}

}





