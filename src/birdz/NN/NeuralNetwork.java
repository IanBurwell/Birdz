package birdz.NN;

public class NeuralNetwork implements Comparable<NeuralNetwork> {

	public interface Input {
		Double fire();
	}

	public class Perceptron implements Input {

		NeuralNetwork network;
		Input[] inputs;
		double[] weights;


		Perceptron(Input[] inputs, double[] weights, NeuralNetwork network) {
			this.inputs = inputs;
			this.weights = new double[weights.length + 1];
			this.weights[0] = getRandomWeightValue();
			for(int i = 0; i < weights.length; i++)
				this.weights[i + 1] = weights[i];

			this.network = network;
		}

		public Double fire() {
			double sum = 0.0;
			sum += weights[0];
			for(int i = 0; i < inputs.length; i++) 
				sum += inputs[i].fire() * weights[i + 1];
			return sigmoid(sum);
		}

		/**
		 * 
		 * @param input the calculated sum of this object's inputs.
		 * @return a number between 0.0 and 1.0 representing whether this <code>Perceptron</code> is firing
		 */
		double sigmoid(double input) {
			return 1.0 / Math.pow((1 + Math.E), input);
		}

	}

	/**
	 * The 2-dimensional array of <code>Perceptrons</code>
	 */

	public Perceptron[][] network;	
	public Input[] inputs;
	public Perceptron[] outputs;
	int score = 0;

	/**
	 * Creates a <code>NeuralNetwork</code> given a number of <code>Perceptron</code> layers, layer size, and a series of inputs
	 * @param hiddenLayers The number of hidden layers in the network. Can be 0.
	 * @param layerSize The number of <code>Perceptron</code> objects in each layer. Must be greater than 0.
	 * @param numOutputs The number of outputs to the <code>NeuralNetwork</code>. Must be greater than 0.
	 * @param inputs The inputs to the neural network.
	 */
	public NeuralNetwork(int hiddenLayers, int layerSize, int numOutputs, Input ... inputs) {
		network = new Perceptron[hiddenLayers][layerSize];
		this.inputs = inputs;

		for(int i = 0; i < layerSize; i++) {												
			double[] weights = new double[layerSize];										
			for(int j = 0; j < this.inputs.length; j++)										
				weights[j] = getRandomWeightValue();										 
			network[0][i] = new Perceptron(this.inputs, weights, this);	
		}																					

		for(int l = 1; l < hiddenLayers; l++) {	
			for(int i = 0; i < layerSize; i++) {											
				double[] weights = new double[layerSize];									
				for(int j = 0; j < layerSize; j++)											
					weights[j] = getRandomWeightValue();			
				network[l][i] = new Perceptron(network[i-1], weights, this);			
			}																				
		}

		outputs = new Perceptron[numOutputs];

		for(int i = 0; i < numOutputs; i++) {
			double[] weights = new double[layerSize];
			for(int j = 0; j < layerSize; j++)
				weights[j] = getRandomWeightValue();
			outputs[i] = new Perceptron(network[network.length - 1], weights, this);
		}

	}

	public double[] run() {
		double[] result = new double[outputs.length];
		for(int i = 0; i < outputs.length; i++)
			result[i] = outputs[i].fire();
		return result;
	}

	private double getRandomWeightValue() {
		return (Math.random() * 2 / Math.sqrt(inputs.length)) - 1;
	}

	public void evolve(NeuralNetwork a, int chanceCopy, int chanceMutate) {
		for(int i = 0; i < network.length; i++)
			for(int j = 0; j < network.length; j++)
				for(int k = 0; k < network[i][j].weights.length; k++) 
					if((int)(Math.random() * chanceCopy) == 0)
						network[i][j].weights[k] = a.network[i][j].weights[k];
					else if((int)(Math.random() * chanceMutate) == 0)
						network[i][j].weights[k] = getRandomWeightValue();
	}

	public void setScore(int s) {
		score = s;
	}

	public int getScore() {
		return score;
	}


	@Override
	public int compareTo(NeuralNetwork n) {
		return n.getScore() - score;
	}
}
