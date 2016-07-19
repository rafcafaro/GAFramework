package algorithm;

public class GeneticAlgorithm extends Thread {

	private double currentFitness, targetFitness;
	private long currentGeneration, maxGenerations;
	private Population[] populations;
	/**
	 * @return the bestSolutionFound. Can be null if algorithm has not been started yet
	 */
	public Genotype getBestSolutionFound() {
		return bestSolutionFound;
	}

	private Genotype bestSolutionFound;

	public GeneticAlgorithm(double minFitnessToStop,int populationSize) {
		super();
		init(minFitnessToStop,populationSize,-1);
	}

	public GeneticAlgorithm(double objectiveFitness,int populationSize, long maxGenerations) {
		super();
		init(objectiveFitness,populationSize,maxGenerations);
	}
	private void init(double objectiveFitness,int popSize, long maxGenerations)
	{
		this.currentFitness = Double.MAX_VALUE;
		this.targetFitness = objectiveFitness;
		this.maxGenerations = maxGenerations;
		this.currentGeneration = 0;
		this.populations = new Population[popSize];
		this.bestSolutionFound =null;
	}

	@Override
	public void run() {
		super.run();
		while (!isTerminationConditionMet()) {
			//evolutionary algorithm using populations
			for (Population population : populations) {
				population.evolve();
				Genotype popBest = population.getBestGenotype();
				double cost = popBest.getFitness();
				//keep track of the best solution found until now
				if(this.currentFitness > cost)
				{
					this.currentFitness = cost;
					bestSolutionFound = popBest;
				}
				currentGeneration++;
			}
		}
	}

	private boolean isTerminationConditionMet() {
		return currentFitness <= targetFitness || (maxGenerations >= 0 && currentGeneration >= maxGenerations);
	}

}
