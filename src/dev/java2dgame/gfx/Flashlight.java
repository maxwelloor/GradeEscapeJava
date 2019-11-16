package dev.java2dgame.gfx;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import dev.java2dgame.main.Handler;

public class Flashlight {
	
	private Handler handler;
	private Color darknessColor, flashlightColor;
	
	public Flashlight(Handler handler) {
		this.handler = handler;
		
		darknessColor = new Color(0, 0, 0, 128);
		flashlightColor = new Color(0, 0, 0, 128);
	}
	
	public void render(Graphics g) {
		// Draws the darkness part of the screen.
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(darknessColor);
		g2.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		
		g2.setColor(flashlightColor);
		g2.setComposite(AlphaComposite.Dst.derive(0.0f));
		g2.fillRect(200, 200, 300, 200);
		
		g2.dispose();
	}
	
}
