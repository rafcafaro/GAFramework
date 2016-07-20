package eigthqueentest;

import algorithm.FitnessFunction;
import genotypes.PermutationGenotype;

public class EightQueenFitness implements FitnessFunction<PermutationGenotype> {

	private int checkBoardSide;
	@Override
	public double calculateFitness(PermutationGenotype genotype) {
		int[] genes = genotype.getGenes().clone();
		this.checkBoardSide = genes.length;
		//change representation from 1 to n 
		//each index is a row, each value a column where a queen is placed
		//on the checkboead
		for (int i = 0; i < genes.length; i++) {
			genes[i]++;
		}
		return getStateCost(genes);
	}
	
	private boolean isQueenAttacked(int queenRow, int queenCol, int[] checkboardCols) {
		// for each row
		for (int i = 0; i < checkBoardSide; i++) { // check that in other rows
													// (i) there aren't queens
													// in the same column
													// (for problem definition,
													// two queens can't occupy
													// the same row)
			if (queenCol == checkboardCols[i] && queenRow != i)
				return true;
			// check principal diagonal
			int columnToCheck = queenCol - (queenRow - i);
			if (columnToCheck >= 0 && columnToCheck < checkBoardSide && i != queenRow
					&& checkboardCols[i] == columnToCheck)
				return true;
			// check other diagonal
			columnToCheck = queenCol + (queenRow - i);
			if (columnToCheck >= 0 && columnToCheck < checkBoardSide && i != queenRow
					&& checkboardCols[i] == columnToCheck)
				return true;

		}
		return false;

	}
	
	/**
	 * count number of queens that attack each other
	 * 
	 * @param state
	 * @return
	 */
	private float getStateCost(int[] board) {
		float cost = 0;
		for (int i = 0; i < this.checkBoardSide; i++) {
			if (isQueenAttacked(i, board[i], board)) {
				cost++;
				// System.out.println("row: " + i + " attacked");
			}
		}
		return cost;

	}
}
