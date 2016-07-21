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
	public RealGenotype(int genotypeSize) {
		this.genes = new double[genotypeSize];
	}

	@Override
	public void randomInit() {
		// randomly shuffle the elements
		for (int i = 0; i < genes.length; i++) {
			genes[i] = Math.random();
		}
	}

	@Override
	public void mutate(double probability) {
		for (int i = 0; i < genes.length; i++) {
			if (Math.random() <= probability) {
				// Perturb by real random from 0 to 1
				genes[i] = Math.random();
			}
		}
	}

	@Override
	public Genotype crossover(Genotype other) throws Exception {
		Genotype offspring = other;
		if (other instanceof RealGenotype) {
			// Select a random index for the cross-over point
			int randomIndex = genes.length/2;
			double[] otherGenes = ((RealGenotype) other).genes;
			double[] offspringGenes = new double[this.genes.length];
			for(int i=0; i<=randomIndex; i++) {
				offspringGenes[i] = otherGenes[i];
			}
			for(int i=randomIndex+1; i<genes.length; i++) {
				offspringGenes[i] = genes[i];
			}
			offspring = new RealGenotype(this.genes.length);
			((RealGenotype) offspring).genes = offspringGenes;
			
		} else {

		}
		return offspring;
	}

	/**
	 * @return the genes
	 */
	public double[] getGenes() {
		return genes;
	}
}
