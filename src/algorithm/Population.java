package algorithm;

import java.util.Arrays;

/**
 * Representation of a collection of genotypes
 */
public class Population<T extends Genotype> {
	/**
	 * population will have a fixed size
	 */
	private T[] genotypes;
	private SelectionProcedure<T> selection;
	private FitnessFunction<T> fitness;
	boolean isSortedByFitness;
	private double mutationProbability;

	public Population(T[] genotypes, SelectionProcedure<T> selection, FitnessFunction<T> fitness) {
		this.genotypes = genotypes;
		isSortedByFitness = false;
		this.selection = selection;
		this.fitness = fitness;
		mutationProbability = (double) 0.01;
	}

	public void randomInit() {
		for (T genotype : genotypes) {
			genotype.randomInit();
			genotype.setFitness(fitness.calculateFitness(genotype));
		}
		isSortedByFitness = false;
	}

	public Genotype getBestGenotype() {
		// if population is already sorted, complexity is O(1),
		// else it is O(n)
		if (isSortedByFitness)
			return genotypes[0];
		else {
			return getMinFitnessGenotype(genotypes);
		}
	}

	private Genotype getMinFitnessGenotype(Genotype[] g) {
		Genotype min = null;
		for (Genotype genotype : g) {
			if (min == null || min.getFitness() > genotype.getFitness())
				min = genotype;
		}
		return min;
	}

	private void sortGenotypes() {
		if (!isSortedByFitness) {
			Arrays.sort(genotypes);
			isSortedByFitness = true;
		}
	}
	/**
	 * Population size
	 * @return numbero of sample in the population
	 */
	public int getSize()
	{
		return this.genotypes.length;
	}
	
	private void updateGenotypes(T[] newGenotypes){
		this.genotypes = newGenotypes;
		this.isSortedByFitness = false;
	}
	
	/**
	 * Perform one step of evolution of this population:
	 * -Selection;
	 * -Crossover;
	 * -Mutation;
	 * @throws Exception 
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void evolve() throws Exception
	{
		
		Genotype[] newPopulation = new Genotype[this.genotypes.length];
		//sort by fitness (lowest/best is first)
		sortGenotypes();
		//generate another population
		for (int i = 0; i < genotypes.length; i++) {
			//Note:Add "Elitism"
			
			//Selection
			Pair<T> selected = selection.select(genotypes);
			//Crossover
			T offspring = (T) selected.first.crossover(selected.second);
			//Mutation
			offspring.mutate(mutationProbability);
			offspring.setFitness(fitness.calculateFitness(offspring));

			newPopulation[i] = offspring;
		}
		
		updateGenotypes((T[]) newPopulation);	
	}
}
