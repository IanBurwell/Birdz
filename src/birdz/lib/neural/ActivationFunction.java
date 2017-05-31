package birdz.lib.neural;

public interface ActivationFunction {
	double getValue(double input);
}

class SigmoidFunction implements ActivationFunction {

	@Override
	public double getValue(double input) {
		return 1.0 / (1 + Math.pow(Math.E, -input)); 
	}
	
}

class TanhFunction implements ActivationFunction {

	@Override
	public double getValue(double input) {
		return Math.tanh(input);
	}
	
}

class StepFunction implements ActivationFunction {

	@Override
	public double getValue(double input) {
		return (input == 0) ? 0.5 : ((input > 0) ? 1 : 0); 
	}
	
}