package de.catchycube.sudoku.gui.swing.properties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import de.catchycube.sudoku.gui.swing.language.Language;

public class Properties {
	Map<String,String> props;
	
	public Properties(Map<String,String> props){
		this.props = props;
	}
	
	public static Properties load(InputStream stream){
		HashMap<String,String> properties = new HashMap<String,String>();
		
		InputStreamReader reader = new InputStreamReader(stream);
		BufferedReader bReader = new BufferedReader(reader);
		
		String line = "";
		try {
			while((line = bReader.readLine())!=null){
				String[] parts = line.split("=");
				if(parts.length == 2){
					properties.put(parts[0], parts[1]);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Properties(properties);
	}
	
	private static Properties current;
	
	public static Properties getCurrent(){
		return current;
	}
	
	public static void setCurrent(Properties newProperties){
		current = newProperties;
	}
	
	public String getProperty(String key,String defaultValue){
		if(!props.containsKey(key)) return defaultValue;
		return props.get(key);
	}
	
	public static Properties getDefault(){
		HashMap<String,String> properties = new HashMap<String,String>();
		properties.put("sudoku_render_width", "450");
		properties.put("sudoku_render_height", "450");
		return new Properties(properties);
	}
	
	static{
		current = getDefault();
	}
}
