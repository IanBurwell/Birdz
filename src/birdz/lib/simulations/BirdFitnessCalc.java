package birdz.lib.simulations;

import birdz.lib.environment.Bird;
import birdz.lib.genetic.FitnessCalc;
import birdz.lib.genetic.Individual;

public class BirdFitnessCalc implements FitnessCalc { 	//TODO Make this class so we have a function to get the inputs of the bird, 
														//which will then be integrated in the bird class (maybe get the function
														//from this class when in the bird class for cleanliness). Assign an individual
	Bird[] birds;										//to each bird, and add an update function to the bird, which moves it according to nn output

	@Override
	public double getFitness(Individual i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getIdealFitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumInputs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumLayers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLayerSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String displayFitness(Individual i) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Bird[] getBirds() {
		return birds;
	}

}
