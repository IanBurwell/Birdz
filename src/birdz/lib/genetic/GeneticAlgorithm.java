package birdz.lib.genetic;

import birdz.lib.simulations.*;
import birdz.lib.neural.HiddenLayer;

public class GeneticAlgorithm {

	private static final double uniformRate = 0.5; //0.5
	private static final double mutationRate = 0.015; //0.015
	private static final int tournamentSize = 5; //5
	private static final boolean elitism = true; //true
	static final int numLayers = 10;
    static final int layerSize = 10;

	public static Population evolvePopulation(Population pop, FitnessCalc fitnessCalc) {
		Population newPopulation = new Population(pop.size(), false, fitnessCalc, fitnessCalc.getNumInputs());

		if (elitism) 
			newPopulation.saveIndividual(0, pop.getFittest());

		int elitismOffset = (elitism) ? 1 : 0;

		for (int i = elitismOffset; i < pop.size(); i++) 
			newPopulation.saveIndividual(i, crossover(tournamentSelection(pop, fitnessCalc), tournamentSelection(pop, fitnessCalc), fitnessCalc));
		
		for (int i = elitismOffset; i < newPopulation.size(); i++) 
			mutate(newPopulation.getIndividual(i));
		
		return newPopulation;
	}

	private static Individual crossover(Individual indiv1, Individual indiv2, FitnessCalc fitnessCalc) {
		Individual newSol = new Individual();
		newSol.generateIndividual(fitnessCalc, fitnessCalc.getNumInputs()); //
		for (int i = 0; i < indiv1.size(); i++) 
			newSol.setGene(i, (Math.random() <= uniformRate) ? indiv1.getGene(i) : indiv2.getGene(i));
		
		return newSol;
	}

	private static void mutate(Individual indiv) {
		for (int i = 0; i < indiv.size(); i++) 
			if (Math.random() <= mutationRate) 
				indiv.setGene(i, HiddenLayer.getRandomWeightValue());
	}

	private static Individual tournamentSelection(Population pop, FitnessCalc fitnessCalc) {
		Population tournament = new Population(tournamentSize, false, fitnessCalc, fitnessCalc.getNumInputs());

		for (int i = 0; i < tournamentSize; i++) 
			tournament.saveIndividual(i, pop.getIndividual((int) (Math.random() * pop.size())));
		
		return tournament.getFittest();
	}
}