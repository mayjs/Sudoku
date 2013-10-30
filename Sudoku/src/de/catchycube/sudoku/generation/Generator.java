package de.catchycube.sudoku.generation;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import de.catchycube.sudoku.general.Sudoku;
import de.catchycube.sudoku.solver.Solver;

public class Generator {
	private int maximalSolvingDepth = 0;
	private int maximalRemovalSteps = 81;
	private int initialRemovalSteps = 0;
	
	private Sudoku generatedSudoku;
	private Sudoku generatedSolution;
	private int removalSteps;
	
	private Sudoku generateRandomPermutation(){
		Sudoku ret = new Sudoku();
		
		for(int i = 0; i < 9; i++){
			List<Byte> numbers = new LinkedList<Byte>();
			for(byte n = 1; n < 10; n++) numbers.add(n);
			int counter = 0;
			for(;!numbers.isEmpty() && counter < 200;counter++){
				Collections.shuffle(numbers);
				if(ret.isValid(9 - numbers.size(), i, numbers.get(0))){
					ret.preSetNumber(9 - numbers.size(), i, numbers.get(0));
					numbers.remove(0);
				}
			}
			if(counter == 200){
				for(int x = 0; x < 9; x++) ret.clear(x, i);
				i--;
			}
		}
		
		return ret;
	}
	
	private Sudoku removeAtRandomPoint(Sudoku sudoku){
		Sudoku ret = sudoku.clone();
		while(!ret.clear((int)(Math.random()*9f), (int)(Math.random()*9f))){}
		return ret;
	}
	
	public void generateRandomSudoku(){
		generatedSolution = this.generateRandomPermutation();
		Solver solver = new Solver();
		solver.setMaximalBruteForceDepth(maximalSolvingDepth);
		LinkedList<Sudoku> generated = new LinkedList<Sudoku>();
		generated.addLast(generatedSolution);
		
		while(solver.solve(generated.getLast())!=null && generated.size() <= maximalRemovalSteps){
			generated.addLast(removeAtRandomPoint(generated.getLast()));
		}

		generated.removeLast();
		for(int x = 0; x < 9 && generated.size() < maximalRemovalSteps; x++){
			for(int y = 0; y < 9 && generated.size() < maximalRemovalSteps; y++){
				Sudoku newS = generated.getLast().clone();
				if(newS.clear(x, y)){
					if(solver.solve(newS) != null){
						generated.addLast(newS);
					}
				}
			}
		}
		removalSteps = generated.size();
		generatedSudoku = generated.get(generated.size() - 1);
	}
	
	public Sudoku getSudoku(){
		return generatedSudoku;
	}
	public Sudoku getSolution(){
		return generatedSolution;
	}

	public int getMaximalSolvingDepth() {
		return maximalSolvingDepth;
	}

	public void setMaximalSolvingDepth(int maximalSolvingDepth) {
		this.maximalSolvingDepth = maximalSolvingDepth;
	}

	public int getMaximalRemovalSteps() {
		return maximalRemovalSteps;
	}

	public void setMaximalRemovalSteps(int maximalRemovalSteps) {
		this.maximalRemovalSteps = maximalRemovalSteps;
	}

	public int getInitialRemovalSteps() {
		return initialRemovalSteps;
	}

	public void setInitialRemovalSteps(int initialRemovalSteps) {
		this.initialRemovalSteps = initialRemovalSteps;
	}
	
	public int getRemovalSteps(){
		return removalSteps;
	}
}
