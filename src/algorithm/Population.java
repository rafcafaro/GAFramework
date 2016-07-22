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
	private SelectionProcedure selection;
	private FitnessFunction<T> fitness;
	boolean isSortedByFitness;
	private double mutationProbability;
	private double elitismFactor;

	public Population(T[] genotypes, SelectionProcedure selection, FitnessFunction<T> fitness) {
		this.genotypes = genotypes;
		isSortedByFitness = false;
		this.selection = selection;
		this.fitness = fitness;
		mutationProbability = (double) 0.01;
		elitismFactor = (double) 0.3F;
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
	 * 
	 * @return numbero of sample in the population
	 */
	public int getSize() {
		return this.genotypes.length;
	}

	private void updateGenotypes(T[] newGenotypes) {
		this.genotypes = newGenotypes;
		this.isSortedByFitness = false;
	}

	/**
	 * Perform one step of evolution of this population: -Selection; -Crossover;
	 * -Mutation;
	 * 
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void evolve() throws Exception {

		int elitismIndex = (int) ((float) this.genotypes.length * elitismFactor);
		Genotype[] newPopulation = new Genotype[this.genotypes.length];
		// sort by fitness (lowest/best is first)
		sortGenotypes();

		// constrain the number of pairs to generate for the Crossover operation
		int desiredPairCount = this.genotypes.length;
		Pair<Genotype>[] selected = new Pair[desiredPairCount];

		// Selection, save new pairs to selected array
		selection.select(genotypes, selected);

		// generate another population
		for (int i = 0; i < selected.length; i++) {
			// Note:Add "Elitism"
			if (i < elitismIndex)
				newPopulation[i] = this.genotypes[i];
			else {
				// selected array can be filled by user defined class. Check for
				// robustness.
				if (selected[i] != null) {
					// Crossover
					T offspring = (T) selected[i - elitismIndex].first.crossover(selected[i - elitismIndex].second);
					// Mutation
					offspring.mutate(mutationProbability);
					offspring.setFitness(fitness.calculateFitness(offspring));

					newPopulation[i] = offspring;
				}
			}
		}

		updateGenotypes((T[]) newPopulation);
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < genotypes.length; i++) {
			s += "Genotype:" + i + "\n";
			s += genotypes[i] + "\n";
		}

		return s;

	}
}
