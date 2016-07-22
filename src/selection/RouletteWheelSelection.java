package selection;
import java.util.Random;
import algorithm.Genotype;
import algorithm.Pair;
import algorithm.SelectionProcedure;

/**
 * 
 * Implements a totally random selection for crossover
 *
 */
public class RouletteWheelSelection implements SelectionProcedure {

	@Override
	public void select(Genotype[] genotypes, Pair<Genotype>[] selected) {
		double sumF = fitnessSum(genotypes);
		Random generator = new Random();
		int s1, s2;
		for (int i = 0; i < selected.length; i++) {
			Pair<Genotype> pair = new Pair<>();
			s1 = generator.nextInt((int)sumF);
			s2 = generator.nextInt((int)sumF);
			pair.first  = getLoopGenotype(genotypes, s1);
			pair.second = getLoopGenotype(genotypes, s2);
			selected[i] = pair;
		}
	}

	private double fitnessSum(Genotype [] genotypes) {
		double sum = 0;
		double maxF = genotypes[genotypes.length-1].getFitness();
		for(int i = 0; i < genotypes.length; i++) {
			sum += maxF-genotypes[i].getFitness();
		}
		return sum;
	}

	private Genotype getLoopGenotype(Genotype[] genotypes, int s)
	{
		double cumulative = 0;
		double maxF = genotypes[genotypes.length-1].getFitness();
		for(int i = 0; i < genotypes.length; i++) {
			cumulative += maxF-genotypes[i].getFitness();
			if(cumulative > s) {
				return genotypes[i];
			}
		}
		return genotypes[genotypes.length-1];
	}

}
