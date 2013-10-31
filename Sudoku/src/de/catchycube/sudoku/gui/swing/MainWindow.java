package de.catchycube.sudoku.gui.swing;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;

import de.catchycube.sudoku.generation.Generator;
import de.catchycube.sudoku.gui.swing.language.Language;
import de.catchycube.sudoku.gui.swing.properties.Properties;
import de.catchycube.sudoku.input.Loader;

public class MainWindow extends JFrame implements ActionListener, WindowListener{
	
	public final static String KEY_TITLE = "mainWindow_title",
								KEY_START_GAME = "mainWindow_startNewGame",
								KEY_CONTINUE_GAME = "mainWindow_continueGame",
								KEY_GENERATE = "mainWindow_generate",
								KEY_LOADTITLE="mainWindow_loadTitle";
	
	private final static String COMMAND_START_NEW_GAME = "mainWindow_cmd_new",
								COMMAND_CONTINUE_GAME = "mainWindow_cmd_continue",
								COMMAND_GENERATE = "mainWindow_cmd_generate",
								REQUEST_GENERATE = "mainWindow_req_generate",
								REQUEST_PLAY = "mainWindow_req_play",
								REQUEST_NONE = "mainWindow_req_none";
	
	private GeneratorDialog generator;
	private String currentGeneratorRequest = REQUEST_NONE;
	
	public MainWindow(){
		super(Language.getCurrent().getVocabulary(KEY_TITLE));
		
		generator = new GeneratorDialog();
		generator.addWindowListener(this);
		
		JButton btnNew = new JButton(Language.getCurrent().getVocabulary(KEY_START_GAME));
		btnNew.setActionCommand(COMMAND_START_NEW_GAME);
		btnNew.addActionListener(this);
		
		JButton btnContinue = new JButton(Language.getCurrent().getVocabulary(KEY_CONTINUE_GAME));
		btnContinue.setActionCommand(COMMAND_CONTINUE_GAME);
		btnContinue.addActionListener(this);
		
		JButton btnGenerate = new JButton(Language.getCurrent().getVocabulary(KEY_GENERATE));
		btnGenerate.setActionCommand(COMMAND_GENERATE);
		btnGenerate.addActionListener(this);
		
		this.setLayout(new GridLayout(0,1));
		this.add(btnNew);
		this.add(btnContinue);
		this.add(btnGenerate);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(COMMAND_START_NEW_GAME)){
//			Generator g = new Generator();
//			g.generateRandomSudoku();
//			new PlayFrame(g.getSudoku());
			currentGeneratorRequest = REQUEST_PLAY;
			generator.setVisible(true);
		}
		else if(e.getActionCommand().equals(COMMAND_CONTINUE_GAME)){
			JFileChooser chooser = new JFileChooser();
			chooser.setDialogType(JFileChooser.OPEN_DIALOG);
			chooser.setDialogTitle(Language.getCurrent().getVocabulary(KEY_LOADTITLE));
			int result = chooser.showOpenDialog(this);
			if(result == JFileChooser.APPROVE_OPTION){
				new PlayFrame(Loader.load(chooser.getSelectedFile()));
			}
		}
		else if(e.getActionCommand().equals(COMMAND_GENERATE)){
			currentGeneratorRequest = REQUEST_GENERATE;
			generator.setVisible(true);
		}
	}
	
	public static void main(String[] args){
		try{
			Language l = Language.load(Language.class.getResourceAsStream("lan_ger"));
			Language.setCurrent(l);
			Properties p = Properties.load(Properties.class.getResourceAsStream("settings"));
			p.setCurrent(p);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		try{
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception ex){
			ex.printStackTrace();
		}
		new MainWindow();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		if(arg0.getWindow() == generator && generator.getResult() == GeneratorDialog.RESULT_GENERATE){
			switch(currentGeneratorRequest){
			case REQUEST_PLAY: new PlayFrame(generator.getSudoku()); break;
			case REQUEST_GENERATE: new ViewFrame(generator.getSudoku(), generator.getSolution());
			}
			currentGeneratorRequest = REQUEST_NONE;
		}
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}
