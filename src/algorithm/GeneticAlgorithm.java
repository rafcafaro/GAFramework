package algorithm;

public class GeneticAlgorithm <T extends Genotype> extends Thread {

	private double currentFitness, targetFitness;
	private long currentGeneration, maxGenerations;
	private Population<T>[] populations;
	private FitnessFunction<T> fitness;
	/**
	 * @return the bestSolutionFound. Can be null if algorithm has not been started yet
	 */
	public Genotype getBestSolutionFound() {
		return bestSolutionFound;
	}

	private Genotype bestSolutionFound;

	public GeneticAlgorithm(double minFitnessToStop,int populationSize,FitnessFunction<T> fitness) {
		super();
		init(minFitnessToStop,populationSize,-1,fitness);
	}

	public GeneticAlgorithm(double objectiveFitness,int populationSize, long maxGenerations,FitnessFunction<T> fitness) {
		super();
		init(objectiveFitness,populationSize,maxGenerations,fitness);
	}
	private void init(double objectiveFitness,int popSize, long maxGenerations,FitnessFunction<T> fitness)
	{
		this.currentFitness = Double.MAX_VALUE;
		this.targetFitness = objectiveFitness;
		this.maxGenerations = maxGenerations;
		this.currentGeneration = 0;
		this.fitness = fitness;
		//TODO: instantiate populations!
		this.populations = new Population[popSize];
		this.bestSolutionFound =null;
	}

	@Override
	public void run() {
		super.run();
		while (!isTerminationConditionMet()) {
			//evolutionary algorithm using populations
			for (Population population : populations) {
				try {
					population.evolve();
				} catch (Exception e) {
					System.out.println("Population evolve method problem:");
					System.out.println(e.getMessage());
					System.out.println("Trying to continue evolution process...");
				}
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
