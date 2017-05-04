package birdz.lib.genetic;
/**
 * An object to provide learning parameters to a population.
 * @author Ian
 */
public interface FitnessCalc {
	/**
	 * Returns the fitness of the given {@code Individual}. A greater fitness value is considered a better fitness.
	 * @param i The individual to score
	 * @return The fitness of the {@code Individual}
	 */
	double getFitness(Individual i);
	
	/**
	 * @return The target fitness (i.e. what the fitness will be when the task is completed)
	 */
	double getIdealFitness();
	/**
	 * @return The number of inputs to each {@code Individual}'s neural network
	 */
	int getNumInputs();
	/**
	 * @return The number of layers in each {@code Individual}'s neural network
	 */
	int getNumLayers();
	/**
	 * @return The size of each layer in the {@code Individual}'s neural network. This also determines the number of values that will be returned by the neural network.
	 */
	int getLayerSize();
	/**
	 * For use with println
	 * @param i The {@code Individual} of which the fitness is being determined
	 * @return A {@code String} containing formatted output of the {@code Individual}'s fitness
	 */
	String displayFitness(Individual i);
}
