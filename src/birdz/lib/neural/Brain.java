package birdz.lib.neural;

public class Brain {
	
	HiddenLayer[] layers;
	double[] inputs;
	static final ActivationFunction activationFunction = new StepFunction();

	public Brain(int numInputs, int numLayers, int layerSize) {
		layers = new HiddenLayer[numLayers];
		layers[0] = new HiddenLayer(layerSize, numInputs);	//Initialize first layer with NN inputs as inputs
		for(int i = 1; i < numLayers && numLayers > 1; i++)
			layers[i] = new HiddenLayer(layerSize, layerSize);	//Initialize all following layers with previous layer as inputs
	}
	
	public double[] fire() {
		layers[0].updateInputs(inputs);
		double[] values = layers[0].fire();
		for(int i = 1; i < layers.length && layers.length > 1; i++) {
			layers[i].updateInputs(values);
			values = layers[i].fire();
		}
		return values;
	}
	
	public double[] fire(double[] inputs) {
		this.inputs = inputs;
		return fire();
	}
	
	public double getGene(int index) {
		assert (index < getNumGenes());
		int layer = 0, gene = 0;
		while(index > 0) {
			gene++;
			if(gene >= layers[layer].getNumGenes()) {
				gene = 0;
				layer++;
			}
			index--;
		}
		return layers[layer].getGene(gene);
	}
	
	public int getNumGenes() {
		int total = 0;
		for(int i = 0; i < layers.length; i++)
			total += layers[i].getNumGenes();
		return total;
	}
	
	public void setGene(int index, double value) {
		int layer = 0, gene = 0;
		while(index > 0) {
			gene++;
			if(gene >= layers[layer].getNumGenes()) {
				gene = 0;
				layer++;
			}
			index--;
		}
		layers[layer].setGene(gene, value);
	}
	
	@Override
	public String toString() {
		String result = "";
		for(HiddenLayer l : layers)
			result += l.toString() + "\n";
		return result;
	}
	
}
