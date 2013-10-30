package de.catchycube.sudoku.gui.swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import de.catchycube.sudoku.general.Sudoku;
import de.catchycube.sudoku.generation.Generator;
import de.catchycube.sudoku.gui.swing.language.Language;

public class GeneratorDialog extends JFrame implements ActionListener{
	
	public final static String KEY_DEFAULT_TITLE = "generatorDialog_defaultTitle",
								KEY_MAXIMAL_REMOVAL_STEPS_LABEL="generatorDialog_maximalRemovalSteps",
								KEY_MAXIMAL_BRUTEFORCE_DEPTH_LABEL="generatorDialog_maximalBruteForceDepth",
								KEY_GENERATE = "generatorDialog_generate",
								KEY_ABORT = "generatorDialog_abort",
								KEY_ERROR_INVALIDINPUT_TITLE ="generatorDialog_invalidInput_title",
								KEY_ERROR_INVALIDINPUT_MESSAGE = "generatorDialog_invalidInput_message";
	
	public final static int RESULT_GENERATE = 1,
							RESULT_ABORT = 2,
							RESULT_NONE = 0;
	
	private final static String COMMAND_GENERATE = "generatorDialog_cmd_generate",
								COMMAND_ABORT = "generatorDialog_cmd_abort";
	
	private int currentResult = RESULT_NONE;
	private Generator generator;
	
	private JFormattedTextField maximalRemovalStepsField, maximalBruteforceDepthField;
	private JButton btnGenerate, btnAbort;
	private JLabel maximalRemovalStepsLabel, maximalBruteforceDepthLabel;
	
	public GeneratorDialog(){
		super(Language.getCurrent().getVocabulary(KEY_DEFAULT_TITLE));
		
		maximalRemovalStepsLabel = new JLabel(Language.getCurrent().getVocabulary(KEY_MAXIMAL_REMOVAL_STEPS_LABEL));
		maximalBruteforceDepthLabel = new JLabel(Language.getCurrent().getVocabulary(KEY_MAXIMAL_BRUTEFORCE_DEPTH_LABEL));
		
		maximalRemovalStepsField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		maximalRemovalStepsField.setText(""+40);
		maximalBruteforceDepthField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		maximalBruteforceDepthField.setText(""+0);
		
		btnGenerate = new JButton(Language.getCurrent().getVocabulary(KEY_GENERATE));
		btnGenerate.setActionCommand(COMMAND_GENERATE);
		btnGenerate.addActionListener(this);
		btnAbort = new JButton(Language.getCurrent().getVocabulary(KEY_ABORT));
		btnAbort.setActionCommand(COMMAND_ABORT);
		btnAbort.addActionListener(this);
		
		this.setLayout(new GridLayout(3,3));
		this.add(maximalRemovalStepsLabel); this.add(maximalRemovalStepsField);
		this.add(maximalBruteforceDepthLabel); this.add(maximalBruteforceDepthField);
		this.add(btnAbort); this.add(btnGenerate);
		this.pack();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
	
	public int getResult(){
		return currentResult;
	}
	
	public Sudoku getSudoku(){
		return generator.getSudoku();
	}
	public Sudoku getSolution(){
		return generator.getSolution();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals(COMMAND_ABORT)){
			currentResult = RESULT_ABORT;
			dispose();
		} else if(arg0.getActionCommand().equals(COMMAND_GENERATE)){
			generator = new Generator();
			Exception exception = null;
			try{
				generator.setMaximalRemovalSteps(Integer.parseInt(maximalRemovalStepsField.getText()));
				generator.setMaximalSolvingDepth(Integer.parseInt(maximalBruteforceDepthField.getText()));
			} catch(Exception ex){
				exception = ex;
				JOptionPane.showMessageDialog(this, Language.getCurrent().getVocabulary(KEY_ERROR_INVALIDINPUT_MESSAGE), 
						Language.getCurrent().getVocabulary(KEY_ERROR_INVALIDINPUT_TITLE), JOptionPane.ERROR_MESSAGE);
			}
			if(exception == null){
				generator.generateRandomSudoku();
				currentResult=RESULT_GENERATE;
				dispose();
			}
		}
	}
	
	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
		if(visible) currentResult = RESULT_NONE;
	}
}
