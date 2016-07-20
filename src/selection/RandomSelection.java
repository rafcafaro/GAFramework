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
	public Pair<Genotype> select(Genotype[] genotypes) {
		// TODO Auto-generated method stub
		Pair<Genotype> pair = new Pair<>();
		pair.first = getRandomGenotype(genotypes);
		pair.second = getRandomGenotype(genotypes);
		return pair;
	}
	
	private Genotype getRandomGenotype(Genotype[] genotypes)
	{
		int randomIndex = (int) (Math.random()*(double)genotypes.length);
		return genotypes[randomIndex];
	}

}
