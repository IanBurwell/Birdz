package birdz.lib.genetic;

import birdz.lib.neural.Brain;
import birdz.lib.neural.HiddenLayer;

public class Individual {

    static int defaultGeneLength = 5;
    private Brain brain;
    private double fitness = 0;
    FitnessCalc fitnessCalc;
    
    int numLayers = GeneticAlgorithm.numLayers;
    int layerSize = GeneticAlgorithm.layerSize;

    public Individual(){}
    
    public void generateIndividual(FitnessCalc fitnessCalc, int numInputs) {
    	this.fitnessCalc = fitnessCalc;
    	brain = new Brain(numInputs, numLayers, layerSize);
        for (int i = 0; i < size(); i++) 
            brain.setGene(i, HiddenLayer.getRandomWeightValue());
    }

    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }
    
    public double getGene(int index) {
        return brain.getGene(index);
    }

    public void setGene(int index, double value) {
        brain.setGene(index, value);
        fitness = 0;
    }

    public FitnessCalc getFitnessCalc() {
		return fitnessCalc;
	}

	public int size() {
        return brain.getNumGenes();
    }

    public double getFitness() {
        if (fitness == 0) 
            fitness = fitnessCalc.getFitness(this);
        return fitness;
    }
    
    public double[] fire(double[] inputs) {
    	return brain.fire(inputs);
    }
    
    @Override
    public String toString() {
        return brain.toString();
    }
}