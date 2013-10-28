import de.catchycube.sudoku.general.Sudoku;
import de.catchycube.sudoku.input.Loader;
import de.catchycube.sudoku.output.StreamOutput;
import de.catchycube.sudoku.solver.Solver;


public class Test {
	public static void main(String[] args){
		Sudoku su = Loader.load("C:\\sudoku.txt");
		StreamOutput.output(System.out, su);
		System.out.println("~~~~~~~~~~~~~");
		Sudoku solved = new Solver().solve(su);
		StreamOutput.output(System.out, solved);
	}
}
