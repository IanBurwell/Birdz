package birdz.lib.simulations;

import birdz.lib.genetic.FitnessCalc;
import birdz.lib.genetic.Individual;

public class SinFitnessCalc implements FitnessCalc {

	int numTests = 2;
	
	@Override
	public double getFitness(Individual i) {
		double error = 0;
		for(int j = 0; j < numTests; j++)
			error += getError(i, j);
		return numTests - error;
	}

	@Override
	public double getIdealFitness() {
		return numTests - 0.1;
	}

	@Override
	public int getNumInputs() {
		return 1;
	}

	double getError(Individual i, double input) {
		return Math.abs(i.fire(new double[]{input})[0] - Math.sin(input));
	}

	@Override
	public String displayFitness(Individual i) {
		return String.valueOf(getFitness(i));
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
	
}
