package birdz.lib.genetic;

public interface FitnessCalc {
	
	double getFitness(Individual i);
	
	double getIdealFitness();
	
	int getNumInputs();
	
	int getNumLayers();
	
	int getLayerSize();
	
	String displayFitness(Individual i);
}
