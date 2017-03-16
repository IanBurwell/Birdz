package birdz.NN;

public class IntegerInput implements NeuralNetwork.Input {

	Integer value;
	
	public IntegerInput(Integer value) {
		this.value = value;
	}
	
	@Override
	public Double fire() {
		return (double) value;
	}
	
	public String toString() {
		return value.toString();
	}
}
