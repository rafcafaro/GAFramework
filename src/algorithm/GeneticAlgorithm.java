package algorithm;

import java.util.ArrayList;

public class GeneticAlgorithm <T extends Genotype> extends Thread {

	private double currentFitness, targetFitness;
	private long currentGeneration, maxGenerations;
	private ArrayList<Population<T>> populations;

	/**
	 * @return the bestSolutioArrayList<Population<T>> populations;nFound. Can be null if algorithm has not been started yet
	 */
	public Genotype getBestSolutionFound() {
		return bestSolutionFound;
	}

	private Genotype bestSolutionFound;

	public GeneticAlgorithm(ArrayList<Population<T>> populations,double minFitnessToStop) {
		super();
		init(populations,minFitnessToStop,-1);
	}

	public GeneticAlgorithm(ArrayList<Population<T>> populations,double minFitnessToStop, long maxGenerations) {
		super();
		init(populations,minFitnessToStop,maxGenerations);
	}
	private void init(ArrayList<Population<T>> populations,double objectiveFitness,long maxGenerations)
	{
		this.currentFitness = Double.MAX_VALUE;
		this.targetFitness = objectiveFitness;
		this.maxGenerations = maxGenerations;
		this.currentGeneration = 0;
		//TODO: instantiate populations!
		this.populations = populations;
		this.bestSolutionFound =null;
		//init randomly the starting populations
		for (Population<T> population : populations) {
			population.randomInit();
		}
	}

	@Override
	public void run() {
		super.run();
		while (!isTerminationConditionMet()) {
			//evolutionary algorithm using populations
			for (Population<T> population : populations) {
				try {
					population.evolve();
				} catch (Exception e) {
					System.out.println("Population evolve method problem:");
					System.out.println(e.getMessage());
					System.out.println("Trying to continue evolution process...");
				}
				Genotype bestOfPopulation = population.getBestGenotype();
				double cost = bestOfPopulation.getFitness();
				//keep track of the best solution found until now
				if(this.currentFitness > cost)
				{
					this.currentFitness = cost;
					bestSolutionFound = bestOfPopulation;
				}
				currentGeneration++;
			}
		}
	}

	private boolean isTerminationConditionMet() {
		return currentFitness <= targetFitness || (maxGenerations >= 0 && currentGeneration >= maxGenerations);
	}

}
