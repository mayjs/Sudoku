package de.catchycube.sudoku.gui.swing.applet;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.catchycube.sudoku.general.Sudoku;
import de.catchycube.sudoku.gui.swing.InputSudokuPanel;
import de.catchycube.sudoku.gui.swing.PlayFrame;
import de.catchycube.sudoku.gui.swing.language.Language;
import de.catchycube.sudoku.gui.swing.properties.Properties;

public class PlayPanel extends JPanel implements ActionListener{
	
	private final static String COMMAND_CHECK = "applet_playPanel_cmd_check";
	
	private SudokuApplet applet;
	
	private InputSudokuPanel panel;
	
	public PlayPanel(SudokuApplet applet){
		super();
		this.applet = applet;
		
		this.setLayout(new FlowLayout());
		
		panel = new InputSudokuPanel();
		panel.setPreferredSize(new Dimension(Integer.parseInt(Properties.getCurrent().getProperty("sudoku_render_width", "450")),
				Integer.parseInt(Properties.getCurrent().getProperty("sudoku_render_height", "450"))));
		this.add(panel);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		
		JButton btnCheck = new JButton(Language.getCurrent().getVocabulary(PlayFrame.KEY_CHECK));
		btnCheck.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCheck.setActionCommand(COMMAND_CHECK);
		btnCheck.addActionListener(this);
		btnPanel.add(btnCheck);
		
		this.add(btnPanel);
	}
	
	public void setSudoku(Sudoku sudoku){
		panel.setSudoku(sudoku);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(COMMAND_CHECK)){
			boolean valid = panel.getSudoku().isValid();
			JOptionPane.showMessageDialog(applet, 
					Language.getCurrent().getVocabulary(valid?PlayFrame.KEY_RIGHT_ANSWER:PlayFrame.KEY_WRONG_ANSWER));
			if(valid) applet.goTo(applet.getMainMenu());
		}		
	}
}
