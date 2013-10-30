package de.catchycube.sudoku.gui.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputSudokuPanel extends SudokuPanel implements MouseListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private int selectedX = -1, selectedY = -1;
	
	public InputSudokuPanel(){
		super();
		this.addMouseListener(this);
		this.addKeyListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getButton() == MouseEvent.BUTTON1){
			int tileWidth = getWidth()/9, tileHeight = getHeight()/9;
			int sx = arg0.getX() / tileWidth, sy = arg0.getY() / tileHeight;
			Rectangle oldRect = new Rectangle(selectedX - 5, selectedY - 5, tileWidth+10, tileHeight + 10);
			Rectangle newRect = new Rectangle(sx - 5, sy - 5, tileWidth+10, tileHeight + 10);
			if(!sudoku.isPreset(sx, sy)){
				selectedX = sx;
				selectedY = sy;
			}
			else{
				selectedX = -1;
				selectedY = -1;
			}
			
			this.paintImmediately(oldRect); this.paintImmediately(newRect); updateUI();
			requestFocus();
		}
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		if(selectedX != -1 && selectedY != -1){
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(3f));
			g2d.setColor(Color.red);
			int tileWidth = getWidth()/9, tileHeight = getHeight()/9;
			g2d.drawRect(tileWidth * selectedX, tileHeight * selectedY, tileWidth, tileHeight);
		}
	}
	
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	//Keyboard input
	@Override
	public void keyPressed(KeyEvent arg0) {
		int character = (int)arg0.getKeyChar();
		if(character >= 48 && character <= 57 && selectedX != -1 && selectedY != -1){
			int number = character -48;
			if(number==0){
				sudoku.remove(selectedX, selectedY);
			}
			else{
				sudoku.set(selectedX, selectedY, (byte) number);
			}
			int tileWidth = getWidth()/9, tileHeight = getHeight()/9;
			paintImmediately(tileWidth*selectedX, tileHeight*selectedY, tileWidth, tileHeight); updateUI();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
