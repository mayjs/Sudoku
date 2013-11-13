package de.catchycube.sudoku.gui.swing.applet;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.catchycube.sudoku.gui.swing.MainWindow;
import de.catchycube.sudoku.gui.swing.language.Language;

public class MainMenuPanel extends JPanel implements ActionListener{
	
	private static final String COMMAND_START_GAME = "mainMenuPanel_command_startNewGame";
	
	private SudokuApplet applet;
	
	public MainMenuPanel(SudokuApplet applet){
		super();
		
		this.applet = applet;
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		
		JButton btnNewGame = new JButton(Language.getCurrent().getVocabulary(MainWindow.KEY_START_GAME));
		btnNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewGame.setActionCommand(COMMAND_START_GAME);
		btnNewGame.addActionListener(this);
		
		this.add(btnNewGame);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(COMMAND_START_GAME)){
			((GeneratorSettingsPanel)applet.getGeneratorSettings()).setTarget(GeneratorSettingsPanel.TARGET_ACTION_PLAY);
			applet.goTo(applet.getGeneratorSettings());
		}
		
	}
}
