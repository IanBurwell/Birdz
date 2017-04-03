package birdz.lib.genetic;

public interface FitnessCalc {
	
	double getFitness(Individual i);
	
	double getIdealFitness();
	
	int getNumInputs();
	
	String displayFitness(Individual i);
}
