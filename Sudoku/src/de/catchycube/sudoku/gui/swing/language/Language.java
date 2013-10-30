package de.catchycube.sudoku.gui.swing.language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import de.catchycube.sudoku.gui.swing.MainWindow;
import de.catchycube.sudoku.gui.swing.PlayFrame;

public class Language {
	private Map<String,String> voc = new HashMap<String,String>();
	
	public Language(Map<String,String> voc){
		this.voc = voc;
	}
	
	public static Language load(InputStream stream){
		HashMap<String,String> vocs = new HashMap<String,String>();

		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader bReader = new BufferedReader(reader);
		
		String line = "";
		try {
			while((line = bReader.readLine())!=null){
				String[] parts = line.split("=");
				if(parts.length == 2){
					vocs.put(parts[0], parts[1]);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Language(vocs);
	}
	
	public static Language getDefault(){
		HashMap<String,String> vocs = new HashMap<String,String>();
		vocs.put(PlayFrame.KEY_TITLE, "SUDOKU");
		vocs.put(PlayFrame.KEY_SAVE, "Speichern");
		vocs.put(PlayFrame.KEY_CHECK, "Prüfen");
		vocs.put(PlayFrame.KEY_RIGHT_ANSWER, "Diese Lösung ist richtig!");
		vocs.put(PlayFrame.KEY_WRONG_ANSWER, "Diese Lösung ist leider falsch.");
		vocs.put(PlayFrame.KEY_FILTER_TXT, "Text-Dateien");
		vocs.put(PlayFrame.KEY_SAVE_GAME, "Spiel speichern unter...");
		
		vocs.put(MainWindow.KEY_CONTINUE_GAME, "Spiel laden");
		vocs.put(MainWindow.KEY_GENERATE, "Sudoku erstellen");
		vocs.put(MainWindow.KEY_LOADTITLE, "Spiel laden..");
		vocs.put(MainWindow.KEY_START_GAME, "Neues Spiel");
		vocs.put(MainWindow.KEY_TITLE, "Hauptmenü");
		return new Language(vocs);
	}
	
	private static Language current;
	public static Language getCurrent(){
		return current;
	}
	public static void setCurrent(Language language){
		current = language;
	}
	
	public String getVocabulary(String key){
		if(!voc.containsKey(key)) return key;
		return voc.get(key);
	}
	
	static{
		current = getDefault();
	}
}
