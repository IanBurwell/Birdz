package birdz.lib.simulations;

import birdz.lib.genetic.FitnessCalc;
import birdz.lib.genetic.Individual;

public class XorFitnessCalc implements FitnessCalc {

	@Override
	public double getFitness(Individual i) {
		double error = 0;

		error += i.fire(new double[]{0,0})[0];	//Should be 0
		error += i.fire(new double[]{1,0})[0]-1;//Should be 1
		error += i.fire(new double[]{0,1})[0]-1;//Should be 1
		error += i.fire(new double[]{1,1})[0];	//Should be 0
		
		return 4.0 - Math.abs(error);
	}

	@Override
	public double getIdealFitness() {
		return 3.999;
	}

	@Override
	public int getNumInputs() {
		return 2;
	}

	@Override
	public String displayFitness(Individual i) {
		return String.valueOf(getFitness(i));
	}

}
