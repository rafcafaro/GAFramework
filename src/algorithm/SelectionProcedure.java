package algorithm;

public interface SelectionProcedure{
	
	/**
	 * Implements the selection of a pair of genotypes to crossover.
	 * @param population is a sorted array, first element has lowest fitness (best score) 
	 * @return
	 */
	Pair<Genotype> select(Genotype[] population);
}
