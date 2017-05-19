package birdz.lib.simulations;

import birdz.lib.environment.Environment;
import birdz.lib.genetic.FitnessCalc;
import birdz.lib.genetic.Individual;

public class BirdFitnessCalc implements FitnessCalc { 

	final Environment template;
	
	public BirdFitnessCalc(Environment e) {
		template = e;
	}
	
	@Override
	public double getFitness(Individual i) {
		return template.copy().getFitness(i);
	}

	@Override
	public double getIdealFitness() {
		return 0;
	}

	@Override
	public int getNumInputs() {
		return 0;
	}

	@Override
	public int getNumLayers() {
		return 3;
	}

	@Override
	public int getLayerSize() {
		return 4;
	}

	@Override
	public String displayFitness(Individual i) {
		double fitness = getFitness(i);
		return (fitness <= -1000) ? "did not reach target" : ("reached target after " + fitness + " frames");
	}

}
