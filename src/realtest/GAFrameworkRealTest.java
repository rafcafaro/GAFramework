package realtest;

import java.util.ArrayList;

import algorithm.GeneticAlgorithm;
import algorithm.Genotype;
import algorithm.Population;
import genotypes.RealGenotype;
import selection.*;

public class GAFrameworkRealTest {

	public static void main(String[] args) {
		int populationSize = 20;
		int numberOfPopulations = 1;
<<<<<<< Updated upstream
		int numberOfGenes = 10;
		double targetFitness = 0.0000001;
=======
		int numberOfGenes = 4;
		double targetFitness = 0.0001;
>>>>>>> Stashed changes
		boolean isDebugActive = true;
		long debugPrintGenerations = 100000L;

		GeneticAlgorithm<RealGenotype> algorithm;
		ArrayList<Population<RealGenotype>> populations = new ArrayList<Population<RealGenotype>>();
		
		for (int i = 0; i < numberOfPopulations; i++) {		
			RealGenotype[] genotypes = new RealGenotype[populationSize];
			for (int j = 0; j < populationSize; j++) {
				genotypes[j] = new RealGenotype(numberOfGenes);
			}
			populations.add(new Population<RealGenotype>(genotypes, new RouletteWheelSelection(),new RealFunctionFitness()));
		}
		long startTime=0,endTime = 0;
		algorithm = new GeneticAlgorithm<>(populations, targetFitness);
		algorithm.setDebug(isDebugActive, debugPrintGenerations);
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
			System.out.println();
		}
		System.out.println("Elapsed time (milliseconds): "+(endTime - startTime));
		
	}
	
}
