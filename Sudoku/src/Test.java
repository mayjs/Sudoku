import de.catchycube.sudoku.generation.Generator;
import de.catchycube.sudoku.output.GraphicsOutput;


public class Test {
	public static void main(String[] args){
//		Sudoku su = Loader.load("D:\\sudoku.txt");
//		StreamOutput.output(System.out, su);
//		System.out.println("~~~~~~~~~~~~~");
//		Sudoku solved = new Solver().solve(su);
//		StreamOutput.output(System.out, solved);
//		
//		GraphicsOutput.save(solved, "C:\\Users\\Kemiren\\Pictures\\solved.jpg", "jpg", 900, 900);
		Generator g = new Generator();
		g.generateRandomSudoku();
		System.out.println(g.getSolution().isValid());
		GraphicsOutput.save(g.getSolution(), "C:\\Users\\Kemiren\\Pictures\\solved.jpg", "jpg", 900, 900);
		GraphicsOutput.save(g.getSudoku(), "C:\\Users\\Kemiren\\Pictures\\riddle.jpg", "jpg", 900, 900);
	}
}
