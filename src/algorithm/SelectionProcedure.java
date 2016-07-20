package algorithm;

public interface SelectionProcedure<T extends Genotype>{
	
	/**
	 * Implements the selection of a pair of genotypes to crossover.
	 * @param population is a sorted array, first element has lowest fitness (best score) 
	 * @return
	 */
	Pair<T> select(T[] population);
}
