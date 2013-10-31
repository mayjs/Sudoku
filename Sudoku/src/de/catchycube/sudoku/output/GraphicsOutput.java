package de.catchycube.sudoku.output;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.catchycube.sudoku.general.Sudoku;

public class GraphicsOutput {
	
	public static float fontFactor = 5f/45f;
	
	private static Color preSetColor = Color.black, insertedColor = Color.gray;
	
	public static void render(Sudoku sudoku, Graphics2D g, int width, int height){
		g.setBackground(Color.white);
		g.clearRect(0, 0, width, height);
		g.setColor(Color.black);
		int tileWidth = width/9, tileHeight = height/9;
		Stroke normalStroke = new BasicStroke(1), bigStroke = new BasicStroke(3);
		for(int i = 0; i < 10; i++){
			if(i%3==0){
				g.setStroke(bigStroke);
			} else{
				g.setStroke(normalStroke);
			}
			g.drawLine(0, tileHeight*i, width, tileHeight*i);
			g.drawLine(tileWidth*i, 0, tileWidth*i, height);
		}
		
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		int yOff = -metrics.getHeight() / 2 + metrics.getAscent();
		for(int x = 0; x < 9; x++){
			for(int y = 0; y < 9; y++){
				String text = "" + sudoku.get(x, y);
				int yp = y * tileHeight +(tileHeight/2) + yOff;
				int xp = x*tileWidth + (tileWidth/2) - metrics.stringWidth(text)/2;
				if(sudoku.get(x, y) != 0){
					g.setColor(sudoku.isPreset(x, y)?preSetColor:insertedColor);
					g.drawString(text, xp, yp);
				}
			}
		}
	}
	
	/**
	 * Saves a sudoku to the specified file name
	 * @param sudoku The sudoku to save
	 * @param fileName The full path to the file
	 * @param format The image format to use for saving
	 * @param width The image width (Should be a multiple of 9)
	 * @param height The image height (Should be a multiple of 9)
	 */
	public static void save(Sudoku sudoku, String fileName, String format, int width, int height){
		save(sudoku,new File(fileName),format,width,height);
	}
	
	public static void save(Sudoku sudoku, File file, String format, int width, int height){
		try{
			BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g = img.createGraphics();
			g.setFont(new Font(Font.SERIF,Font.PLAIN,(int)(fontFactor * height)));
			render(sudoku,g,width,height);
			ImageIO.write(img, format, file);
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public static Color getPreSetColor() {
		return preSetColor;
	}

	public static void setPreSetColor(Color preSetColor) {
		GraphicsOutput.preSetColor = preSetColor;
	}

	public static Color getInsertedColor() {
		return insertedColor;
	}

	public static void setInsertedColor(Color insertedColor) {
		GraphicsOutput.insertedColor = insertedColor;
	}
	
	
}
