package de.catchycube.sudoku.gui.swing;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.catchycube.sudoku.general.Sudoku;
import de.catchycube.sudoku.gui.swing.language.Language;
import de.catchycube.sudoku.output.FileOutput;

public class PlayFrame extends JFrame implements ActionListener{
	
	public final static String KEY_CHECK="playFrame_btnCheck",
								KEY_SAVE ="playFrame_btnSave",
								KEY_TITLE="playFrame_title",
								KEY_WRONG_ANSWER="playFrame_wrongAnswer",
								KEY_RIGHT_ANSWER="playFrame_rightAnswer",
								KEY_FILTER_TXT="playFrame_txtFilter",
								KEY_SAVE_GAME="playFrame_saveGame";
	
	private final static String COMMAND_CHECK="playFrame_cmd_check",
								COMMAND_SAVE="playFrame_cmd_save";
	
	private InputSudokuPanel panel;
	
	public PlayFrame(Sudoku sudoku){
		super(Language.getCurrent().getVocabulary(KEY_TITLE));
		panel = new InputSudokuPanel();
		panel.setSudoku(sudoku);
		panel.setPreferredSize(new Dimension(450,450));
		
		JButton btnCheck = new JButton(Language.getCurrent().getVocabulary(KEY_CHECK));
		btnCheck.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnCheck.addActionListener(this);
		btnCheck.setActionCommand(COMMAND_CHECK);
		
		JButton btnSave = new JButton(Language.getCurrent().getVocabulary(KEY_SAVE));
		btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSave.addActionListener(this);
		btnSave.setActionCommand(COMMAND_SAVE);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		btnPanel.add(btnCheck);
		btnPanel.add(btnSave);
		
		this.setLayout(new FlowLayout());
		this.add(panel);
		this.add(btnPanel);
		
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(COMMAND_CHECK)){
			JOptionPane.showMessageDialog(this, 
				Language.getCurrent().getVocabulary(panel.getSudoku().isValid()?KEY_RIGHT_ANSWER:KEY_WRONG_ANSWER));
		}
		else if(arg0.getActionCommand().equals(COMMAND_SAVE)){
			JFileChooser chooser = new JFileChooser(/*new File(System.getProperty("user.home"))*/);
			chooser.setDialogType(JFileChooser.SAVE_DIALOG);
			
			chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
			
			FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(Language.getCurrent().getVocabulary(KEY_FILTER_TXT), "txt");
			
			chooser.addChoosableFileFilter(txtFilter);
			
			chooser.setDialogTitle(Language.getCurrent().getVocabulary(KEY_SAVE_GAME));
			
			int result = chooser.showSaveDialog(this);
			
			if(result == JFileChooser.APPROVE_OPTION){
				FileOutput.save(panel.getSudoku(), chooser.getSelectedFile(), true);
			}
		}
	}
}
