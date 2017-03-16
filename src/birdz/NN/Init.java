package birdz.NN;

import simulations.*;

public class Init {
	
	static final int POPULATION_SIZE = 20;
	static final int INHERIT_CHANCE = 2;
	static final int MUTATE_CHANCE = 30;
	static final int CROSSES_PER_GENERATION = 100;
	static final int DELAY_MILLIS = 500;
	
	public static void main(String[] args) {
		XORSimulation xorSim = new XORSimulation();
		xorSim.run(POPULATION_SIZE, INHERIT_CHANCE, MUTATE_CHANCE, CROSSES_PER_GENERATION, DELAY_MILLIS);
	}

}
