package de.catchycube.sudoku.output;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import de.catchycube.sudoku.general.Sudoku;

public class FileOutput {
	public static void save(Sudoku sudoku, File file, boolean savePresetFlags){
		try {
			FileWriter writer = new FileWriter(file,false);
			for(int y = 0; y < 9; y++){
				for(int x = 0; x < 9; x++){
					writer.write((char)(sudoku.get(x, y)+48));
				}
				writer.write(System.getProperty("line.separator"));
			}
			if(savePresetFlags){
				for(int y = 0; y < 9; y++){
					for(int x = 0; x < 9; x++){
						writer.write(sudoku.isPreset(x, y)?'1':'0');
					}
					writer.write(System.getProperty("line.separator"));
				}
			}
			writer.flush();
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
