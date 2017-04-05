package birdz.lib.genetic;

import birdz.lib.simulations.XorFitnessCalc;

public class Init {

    public static void main(String[] args) {
    	//ianInit();
    	pixelInit();
    }

    
    
	private static void pixelInit() {
		
		
	}

	private static void ianInit() {
		Learner xorLearner = new Learner(new XorFitnessCalc(), 50);
		xorLearner.learnUntilDone(true);	
	}
}