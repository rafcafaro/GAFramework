package algorithm;

import java.util.Arrays;

/**
 * Representation of a collection of genotypes
 */
public class Population {
	/**
	 * population will have a fixed size
	 */
	private Genotype[] genotypes;
	private SelectionProcedure selection;
	boolean isSortedByFitness;
	private float mutationProbability;

	public Population(Genotype[] genotypes, SelectionProcedure selection) {
		this.genotypes = genotypes;
		isSortedByFitness = false;
		this.selection = selection;
		mutationProbability = (float) 0.01;
	}

	public void randomInit() {
		for (Genotype genotype : genotypes) {
			genotype.randomInit();
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
	
	private void updateGenotypes(Genotype[] newGenotypes){
		this.genotypes = newGenotypes;
		this.isSortedByFitness = false;
	}
	
	/**
	 * Perform one step of evolution of this population:
	 * -Selection;
	 * -Crossover;
	 * -Mutation;
	 * 
	 */
	public void evolve()
	{
		
		Genotype[] newPopulation = new Genotype[this.genotypes.length];
		//sort by fitness (lowest/best is first)
		sortGenotypes();
		//generate another population
		for (int i = 0; i < genotypes.length; i++) {
			//Note:Add "Elitism"
			
			//Selection
			Pair<Genotype> selected = selection.select(genotypes);
			//Crossover
			Genotype offspring = selected.first.crossover(selected.second);
			//Mutation
			offspring.mutate(mutationProbability);
			newPopulation[i] = offspring;
		}
		
		updateGenotypes(newPopulation);	
	}
}
