import de.catchycube.sudoku.general.Sudoku;
import de.catchycube.sudoku.input.Loader;
import de.catchycube.sudoku.output.GraphicsOutput;
import de.catchycube.sudoku.output.StreamOutput;
import de.catchycube.sudoku.solver.Solver;


public class Test {
	public static void main(String[] args){
		Sudoku su = Loader.load("D:\\sudoku.txt");
		StreamOutput.output(System.out, su);
		System.out.println("~~~~~~~~~~~~~");
		Sudoku solved = new Solver().solve(su);
		StreamOutput.output(System.out, solved);
		
		GraphicsOutput.save(solved, "C:\\Users\\Kemiren\\Pictures\\solved.jpg", "jpg", 900, 900);
	}
}
