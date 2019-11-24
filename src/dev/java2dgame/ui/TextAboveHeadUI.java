package dev.java2dgame.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ListIterator;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.gfx.Fonts;
import dev.java2dgame.main.Handler;

public class TextAboveHeadUI extends UIObject {
	
	public static final int FRAMES_TO_DISSAPEAR = 60;
	private static final Font FONT = Assets.collectible_menu_info_font;
	
	private String text;
	private Handler handler;
	private int framesAlive = 0;
	
	public TextAboveHeadUI(Handler handler, String text) {
		super(0, 0, 0, 0);
		this.text = text;
		this.handler = handler;
	}
	
	@Override
	public void tick(ListIterator<UIObject> it) {
		x = (handler.getWorld().getEntityManager().getPlayer().getX() + handler.getWorld().getEntityManager().getPlayer().getW()/2 - Fonts.getWidthOfString(text, FONT)/2) + 16;
		
		framesAlive++;
		
		if (framesAlive >= FRAMES_TO_DISSAPEAR) {
			it.remove();
		}
	}
	
	@Override
	public void render(Graphics g) {
		g.setFont(FONT);
		g.setColor(Color.white);
		g.drawString(text, (int) x, (int) (handler.getWorld().getEntityManager().getPlayer().getY() - 20 - handler.getGameCamera().getyOffset()));
	}

	@Override
	public void onClick() {
		
	}
}
