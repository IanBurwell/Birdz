package birdz.lib.simulations;

import birdz.lib.genetic.FitnessCalc;
import birdz.lib.genetic.Individual;

public class StringFitnessCalc implements FitnessCalc {

	String alphabet = "abcdefghijklmnopqrstuvwxyz";
	String target = "hello"; 
	double[] outputs;

	@Override
	public double getFitness(Individual i) {
		double[] targetValues = getTargetValues();
		outputs = i.fire(targetValues);
		double error= 0.0;
		for(int j = 0; j < target.length(); j++) 
			error += Math.abs(targetValues[j] - outputs[j]);
		return 0 - error;
	}

	@Override
	public double getIdealFitness() {
		return -0.000001;
	}

	@Override
	public int getNumInputs() {
		return target.length();
	}

	private double[] getTargetValues() {
		double[] inputs = new double[target.length()];
		char[] targetChars = target.toCharArray();
		for(int i = 0; i < target.length(); i++) 
			inputs[i] = alphabet.indexOf((targetChars[i])) / alphabet.length();
		return inputs;
	}

	@Override
	public String displayFitness(Individual i) {
		if(outputs.length == 0)
			return "";
		String fitness = "";
		for(int j = 0; j < outputs.length; j++)
			fitness = fitness + alphabet.charAt((int) (outputs[j] * alphabet.length()));
		return fitness;
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
