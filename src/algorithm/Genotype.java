package algorithm;
/**
 * Different kind of genotype representations
 * will extend this abstract Genotype
 */
public abstract class Genotype implements Comparable<Genotype>{
	
	
	protected double fitness;
	
	public abstract void randomInit();
	/**
	 * 
	 * @param probability that a single gene has to mutate
	 */
	public abstract void mutate(double probability);
	/**
	 * Crossover between two genotypes
	 * @param other
	 * @return the generated offspring
	 * @throws Exception if there is an inconsistent "other" genotype
	 */
	public abstract Genotype crossover(Genotype other) throws Exception;
	
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
	

	
}
