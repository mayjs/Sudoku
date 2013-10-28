package de.catchycube.sudoku.output;

import java.io.PrintStream;

import de.catchycube.sudoku.general.Sudoku;


public class StreamOutput {
	public static void output(PrintStream stream,Sudoku sudoku){
		for(int y = 0; y < 9; y++){
			for(int x = 0; x < 9; x++){
				stream.print(sudoku.get(x, y));
			}
			stream.println();
		}
	}
}
