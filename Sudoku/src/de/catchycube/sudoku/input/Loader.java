package de.catchycube.sudoku.input;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.catchycube.sudoku.general.Sudoku;

public class Loader {
	public static Sudoku load(String path){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			List<String> file = new LinkedList<String>();
			String s = null;
			try {
				while((s=reader.readLine())!=null){
					file.add(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reader.close();
			String[] fileArr = new String[9];
			for(int i = 0; i < 9; i++){
				fileArr[i] = file.get(i);
			}
			return Sudoku.parse(fileArr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
}
