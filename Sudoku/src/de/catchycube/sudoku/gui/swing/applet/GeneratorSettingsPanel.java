package de.catchycube.sudoku.gui.swing.applet;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.catchycube.sudoku.generation.Generator;
import de.catchycube.sudoku.gui.swing.GeneratorDialog;
import de.catchycube.sudoku.gui.swing.language.Language;
import de.catchycube.sudoku.gui.swing.properties.Properties;

public class GeneratorSettingsPanel extends JPanel implements ActionListener{
	
	private SudokuApplet applet;
	
	public static final String KEY_EASY="applet_generatorSettings_easy",
								KEY_AVERAGE="applet_generatorSettings_average",
								KEY_HARD="applet_generatorSettings_hard",
								KEY_SELECT_DIFFICULTY="applet_generatorSettings_selectDifficulty";
	
	public static final String PROP_EASY="applet_generator_easy_remove_Steps",
								PROP_AVERAGE="applet_generator_average_remove_Steps",
								PROP_HARD="applet_generator_average_remove_Steps";
	
	public static final String TARGET_ACTION_PLAY="applet_target_play";
	
	private static final String COMMAND_ABORT="applet_generatorSettingsPanel_cmd_abort";
	
	private String target;
	
	public GeneratorSettingsPanel(SudokuApplet applet){
		super();
		this.applet = applet;
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		JLabel label = new JLabel(Language.getCurrent().getVocabulary(KEY_SELECT_DIFFICULTY));
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(label);
		
		JButton btnEasy = new JButton(Language.getCurrent().getVocabulary(KEY_EASY));
		btnEasy.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnEasy.setActionCommand(PROP_EASY);
		btnEasy.addActionListener(this);
		this.add(btnEasy);
		
		JButton btnAv = new JButton(Language.getCurrent().getVocabulary(KEY_AVERAGE));
		btnAv.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAv.setActionCommand(PROP_AVERAGE);
		btnAv.addActionListener(this);
		this.add(btnAv);
		
		JButton btnHard = new JButton(Language.getCurrent().getVocabulary(KEY_HARD));
		btnHard.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnHard.setActionCommand(PROP_HARD);
		btnHard.addActionListener(this);
		this.add(btnHard);
		
		JButton btnAbort = new JButton(Language.getCurrent().getVocabulary(GeneratorDialog.KEY_ABORT));
		btnAbort.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAbort.setActionCommand(COMMAND_ABORT);
		btnAbort.addActionListener(this);
		this.add(btnAbort);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(COMMAND_ABORT)){
			applet.goTo(applet.getMainMenu());
		}
		else{
			Generator g = new Generator();
			g.setMaximalRemovalSteps(Integer.parseInt(Properties.getCurrent().getProperty(e.getActionCommand(), "0")));
			g.generateRandomSudoku();
			if(target.equals(TARGET_ACTION_PLAY)){
				applet.getPlayPanel().setSudoku(g.getSudoku());
				applet.goTo(applet.getPlayPanel());
			}
		}
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
