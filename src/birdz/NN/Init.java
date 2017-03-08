package birdz.NN;

public class Init {
	
	public static void main(String[] args) {
		Integer i = new Integer(0);
		Integer j = new Integer(0);
		IntegerInput I = new IntegerInput(i);
		IntegerInput J = new IntegerInput(j);
		NeuralNetwork xorTest = new NeuralNetwork(1, 2, 1, I, J);
	}

}
