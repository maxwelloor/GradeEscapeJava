package dev.java2dgame.collectibles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.gfx.Fonts;
import dev.java2dgame.main.Handler;

public class CollectibleMenu {
	
	public static final int MENU_W = 800, MENU_H = 600, LINE_THICKNESS = 5;
	public static final Font FONT = Assets.collectible_menu_font, INFO_FONT = Assets.collectible_menu_info_font;
	private static final String MENU_TITLE = "Collectible's";
	private static final Color PRIMARY_C = Color.DARK_GRAY, SECONDARY_C = Color.white, THIRD_C = Color.black;
	
	private Handler handler;
	private Collectible selectedCollectible = null;
	private boolean active = false;
	private int x, y;
	
	public CollectibleMenu(Handler handler) {
		this.handler = handler;
		
		this.x = handler.getWidth() / 2 - MENU_W / 2;
		this.y = handler.getHeight() / 2 - MENU_H / 2;
	}
	
	public void tick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E) && !handler.getWorld().getEntityManager().getPlayer().getQuestMenu().isMenuOpen()) { // checks for the player trying to open the menu
			if (active)
				active = false;
			else
				active = true;
		}
		
		if (!active) return; // If the menu isnt active dont tick() 
		
		
		// PUT EVERYTHING BELOW HERE
		int temp_y = y + Fonts.getFontHeight(FONT) + 5;
		int startX = x + 21, collectibleY = temp_y + 20, loops = 0;
		for (Collectible c: Collectible.getAllCollectibles()) {
			if (c == null)
				break;
			
			if (handler.getMouseManager().isLeftPressed()) {
				if (new Rectangle(startX + loops * 100, collectibleY, 64, 64).contains(handler.getMouseManager().getMouseX(), 
																						handler.getMouseManager().getMouseY())) {
					selectedCollectible = c;
				}
			}
			
			loops++;
			
			if (loops > 7) {
				loops = 0;
				collectibleY += 88;
			}
		}
		
	}
	
	public void render(Graphics g) {
		if (!active) return;
		
		// The main gray box with an outline
		g.setColor(THIRD_C);
		g.fillRect(x - LINE_THICKNESS, y - LINE_THICKNESS, MENU_W + LINE_THICKNESS * 2, MENU_H + LINE_THICKNESS * 2);
		g.setColor(PRIMARY_C);
		g.fillRect(x, y, MENU_W, MENU_H);
		
		// The part that says "Collectibles".
		g.setFont(FONT);
		g.setColor(SECONDARY_C);
		g.drawString(MENU_TITLE, handler.getWidth() / 2 - Fonts.getWidthOfString(MENU_TITLE, FONT)/2, y + Fonts.getFontHeight(FONT) - 10);
		
		// The black lines.
		g.setColor(THIRD_C);
		for (int yOffset = 0; yOffset <= LINE_THICKNESS; yOffset++) {
			g.drawLine(x, y + Fonts.getFontHeight(FONT) + 5 + yOffset, x + MENU_W, y + Fonts.getFontHeight(FONT) + 5 + yOffset);
		}
		
		for (int yOffset = 0; yOffset <= LINE_THICKNESS; yOffset++) {
			int temp_h = MENU_H - (Fonts.getFontHeight(FONT) + 5);
			int temp_y = y + Fonts.getFontHeight(FONT) + 5;
			
			g.drawLine(x, temp_y + temp_h / 2 + yOffset, x + MENU_W, temp_y + temp_h / 2 + yOffset);
		}
		
		for (int lines = 1; lines < 8; lines++ ) {
			int temp_h = MENU_H - (Fonts.getFontHeight(FONT) + 5);
			int temp_y = y + Fonts.getFontHeight(FONT) + 5;
			for (int xOffset = 0; xOffset <= LINE_THICKNESS; xOffset++) {
				g.drawLine((x + lines * 100) + xOffset, temp_y, x + (lines * 100) + xOffset, temp_y + temp_h / 2);
			}
		}
		
		for (int lines = 1; lines < 3; lines++ ) {
			int temp_y = y + Fonts.getFontHeight(FONT) + 5;
			for (int yOffset = 0; yOffset <= LINE_THICKNESS; yOffset++) {
				g.drawLine(x, (temp_y + 88 * lines) + yOffset, x + MENU_W, (temp_y + 88 * lines) + yOffset);
			}
		}
		
		int temp_y = y + Fonts.getFontHeight(FONT) + 5;
		int startX = x + 21, collectibleY = temp_y + 20, loops = 0;
		for (Collectible c: Collectible.getAllCollectibles()) {
			if (c == null)
				break;
			
			if (!c.isCollected()) {
				g.drawString("???", startX + loops * 100, collectibleY + Fonts.getFontHeight(FONT) - 18);
			} else {
				c.renderMenuImage(g, startX + loops * 100,  collectibleY - 5);
			}
			
			// Draws the selection box around the selection.
			if (c == selectedCollectible) {
				for (int offset = -2; offset < 3; offset++) {
					g.drawRect((startX + offset + loops * 100) - 2, collectibleY - 5, 64, 64);
					g.drawRect((startX + loops * 100) - 2, collectibleY - 5+ offset, 64, 64);
				}
			}

			loops++;
			
			if (loops > 7) {
				loops = 0;
				collectibleY += 88;
			}
		}
		
		g.setColor(SECONDARY_C);
		
		if (selectedCollectible != null) {
		
			int infoAreaY = y + Fonts.getFontHeight(FONT) + 5 + 265; // Start of the info section.
			int infoAreaX = x + 10;
			
			g.setFont(INFO_FONT);
			
			if (selectedCollectible.isCollected()) {
				g.drawString("Collectible Name: " + selectedCollectible.getName(), infoAreaX, infoAreaY + Fonts.getFontHeight(INFO_FONT));
				int cLoops = 1;
				for (String line: selectedCollectible.getDisplayDesc()) {
					cLoops++;
					g.drawString(line, infoAreaX, infoAreaY + Fonts.getFontHeight(INFO_FONT) * cLoops);
				}
			} else {
				g.drawString("Collectible Name: ???", infoAreaX, infoAreaY + Fonts.getFontHeight(INFO_FONT));
				g.drawString("Collectible Desc: ???", infoAreaX, infoAreaY + Fonts.getFontHeight(INFO_FONT) * 2);
			}
		}
		
		
	}
	
	public boolean isMenuActive() {
		return active;
	}

}
