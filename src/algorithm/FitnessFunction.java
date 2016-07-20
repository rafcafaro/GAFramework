package algorithm;

public interface FitnessFunction<T extends Genotype> {
	double calculateFitness(T genotype);
}
