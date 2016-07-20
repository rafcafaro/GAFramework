package eigthqueentest;

import java.util.ArrayList;

import algorithm.GeneticAlgorithm;
import algorithm.Genotype;
import algorithm.Population;
import genotypes.PermutationGenotype;
import selection.RandomSelection;

public class GAFrameworkApplication {

	public static void main(String[] args) {
		int populationSize = 10;
		int numberOfPopulations = 1;
		int numberOfGenes = 8;
		double targetFitness = 0;
		GeneticAlgorithm<PermutationGenotype> algorithm;
		ArrayList<Population<PermutationGenotype>> populations = new ArrayList<Population<PermutationGenotype>>();
		
		for (int i = 0; i < numberOfPopulations; i++) {		
			PermutationGenotype[] genotypes = new PermutationGenotype[populationSize];
			for (int j = 0; j < populationSize; j++) {
				genotypes[j] = new PermutationGenotype(numberOfGenes);
			}
			populations.add(new Population<PermutationGenotype>(genotypes,new RandomSelection(),new EightQueenFitness()));
		}
		long startTime=0,endTime = 0;
		algorithm = new GeneticAlgorithm<>(populations, targetFitness);
		algorithm.start();
		startTime = System.currentTimeMillis();
		try {
			algorithm.join();
			endTime = System.currentTimeMillis();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Genotype solution = algorithm.getBestSolutionFound();
		if(solution != null)
		{
			int [] genes = ((PermutationGenotype)solution).getGenes();
			for (int i = 0; i < genes.length; i++) {
				System.out.print(genes[i]+" ");
			}

		}
		System.out.println("Elapsed time (milliseconds): "+(endTime - startTime));
		
	}
	
}
