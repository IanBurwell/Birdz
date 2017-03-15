package birdz.NN;

public class NeuralNetwork {

	public interface Input {
		Double fire();
	}

	public class Perceptron implements Input {
		
		NeuralNetwork network;
		Input[] inputs;
		double[] weights;
		double bias;

		Perceptron(Input[] inputs, double[] weights, double bias, NeuralNetwork network) {
			this.inputs = inputs;
			this.weights = weights;
			this.bias = bias;
			this.network = network;
		}

		public Double fire() {
			double sum = 0.0;
			for(int i = 0; i < inputs.length; i++) 
				sum += inputs[i].fire() * weights[i];
			sum += bias;
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
		
		void mutate(int numMutations) {
			for(int i = 0; i < numMutations; i++) 
				weights[(int) (Math.random() * weights.length)] = network.getRandomWeightValue();
		}
	}

	/**
	 * The 2-dimensional array of <code>Perceptrons</code>
	 */
	public Perceptron[][] network;	
	public Input[] inputs;
	public Perceptron[] outputs;
	
	
	
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
			network[0][i] = new Perceptron(this.inputs, weights, getRandomWeightValue(), this);	
		}																					

		for(int l = 1; l < hiddenLayers; l++) {	
			for(int i = 0; i < layerSize; i++) {											
				double[] weights = new double[layerSize];									
				for(int j = 0; j < layerSize; j++)											
					weights[j] = getRandomWeightValue();			
				network[l][i] = new Perceptron(network[l-1], weights, getRandomWeightValue(), this);			
			}																				
		}

		outputs = new Perceptron[numOutputs];

		for(int i = 0; i < numOutputs; i++) {
			double[] weights = new double[layerSize];
			for(int j = 0; j < layerSize; j++)
				weights[j] = getRandomWeightValue();
			outputs[i] = new Perceptron(network[network.length - 1], weights, getRandomWeightValue(), this);
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

	public void mutate(int numMutations) {	//TODO
		Perceptron toMutate;
		for(int i = 0; i < numMutations; i++) {
			toMutate = network[(int)(Math.random() * network.length)][(int)(Math.random() * network[0].length)];
			if(Math.random() * (toMutate.weights.length + 1) < 1)
				toMutate.bias = getRandomWeightValue();
			else
				toMutate.weights[(int)(Math.random() * toMutate.weights.length)] = getRandomWeightValue();
		}
	}
	
}
