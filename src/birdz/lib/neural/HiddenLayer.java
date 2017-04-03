package birdz.lib.neural;

public class HiddenLayer {
	double[][] weights;
	double[] inputs;

	int size, numInputs;
	int numGenes;

	HiddenLayer(int size, int numInputs) { // 3, 3
		numGenes = (numInputs + 1) * size;
		this.size = size;
		this.numInputs = numInputs;
		initializeWeights();
	}

	public double getGene(int index) {
		return weights[index / (numInputs + 1)][index % (numInputs + 1)];
	}
	
	public void setGene(int index, double value) {
		weights[index / (numInputs + 1)][index % (numInputs + 1)] = value; 
	}
	
	public int getNumGenes() {
		return numGenes;
	}

	public int getSize() {
		return size;
	}

	public int getNumInputs() {
		return numInputs;
	}

	void initializeWeights() {
		weights = new double[size][numInputs + 1]; 
		for(int i = 0; i < size; i++)
			for(int j = 0; j < numInputs; j++)
				weights[i][j] = getRandomWeightValue();
		for(int i = 0; i < size; i++)
			weights[i][numInputs] = getRandomWeightValue();		//Biases are the last double in each subarray
	}

	public static double getRandomWeightValue() {
		return Math.random() - 0.5;
	}

	void updateInputs(double[] inputs) {
		this.inputs = inputs;
	}

	double[] fire() {
		double[] values = new double[size];
		for(int i = 0; i < size; i++) {
			double total = 0.0;
			for(int j = 0; j < numInputs; j++)
				total += inputs[j] * weights[i][j];
			total += weights[i][numInputs];				//Add bias
			values[i] = Brain.activationFunction.getValue(total);
		}
		return values;
	}
	
	@Override
	public String toString() {
		String result = "";
		for(int i = 0; i < weights.length; i++)
			result += weights[i][0] + ", ";
		return result;
	}
}
