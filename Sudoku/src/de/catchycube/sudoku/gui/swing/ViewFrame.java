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
import de.catchycube.sudoku.gui.swing.properties.Properties;
import de.catchycube.sudoku.output.FileOutput;
import de.catchycube.sudoku.output.GraphicsOutput;

public class ViewFrame extends JFrame implements ActionListener{

	public final static String KEY_DEFAULT_TITLE="viewFrame_defaultTitle",
								KEY_SHOW_SOLUTION="viewFrame_showSolution",
								KEY_SHOW_SUDOKU="viewFrame_showSudoku",
								KEY_SAVE="viewFrame_save",
								KEY_SAVE_SOLUTION="viewFrame_saveSolution",
								KEY_EXTENSION_TXT="viewFrame_extension_txt",
								KEY_EXTENSION_JPG="viewFrame_extension_jpg",
								KEY_SELECT_RESULUTION="viewFrame_selectResolution";
	
	private final static String COMMAND_SAVE="viewFrame_cmd_save",
						 		COMMAND_SWITCH="viewFrame_cmd_switch";
	
	private SudokuPanel panel;
	private Sudoku riddle, solution;
	private JButton btnSave, btnSwitch;
	
	public ViewFrame(Sudoku riddle, Sudoku solution){
		super(Language.getCurrent().getVocabulary(KEY_DEFAULT_TITLE));
		this.riddle = riddle;
		this.solution = solution;
		
		panel = new SudokuPanel();
		panel.setSudoku(riddle);
		panel.setPreferredSize(new Dimension(Integer.parseInt(Properties.getCurrent().getProperty("sudoku_render_width", "450")),
				Integer.parseInt(Properties.getCurrent().getProperty("sudoku_render_height", "450"))));
		
		btnSwitch = new JButton(Language.getCurrent().getVocabulary(KEY_SHOW_SOLUTION));
		btnSwitch.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSwitch.setActionCommand(COMMAND_SWITCH);
		btnSwitch.addActionListener(this);
		
		btnSave = new JButton(Language.getCurrent().getVocabulary(KEY_SAVE));
		btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSave.setActionCommand(COMMAND_SAVE);
		btnSave.addActionListener(this);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
		btnPanel.add(btnSwitch);
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
		if(arg0.getActionCommand().equals(COMMAND_SWITCH)){
			if(panel.getSudoku() == riddle){
				panel.setSudoku(solution);
				btnSwitch.setText(Language.getCurrent().getVocabulary(KEY_SHOW_SUDOKU));
			} else{
				panel.setSudoku(riddle);
				btnSwitch.setText(Language.getCurrent().getVocabulary(KEY_SHOW_SOLUTION));
			}
		}
		else if(arg0.getActionCommand().equals(COMMAND_SAVE)){
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogType(JFileChooser.SAVE_DIALOG);
			
			chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
			
			FileNameExtensionFilter txtFiler = new FileNameExtensionFilter(Language.getCurrent().getVocabulary(KEY_EXTENSION_TXT), "txt");
			FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(Language.getCurrent().getVocabulary(KEY_EXTENSION_JPG), "jpg");
			
			chooser.addChoosableFileFilter(txtFiler);
			chooser.addChoosableFileFilter(jpgFilter);
			
			int result = chooser.showSaveDialog(this);
			if(result == JFileChooser.APPROVE_OPTION){
				
				boolean saveSolution = JOptionPane.showConfirmDialog(this, Language.getCurrent().getVocabulary(KEY_SAVE_SOLUTION), "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
				
				File file = chooser.getSelectedFile();
				String path = file.getAbsolutePath();
				String pathWithoutExt = file.getAbsolutePath();
				String wishedExt = chooser.getFileFilter().equals(txtFiler)?".txt":".jpg";
				if(!path.endsWith(wishedExt)){
					file = new File(path+wishedExt);
				} else{
					pathWithoutExt = path.substring(0,path.length()-4);
				}
				File solutionFile = new File(pathWithoutExt + "_solution"+wishedExt);
				if(chooser.getFileFilter().equals(txtFiler)){
					FileOutput.save(riddle, file, false);
					if(saveSolution) FileOutput.save(solution, solutionFile, false);
				}
				else if(chooser.getFileFilter().equals(jpgFilter)){
					Object res1 = "225x225"; Object res2="450x450"; Object res3="900x900";
					Object[] all = new Object[]{res1,res2,res3};
					result = JOptionPane.showOptionDialog(this, Language.getCurrent().getVocabulary(KEY_SELECT_RESULUTION), "", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, all, res2);
					if(result!=JOptionPane.CLOSED_OPTION){
						int resulution = all[result]==res1?225:all[result]==res2?450:900;
						GraphicsOutput.save(riddle, file, "jpg", resulution, resulution);
						if(saveSolution) GraphicsOutput.save(solution, solutionFile, "jpg", resulution, resulution);
					}
				}
			}
		}
	}

}
