package selection;

import algorithm.Genotype;
import algorithm.Pair;
import algorithm.SelectionProcedure;

/**
 * 
 * Implements a totally random selection for crossover
 *
 */
public class RandomSelection implements SelectionProcedure {

	@Override
	public void select(Genotype[] genotypes, Pair<Genotype>[] selected) {
		for (int i = 0; i < selected.length; i++) {
			Pair<Genotype> pair = new Pair<>();
			pair.first = getRandomGenotype(genotypes);
			pair.second = getRandomGenotype(genotypes);
			selected[i] = pair;
		}
	}
	
	private Genotype getRandomGenotype(Genotype[] genotypes)
	{
		int randomIndex = (int) (Math.random()*(double)genotypes.length);
		return genotypes[randomIndex];
	}

	

}
