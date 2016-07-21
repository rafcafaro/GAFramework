package genotypes;

import algorithm.Genotype;

/**
 * Genotype representation of a string permutation.
 *
 */
public class RealGenotype extends Genotype {

	private double[] genes;

	double crossoverProbability = 0.5D;

	/**
	 * Genes will be an array of real numbers
	 * 
	 * @param genotypeSize
	 *            genes will be arrays of real numbers
	 */
	public PermutationGenotype(int genotypeSize) {
		this.genes = new double[genotypeSize];
	}

	@Override
	public void randomInit() {
		// randomly shuffle the elements
		for (int i = 0; i < genes.length; i++) {
			// ********
		}

	}

	@Override
	public void mutate(double probability) {
		for (int i = 0; i < genes.length; i++) {
			// ********
		}
	}

	@Override
	public Genotype crossover(Genotype other) throws Exception {
		// ********
	}

	/**
	 * @return the genes
	 */
	public double[] getGenes() {
		return genes;
	}
}
