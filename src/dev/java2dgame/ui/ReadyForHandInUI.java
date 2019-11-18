package dev.java2dgame.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ListIterator;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.main.Handler;

public class ReadyForHandInUI extends UIObject {
	
	private static final int BOX_WIDTH = 400, BOX_HEIGHT = 80, BOX_SPEED = 3, FRAMES_BEFORE_LEAVE = 120,
			START_X = 0, START_Y = -BOX_HEIGHT, BORDER_THICKNESS = 5;
			
	private static final Font Q_NAME_FONT = Assets.quest_name_font, Q_COMPLETED_FONT = Assets.quest_complete_font;
	
	private Handler handler;
	private boolean revealing = true, staying = false, hiding = false;
	private int frameCounter = 0;
	private String qName;

	public ReadyForHandInUI(Handler handler, String qName) {
		super(START_X, START_Y, 0, 0);
		
		this.handler = handler;
		this.qName = qName;
	}

	@Override
	public void tick(ListIterator<UIObject> it) {
		if (revealing) {
			y += BOX_SPEED;
			if (y >= 0) {
				y = 0;
				revealing = false;
				staying = true;
			}
		} else if (staying) {
			frameCounter++;
			
			if (frameCounter >= FRAMES_BEFORE_LEAVE) {
				staying = false;
				hiding = true;
			}
		} else {
			y -= BOX_SPEED;
			
			if (y < -BOX_HEIGHT) {
				it.remove();
				
				if (handler.getMouseManager().getUiManager().doesQueueHaveItem()) {
					it.add(handler.getMouseManager().getUiManager().getObjectInQueue());
					handler.getMouseManager().getUiManager().setObjectInQueue(null);
					handler.getMouseManager().getUiManager().setIfItemInQueue(false);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect((int) x, (int) y, BOX_WIDTH, BOX_HEIGHT);
		
		// Draws the border around the box
		g.setColor(Color.black);
		for (int xOffset = 0; xOffset < BORDER_THICKNESS; xOffset++)
			g.drawRect((int) (x + xOffset), (int) y, BOX_WIDTH, BOX_HEIGHT);
		
		for (int yOffset = 0; yOffset < BORDER_THICKNESS; yOffset++)
			g.drawRect((int) x, (int) (y + yOffset), BOX_WIDTH, BOX_HEIGHT);
		
		g.setColor(Color.white);
		g.setFont(Q_NAME_FONT);
		
		FontMetrics fm = g.getFontMetrics();
		
		g.drawString("Quest ready for hand in:", (int) (x + 10), (int) (y + fm.getHeight()));
		
		g.setFont(Q_COMPLETED_FONT);
		g.setColor(Color.yellow);
		FontMetrics fm2 = g.getFontMetrics();
		g.drawString(qName, (int) (x + 10), (int) (y + fm2.getHeight() + 20));
	}

	@Override
	public void onClick() {
		
	}

}
