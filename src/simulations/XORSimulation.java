package simulations;

import birdz.NN.*;

public class XORSimulation {
	int popSize;
	public NeuralNetwork[] population;
	double[] results;
	double[] scores;

	Integer a, b;
	IntegerInput inputA, inputB;

	
	
	public void run(int populationSize, int copyChance, int mutateChance, int crossesPerGeneration, int delay) {
		this.popSize = populationSize;
		population = new NeuralNetwork[popSize];
		results = new double[popSize];
		scores = new double[popSize];

		a = 0;
		b = 0;
		inputA = new IntegerInput(a);
		inputB = new IntegerInput(b);

		for(int i = 0; i < popSize; i++) 
			population[i] = new NeuralNetwork(1, 2, 1, inputA, inputB);

		boolean stupid = true;
		while(stupid) {
			getResults();
			scoreResults(0);
			a = 1;
			getResults();
			scoreResults(1);
			b = 1;
			getResults();
			scoreResults(0);
			a = 0;
			getResults();
			scoreResults(1);

			for(int i = 0; i < popSize; i++) {
				System.out.print(((int) (scores[i] * 100)) / 100.0 + " ");

//				if(scores[i] > 3)
//					stupid = false;
			}
			System.out.println();
			for(int i = 0; i < crossesPerGeneration; i++)
				breed(copyChance, mutateChance);
			resetScores();
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	private void getResults() {
		for(int i = 0; i < popSize; i++) {
			results[i] = population[i].run()[0];
		}
	}

	void breed(int copyChance, int mutateChance) {
		int a = 0, b = 0;
		double total = 0;
		for(int i = 0; i < popSize; i++)
			total += scores[i];

		for(double t = Math.random() * total; t > 0; t -= scores[a], a++);
		for(double t = Math.random() * total; t > 0; t -= scores[b], b++);
		a--; b--;
//		System.out.println("Members to breed: " + a + ", " + b);
		NeuralNetwork.evolve(population[a], population[b], copyChance, mutateChance);
	}

	
	void scoreResults(int target) {
		for(int i = 0; i < popSize; i++) {
			addScore(i, 10 - Math.pow(results[i] - target, 2));
		}
	}

	void addScore(int index, double score) {
		scores[index] += score;
		population[index].setScore(population[index].getScore() + score);
	}

	void resetScores() {
		for(int i = 0; i < popSize; i++)
			scores[i] = 0;
	}
}
