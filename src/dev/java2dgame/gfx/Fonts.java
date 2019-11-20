package dev.java2dgame.gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Fonts {
	
	public static Font loadFont(String path, float size){	
		try {
			InputStream fileStream = Fonts.class.getResourceAsStream(path);
			Font myFont = Font.createFont(Font.TRUETYPE_FONT, fileStream);
			return myFont.deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	public static int getWidthOfString(String text, Font font) {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

		Graphics g = img.createGraphics();
		g.setFont(font);

		FontMetrics fm = g.getFontMetrics();
		return fm.stringWidth(text);
	}
	
	public static int getFontHeight(Font font) {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = img.createGraphics();
		g.setFont(font);
		
		FontMetrics fm = g.getFontMetrics();
		
		return fm.getHeight();
	}
	
	public static int getFontAscent(Font font) {
		BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = img.createGraphics();
		g.setFont(font);
		
		FontMetrics fm = g.getFontMetrics();
		
		return fm.getAscent();
	}
}
