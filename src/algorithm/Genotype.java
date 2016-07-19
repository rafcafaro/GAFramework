package algorithm;
/**
 * Different kind of genotype representations
 * will extend this abstract Genotype
 */
public abstract class Genotype implements Comparable<Genotype>{
	
	
	private double fitness;
	
	public abstract void randomInit();
	/**
	 * 
	 * @param probability that a single gene has to mutate
	 */
	public abstract void mutate(float probability);
	/**
	 * Crossover between two genotypes
	 * @param other
	 * @return the generated offspring
	 */
	public abstract Genotype crossover(Genotype other);
	
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	
	@Override
	public int compareTo(Genotype other) {
		return Double.compare(this.fitness, other.fitness);
	}
	/**
	 * Select a pair of Genotype from a population.
	 * This pair could be used to crossover the two genotypes.
	 * @param population
	 * @return a pair of genotypes
	 */
	public abstract Pair<Genotype> selection(Genotype[] population);

	
}
