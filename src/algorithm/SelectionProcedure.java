package algorithm;

public interface SelectionProcedure{
	
	/**
	 * Implements the selection of n pairs of genotypes to crossover.
	 * @param genotypes is a sorted array, first element has lowest fitness (best score) 
	 * @param selected is the output array to fill with new instances of Pair of genotypes. This 
	 * array will be used to crossover the selected pairs.
	 *
	 */
	void select(Genotype[] genotypes, Pair<Genotype>[] selected);
}
