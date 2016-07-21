package realtest;

import java.util.ArrayList;

import algorithm.GeneticAlgorithm;
import algorithm.Genotype;
import algorithm.Population;
import genotypes.RealGenotype;
import selection.RandomSelection;

public class GAFrameworkRealTest {

	public static void main(String[] args) {
		int populationSize = 10;
		int numberOfPopulations = 1;
		int numberOfGenes = 8;
		double targetFitness = 0.1;
		GeneticAlgorithm<RealGenotype> algorithm;
		ArrayList<Population<RealGenotype>> populations = new ArrayList<Population<RealGenotype>>();
		
		for (int i = 0; i < numberOfPopulations; i++) {		
			RealGenotype[] genotypes = new RealGenotype[populationSize];
			for (int j = 0; j < populationSize; j++) {
				genotypes[j] = new RealGenotype(numberOfGenes);
			}
			populations.add(new Population<RealGenotype>(genotypes,new RandomSelection(),new RealFunctionFitness()));
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
			double [] genes = ((RealGenotype)solution).getGenes();
			for (int i = 0; i < genes.length; i++) {
				System.out.print(genes[i]+" ");
			}

		}
		System.out.println("Elapsed time (milliseconds): "+(endTime - startTime));
		
	}
	
}
