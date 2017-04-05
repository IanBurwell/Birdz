package birdz.lib.genetic;

public class Init {

    public static void main(String[] args) {
    	//ianInit();
    	pixelInit();
    }

    
    
	private static void pixelInit() {
		
		
	}

	private static void ianInit() {
		FitnessCalc fitnessCalc = GeneticAlgorithm.fitnessCalc;
        Population myPop = new Population(50, true, fitnessCalc, fitnessCalc.getNumInputs());
        
        int generationCount = 0;
        while (myPop.getFittest().getFitness() < fitnessCalc.getIdealFitness()) {
            generationCount++;
			System.out.println("Generation: " + generationCount + " Fittest: " + fitnessCalc.displayFitness(myPop.getFittest()));
            myPop = GeneticAlgorithm.evolvePopulation(myPop);
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);		
	}
}