package birdz.NN;

class IntegerInput implements NeuralNetwork.Input {

	Integer value;
	
	IntegerInput(Integer value) {
		this.value = value;
	}
	
	@Override
	public Double fire() {
		return (double) value;
	}
}
