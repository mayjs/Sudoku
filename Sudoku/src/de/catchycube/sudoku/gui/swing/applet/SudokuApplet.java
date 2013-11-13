package de.catchycube.sudoku.gui.swing.applet;

import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.catchycube.sudoku.gui.swing.language.Language;
import de.catchycube.sudoku.gui.swing.properties.Properties;

public class SudokuApplet extends JApplet{
	
	private JPanel centralPanel;
	
	private JPanel mainMenu, generatorSettings, playPanel;
	
	//This will be called when the applet is used (like a main method)
	public SudokuApplet(){
		super();
		
		//loading the lan/properties file
		try{
			Language l = Language.load(Language.class.getResourceAsStream("lan_ger"));
			Language.setCurrent(l);
			Properties p = Properties.load(Properties.class.getResourceAsStream("settings"));
			Properties.setCurrent(p);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		this.setLayout(new BorderLayout());
		initPanels();
		
		centralPanel = mainMenu;
		this.add(centralPanel,BorderLayout.CENTER);
		
		this.add(new JLabel("© 2013 by Johannes May"),BorderLayout.SOUTH);
	}
	
	public void goTo(JPanel panel){
		this.remove(centralPanel);
		centralPanel = panel;
		this.add(centralPanel,BorderLayout.CENTER);
		centralPanel.updateUI();
//		System.out.println("finished");
	}
	
	private void initPanels(){
		mainMenu = new MainMenuPanel(this);
		generatorSettings = new GeneratorSettingsPanel(this);
		playPanel = new PlayPanel(this);
	}

	public JPanel getMainMenu() {
		return mainMenu;
	}

	public JPanel getGeneratorSettings() {
		return generatorSettings;
	}
	
	public PlayPanel getPlayPanel(){
		return (PlayPanel)playPanel;
	}
}
