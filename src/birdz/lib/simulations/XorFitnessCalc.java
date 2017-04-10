package birdz.lib.simulations;

import birdz.lib.genetic.FitnessCalc;
import birdz.lib.genetic.Individual;

public class XorFitnessCalc implements FitnessCalc {

	@Override
	public double getFitness(Individual i) {
		double error = 0;

		error += Math.abs(i.fire(new double[]{0,0})[0]);	//Should be 0
		error += Math.abs(i.fire(new double[]{1,0})[0]-1);//Should be 1
		error += Math.abs(i.fire(new double[]{0,1})[0]-1);//Should be 1
		error += Math.abs(i.fire(new double[]{1,1})[0]);	//Should be 0
		
		return 4.0 - error;
	}

	@Override
	public double getIdealFitness() {
		return 4.0;
	}

	@Override
	public int getNumInputs() {
		return 2;
	}

	@Override
	public String displayFitness(Individual i) {
		return String.valueOf(getFitness(i));
//		String display = "";
//    	display += String.valueOf(i.fire(new double[]{0,0})[0]) + " (should be 0)\n";
//    	display += String.valueOf(i.fire(new double[]{1,0})[0]) + " (should be 1)\n";
//    	display += String.valueOf(i.fire(new double[]{0,1})[0]) + " (should be 1)\n";
//    	display += String.valueOf(i.fire(new double[]{1,1})[0]) + " (should be 0)";
//    	return display;
	}

	@Override
	public int getNumLayers() {
		return 2;
	}

	@Override
	public int getLayerSize() {
		return 2;
	}

}
