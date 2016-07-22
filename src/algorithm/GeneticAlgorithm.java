package algorithm;

import java.util.ArrayList;

public class GeneticAlgorithm<T extends Genotype> extends Thread {

	private double currentFitness, targetFitness;
	private long currentGeneration, maxGenerations;
	private ArrayList<Population<T>> populations;

	boolean debugActive;
	long debugPrintGenerations;

	/**
	 * @return the bestSolutioArrayList<Population<T>> populations;nFound. Can
	 *         be null if algorithm has not been started yet
	 */
	public Genotype getBestSolutionFound() {
		return bestSolutionFound;
	}

	private Genotype bestSolutionFound;

	public GeneticAlgorithm(ArrayList<Population<T>> populations, double minFitnessToStop) {
		super();
		init(populations, minFitnessToStop, -1);
	}

	public GeneticAlgorithm(ArrayList<Population<T>> populations, double minFitnessToStop, long maxGenerations) {
		super();
		init(populations, minFitnessToStop, maxGenerations);
	}

	private void init(ArrayList<Population<T>> populations, double objectiveFitness, long maxGenerations) {
		this.currentFitness = Double.MAX_VALUE;
		this.targetFitness = objectiveFitness;
		this.maxGenerations = maxGenerations;
		this.currentGeneration = 0;
		this.populations = populations;
		this.bestSolutionFound = null;
		// init randomly the starting populations
		for (Population<T> population : populations) {
			population.randomInit();
		}
		this.debugActive = false;
		debugPrintGenerations = Long.MAX_VALUE;
	}

	@Override
	public void run() {
		super.run();
		while (!isTerminationConditionMet()) {
			// evolutionary algorithm using populations
			for (Population<T> population : populations) {
				try {
					population.evolve();
				} catch (Exception e) {
					System.out.println("Population evolve method problem:");
					// System.out.println(e.getStackTrace());
					e.printStackTrace();
					System.out.println("Trying to continue evolution process...");
				}
				Genotype bestOfPopulation = population.getBestGenotype();
				double cost = bestOfPopulation.getFitness();
				// keep track of the best solution found until now
				if (this.currentFitness > cost) {
					this.currentFitness = cost;
					bestSolutionFound = bestOfPopulation;
				}
				if (isDebugToBePrinted())
					System.out.println(population);
			}
			currentGeneration++;
			// print only if needed
			if (isDebugToBePrinted())
				printDebugInfo();
		}
		// print final iteration
		printDebugInfo();
	}

	private boolean isTerminationConditionMet() {
		return currentFitness <= targetFitness || (maxGenerations >= 0 && currentGeneration >= maxGenerations);
	}

	/**
	 * 
	 * @param isDebugActive
	 * @param debugPrintGenerations
	 *            print debug (if active) every debugPrintGenerations
	 *            generations
	 */
	public void setDebug(boolean isDebugActive, long debugPrintGenerations) {
		this.debugActive = isDebugActive;
		this.debugPrintGenerations = debugPrintGenerations;
	}

	public boolean isDebugActive() {
		return this.debugActive;
	}

	private void printDebugInfo() {
		System.out.println("-----\nGeneration: " + this.currentGeneration + "\nCurrent best fitness: "
				+ this.currentFitness + "\n");

	}

	private boolean isDebugToBePrinted() {
		return isDebugActive() && (currentGeneration % debugPrintGenerations == 0);
	}

}
