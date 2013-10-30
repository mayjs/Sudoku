package de.catchycube.sudoku.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import de.catchycube.sudoku.general.Sudoku;

public class Loader {
	public static Sudoku load(String path){
		return load(new File(path));
	}
	
	public static Sudoku load(File file){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			List<String> lines = new LinkedList<String>();
			String s = null;
			try {
				while((s=reader.readLine())!=null){
					lines.add(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			reader.close();
			String[] fileArr = new String[lines.size()];
			for(int i = 0; i < lines.size(); i++){
				fileArr[i] = lines.get(i);
			}
			return Sudoku.parse(fileArr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}
}
