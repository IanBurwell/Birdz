package birdz.lib.genetic;

import birdz.lib.simulations.*;

public class Learner {

	FitnessCalc fitnessCalc;
	Population population;
	int generation = 0;

	Learner(FitnessCalc fitnessCalc, int popSize) {
		this.fitnessCalc = fitnessCalc;
		population = new Population(popSize, true, fitnessCalc, fitnessCalc.getNumInputs());
	}

	Individual learnUntilDone(boolean display) {
		while (population.getFittest().getFitness() < fitnessCalc.getIdealFitness()) {
			generation++;
			if(display) System.out.println("Generation: " + generation + " Fittest: \n" + fitnessCalc.displayFitness(population.getFittest()));
			population = GeneticAlgorithm.evolvePopulation(population, fitnessCalc);
		}
		if(display) System.out.println("Solution found!");
		if(display) System.out.println("Generation: " + generation);
		if(display) System.out.println(fitnessCalc.displayFitness(population.getFittest()));
		return population.getFittest();
	}

	Individual nextGeneration(boolean display) {
		generation++;
		population = GeneticAlgorithm.evolvePopulation(population, fitnessCalc);
		if(display) System.out.println("Generation: " + generation + " Fittest: \n" + fitnessCalc.displayFitness(population.getFittest()));
		return population.getFittest();
	}

}