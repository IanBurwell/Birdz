package birdz.lib.genetic;
/**
 * An object that facilitates the learning process of a population given a {@code FitnessCalc} object
 * @author Ian
 *
 */
public class Learner {

	FitnessCalc fitnessCalc;
	Population population;
	int generation = 0;

	/**
	 * @param fitnessCalc A {@code FitnessCalc} object upon which to base the learning of the population 
	 * @param popSize The size of the population
	 */
	Learner(FitnessCalc fitnessCalc, int popSize) {
		this.fitnessCalc = fitnessCalc;
		population = new Population(popSize, true, fitnessCalc, fitnessCalc.getNumInputs());
	}

	/**
	 * Repeats the learning process until any {@code Individual} in the population has achieved the target fitness specified in the {@code FitnessCalc}
	 * @param display Set to true to display the fittest {@code Individual} of each generation in the console
	 * @return The {@code Individual} that has achieved the target fitness
	 */
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

	/**
	 * Produces the next generation of the population, returning the best {@code Individual}
	 * @param display Set to true to display the fittest {@code Individual} in the console
	 * @return The fittest {@code Individual} in the population
	 */
	Individual nextGeneration(boolean display) {
		generation++;
		population = GeneticAlgorithm.evolvePopulation(population, fitnessCalc);
		if(display) System.out.println("Generation: " + generation + " Fittest: \n" + fitnessCalc.displayFitness(population.getFittest()));
		return population.getFittest();
	}

}