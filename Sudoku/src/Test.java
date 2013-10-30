import java.awt.Dimension;

import javax.swing.JFrame;

import de.catchycube.sudoku.generation.Generator;
import de.catchycube.sudoku.gui.swing.InputSudokuPanel;
import de.catchycube.sudoku.gui.swing.PlayFrame;


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
//		System.out.println(g.getSolution().isValid());
//		GraphicsOutput.save(g.getSolution(), "C:\\Users\\Kemiren\\Pictures\\solved.jpg", "jpg", 900, 900);
//		GraphicsOutput.save(g.getSudoku(), "C:\\Users\\Kemiren\\Pictures\\riddle.jpg", "jpg", 900, 900);
//		JFrame frame = new JFrame("Sudoku test");
//		InputSudokuPanel panel = new InputSudokuPanel();
//		panel.setSize(450, 450); panel.setPreferredSize(new Dimension(450,450));
//		panel.setSudoku(g.getSudoku());
//		
//		frame.add(panel);
//		frame.pack();
//		//frame.setSize(450, 450);
//		frame.setVisible(true);
		new PlayFrame(g.getSudoku());
		JFrame frame = new JFrame("Titel");
		frame.pack();
		
	}
}
