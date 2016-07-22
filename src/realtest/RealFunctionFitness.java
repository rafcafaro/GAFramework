package realtest;

import algorithm.FitnessFunction;
import genotypes.RealGenotype;

public class RealFunctionFitness implements FitnessFunction<RealGenotype> {

	@Override
	public double calculateFitness(RealGenotype genotype) {
		// Calculate the function f(x) = x[1]^2 + x[2]^2 + ... + x[n]^2
		double[] genes = genotype.getGenes();
		double f=0; // value of the function
		for (int i = 0; i < genes.length; i++) {
			f += genes[i]*genes[i];
		}
		return f;
	}
}
