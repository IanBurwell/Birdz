package birdz.NN;

public class Init {
	
	static class IntegerInput implements NeuralNetwork.Input {

		Integer value;
		
		IntegerInput(Integer value) {
			this.value = value;
		}
		
		@Override
		public Double fire() {
			return (double) value;
		}
	}
	
	public static void main(String[] args) {
		Integer i = new Integer(0);
		Integer j = new Integer(0);
		IntegerInput I = new IntegerInput(i);
		IntegerInput J = new IntegerInput(j);
		//NeuralNetwork xorTest = new NeuralNetwork(1, 2, 1, I, J);
	}

}
