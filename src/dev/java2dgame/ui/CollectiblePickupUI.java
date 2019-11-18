package dev.java2dgame.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ListIterator;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.gfx.Fonts;
import dev.java2dgame.main.Handler;

public class CollectiblePickupUI extends UIObject {
	
	private static final int FRAMES_TO_DISSAPEAR = 60;
	private static final Font FONT = Assets.collectible_menu_info_font;
	
	private int framesAlive, yOffset;
	private Handler handler;
	private String cName;

	public CollectiblePickupUI(Handler handler, String cName) {
		super(0, 0, 0, 0);
		this.handler = handler;
		this.cName = cName;
		
		this.yOffset = -20;
	}

	@Override
	public void tick(ListIterator<UIObject> it) {
		x = (handler.getWorld().getEntityManager().getPlayer().getX() - handler.getGameCamera().getxOffset() + handler.getWorld().getEntityManager().getPlayer().getW()/2 - Fonts.getWidthOfString(cName, FONT)/2) + 19;
		
		framesAlive++;
		yOffset--;
		
		if (framesAlive >= FRAMES_TO_DISSAPEAR) {
			it.remove();
		}
	}

	@Override
	public void render(Graphics g) {
		g.setFont(FONT);
		g.setColor(Color.white);
		g.drawString(cName, (int) x, (int) (handler.getWorld().getEntityManager().getPlayer().getY() + yOffset));
	}

	@Override
	public void onClick() {
		
	}

}
