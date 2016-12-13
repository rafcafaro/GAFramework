package genotypes;

import algorithm.Genotype;

/**
 * Genotype representation of a string permutation.
 *
 */
public class RealGenotype extends Genotype {

	private double[] genes;

	// TODO: this parameters should be configurable
	double crossoverProbability = 0.5D; 
	double mutationStep = 0.01;
	// used to reduce computation each time a mutation is needed
	private double mutationStepHalf = mutationStep / 2;
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
				// Perturb by random value from -mutationStepHalf to mutationStepHalf
				genes[i] += Math.random() * mutationStep - mutationStepHalf;
			}
		}
	}

	@Override
	public Genotype crossover(Genotype other) throws Exception {
		Genotype offspring = other;
		if (other instanceof RealGenotype) {
			// Select a random index for the cross-over point
			int randomIndex = (int) (Math.random() * genes.length);
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
			throw new Exception("crossover called on incompatible type of genotype."
					+ " Expected: RealGenotype");
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
