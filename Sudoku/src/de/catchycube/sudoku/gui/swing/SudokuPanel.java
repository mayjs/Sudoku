package de.catchycube.sudoku.gui.swing;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import de.catchycube.sudoku.general.Sudoku;
import de.catchycube.sudoku.output.GraphicsOutput;

public class SudokuPanel extends JPanel{
	protected Sudoku sudoku;
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setFont(new Font(Font.SERIF,Font.PLAIN,50));
		GraphicsOutput.render(sudoku, (Graphics2D)g, getWidth(), getHeight());
	}

	public Sudoku getSudoku() {
		return sudoku;
	}

	public void setSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
		this.updateUI();
	}
}
