package dev.java2dgame.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpriteSheet {
	
	private BufferedImage sheet;
	
	public SpriteSheet(String path) {
		this.sheet = ImageLoader.loadImage(path);
	}
	
	public BufferedImage crop(int x, int y, int w, int h) {
		return sheet.getSubimage(x, y, w, h);
	}
	
	public BufferedImage[] getPlayerList(int rows, int cols, int width, int height) {
		
		BufferedImage[] spritelist = new BufferedImage[rows * cols];
		
		int row = 1;
		
		int x_cord = 0;
		int y_cord = 0;
		int setI = 0;
		
		while(row < rows + 1) {			
			// Loops for the same amount of rows there is
			for (int x = 0; x < cols; x++) {
				spritelist[setI] = sheet.getSubimage(x_cord, y_cord, width, height);
				x_cord += width;
				setI++;
			}
			// After every row adds the height to the y_cord var.
			y_cord += height;
			x_cord = 0;
			row += 1;
		}
		return spritelist;
	}
}
