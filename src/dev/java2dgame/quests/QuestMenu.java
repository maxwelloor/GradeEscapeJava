package dev.java2dgame.quests;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.gfx.Fonts;
import dev.java2dgame.main.Handler;

public class QuestMenu {
	
	private static final int MENU_W = 800, MENU_H = 600, LINE_THICKNESS = 5;
	private static final Font FONT = Assets.dialogue_font;
	private static final String MENU_TITLE = "Quest's";
	private static final Color PRIMARY_C = Color.DARK_GRAY, SECONDARY_C = Color.white, THIRD_C = Color.black;
	
	private Handler handler;
	private boolean menuOpen;
	private int x, y, startingIndex = 0;
	private Quest selectedQuest;
	
	public QuestMenu(Handler handler) {
		this.handler = handler;
		
		this.x = handler.getWidth() / 2 - MENU_W / 2;
		this.y = handler.getHeight() / 2 - MENU_H / 2;
	}
	
	public void tick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q) && !handler.getWorld().getEntityManager().getPlayer().getCollectibleMenu().isMenuActive()) {
			if (!menuOpen)
				menuOpen = true;
			else
				menuOpen = false;
		}
		
		if (!menuOpen)
			return;
		
		int temp_y = y + Fonts.getFontHeight(FONT) + 5;
		
		for (int loops = 0; loops < 5; loops++) {
			int yOfString = (temp_y + 106 * loops);
			System.out.println(yOfString);
			int xOfString = x + 10;

			Rectangle clickbox = new Rectangle(xOfString, yOfString, MENU_W/2, 106);
			
			if (handler.getMouseManager().isLeftPressed() && clickbox.contains(new Point(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY()))) {
				if (Quest.getQuests()[startingIndex + loops] != null)
					selectedQuest = Quest.getQuests()[startingIndex + loops];
			}
		}
		
		if (selectedQuest != null)
			System.out.println(selectedQuest.getQuestName());
	}
	
	public void render(Graphics g) {
		if (!menuOpen)
			return;
		
		// The main gray box with an outline
		g.setColor(THIRD_C);
		g.fillRect(x - LINE_THICKNESS, y - LINE_THICKNESS, MENU_W + LINE_THICKNESS * 2, MENU_H + LINE_THICKNESS * 2);
		g.setColor(PRIMARY_C);
		g.fillRect(x, y, MENU_W, MENU_H);
		
		// The part that says "Quests".
		g.setFont(FONT);
		g.setColor(SECONDARY_C);
		g.drawString(MENU_TITLE, handler.getWidth() / 2 - Fonts.getWidthOfString(MENU_TITLE, FONT)/2, y + Fonts.getFontHeight(FONT) - 10);
		
		// The black lines.
		g.setColor(THIRD_C);
		for (int yOffset = 0; yOffset <= LINE_THICKNESS; yOffset++) {
			g.drawLine(x, y + Fonts.getFontHeight(FONT) + 5 + yOffset, x + MENU_W, y + Fonts.getFontHeight(FONT) + 5 + yOffset);
		}
		
		int temp_y = y + Fonts.getFontHeight(FONT) + 5;
		
		for (int xOffset = 0; xOffset <= LINE_THICKNESS; xOffset++) {
			g.drawLine(handler.getWidth()/2 + xOffset, temp_y, handler.getWidth()/2 + xOffset, y + MENU_H);
		}
		
		for (int linesDrawn = 1; linesDrawn < 5; linesDrawn++) {
			int yOfLine = temp_y + 106 * linesDrawn;
			for (int yOffset = 0; yOffset <= LINE_THICKNESS; yOffset++)
				g.drawLine(x, yOfLine + yOffset, x + MENU_W/2, yOfLine + yOffset);
		}
		// Lines Over.
		
		// Quest Names.
		temp_y = y + Fonts.getFontHeight(FONT) + 5 + 106;
		
		for (int loops = 0; loops < 5; loops++) {
			int yOfString = (temp_y + 106 * loops) + 20;
			
			g.setFont(FONT);
			
			try {
				g.drawString(Quest.getQuests()[startingIndex + loops].getQuestName(), x + 10, yOfString - Fonts.getFontHeight(FONT));
				
				if (selectedQuest == Quest.getQuests()[startingIndex + loops]) {
					for (int offset = 0; offset < 5; offset++) {
						g.drawRect(x + 8 + offset, yOfString + offset - Fonts.getFontAscent(FONT)*2 - 10, Fonts.getWidthOfString(Quest.getQuests()[startingIndex + loops].getQuestName(), FONT), Fonts.getFontHeight(FONT));
					}
				}
			} catch (NullPointerException e) {
				g.drawString("???", x + 168, yOfString - Fonts.getFontHeight(FONT) + 4);
			}
		}
		// Quest Names Over.
	}
	
	public boolean isMenuOpen() {
		return menuOpen;
	}
}
