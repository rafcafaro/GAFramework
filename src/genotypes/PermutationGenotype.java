package genotypes;

import java.util.HashSet;

import algorithm.Genotype;

/**
 * Genotype representation of a string permutation.
 *
 */
public class PermutationGenotype extends Genotype {

	private int[] genes;

	// TODO: this parameter should be configurable
	double crossoverProbability = 0.5D;
	/*
	 * Set of integers used in the permutation
	 */
	HashSet<Integer> permutationSet;

	/**
	 * Genes will be integers from 0 to permutationSize-1
	 * 
	 * @param permutationSize
	 *            genes will be integers from 0 to permutationSize-1
	 */
	public PermutationGenotype(int permutationSize) {
		this.genes = new int[permutationSize];
		this.permutationSet = new HashSet<Integer>();
		for (int i = 0; i < genes.length; i++) {
			this.genes[i] = i;
			permutationSet.add(i);
		}

	}

	@Override
	public void randomInit() {
		// randomly shuffle the elements
		for (int i = 0; i < genes.length; i++) {
			swapRandomly(this.genes, i);
		}

	}

	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	@Override
	public void mutate(double probability) {
		for (int i = 0; i < genes.length; i++) {
			if (Math.random() <= probability) {
				swapRandomly(this.genes, i);
			}
		}
	}

	@Override
	public Genotype crossover(Genotype other) throws Exception {
		Genotype offspring = other;
		if (other instanceof PermutationGenotype) {
			int[] otherGenes = ((PermutationGenotype) other).genes;
			int[] offspringGenes = new int[this.genes.length];
			int offspringIndex = 0;
			int inputIndex = 0;
			int chosenFromThis = 0, chosenFromOther = 0;
			@SuppressWarnings("unchecked")
			HashSet<Integer> availableNumbers = (HashSet<Integer>) this.permutationSet.clone();
			while (availableNumbers.size() > 0) {
				double rand = Math.random();
				if (rand < crossoverProbability && availableNumbers.contains(genes[inputIndex])) {
					offspringGenes[offspringIndex] = genes[inputIndex];
					availableNumbers.remove(genes[inputIndex]);
					offspringIndex++;
				} else if (availableNumbers.contains(otherGenes[inputIndex])) {
					offspringGenes[offspringIndex] = otherGenes[inputIndex];
					availableNumbers.remove(otherGenes[inputIndex]);
					offspringIndex++;
				}
				inputIndex = (inputIndex+1)%this.genes.length;
				if(chosenFromThis == this.genes.length || chosenFromOther == this.genes.length){
					for (Integer elem : availableNumbers) {
						offspringGenes[offspringIndex] = elem;
						offspringIndex++;
					}
				//all available elements have been inserted
					availableNumbers.clear();
				}
			}
			offspring = new PermutationGenotype(this.genes.length);
			((PermutationGenotype) offspring).genes = offspringGenes;
		} else
			throw new Exception("Found a Genotype different from PermutationGenotype");
		return offspring;

	}

	/**
	 * The element with indexToSwap index in the array will be swapped with a
	 * random element in the same array
	 * 
	 * @param array
	 * @param indexToSwap
	 */
	private void swapRandomly(int[] array, int indexToSwap) {
		int swapPosition = (int) (Math.random() * (double)this.genes.length);
		swap(this.genes, indexToSwap, swapPosition);
	}
	/**
	 * @return the genes
	 */
	public int[] getGenes() {
		return genes;
	}
}
