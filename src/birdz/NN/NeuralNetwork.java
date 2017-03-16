package birdz.NN;

public class NeuralNetwork implements Comparable<NeuralNetwork> {
	
	public static final boolean DEBUGGING = true;

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

	}

	/**
	 * The 2-dimensional array of <code>Perceptrons</code>
	 */


	public Perceptron[][] network;	
	public Input[] inputs;
	public Perceptron[] outputs;
	public double score = 0;

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
				network[l][i] = new Perceptron(network/*TODO*/[l-1]/*TODO*/, weights, this);			
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
		for(int i = 0; i < outputs.length; i++) {
			result[i] = outputs[i].fire();
		}
		return result;
	}

	private double getRandomWeightValue() {
		return (Math.random() * 2 / Math.sqrt(inputs.length)) - 1;
	}

	public static void evolve(NeuralNetwork a, NeuralNetwork b, int chanceCopy, int chanceMutate) {

		if(a.getScore() < b.getScore()) {
			NeuralNetwork c;
			c = b;
			b = a;
			a = c;
		}
		for(int i = 0; i < b.network.length; i++)
			for(int j = 0; j < b.network[i].length; j++)
				for(int k = 0; k < b.network[i][j].weights.length; k++) 
					if((int)(Math.random() * chanceCopy) == 0)
						b.network[i][j].weights[k] = a.network[i][j].weights[k];
					else if((int)(Math.random() * chanceMutate) == 0)
						b.network[i][j].weights[k] = b.getRandomWeightValue();
	}
	
	public void setScore(double s) {

		score = s;
	}

	
	public double getScore() {
		return score;
	}

	
	/**
	 * 
	 * @param input the calculated sum of this object's inputs.
	 * @return a number between 0.0 and 1.0 representing whether this <code>Perceptron</code> is firing
	 */
	public double sigmoid(double input) {
		return 1.0 / (1 + Math.pow(Math.E, -input));
	}
	
	static void debug(String m) {
		if(DEBUGGING)
			System.out.println("Debug (NeuralNetwork): " + m);
	}

	@Override
	public int compareTo(NeuralNetwork n) {
		return (int) (score - n.getScore());
	}
}