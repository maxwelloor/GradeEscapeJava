package dev.java2dgame.ui;

import java.awt.Color;
import java.awt.Graphics;

import dev.java2dgame.entities.creatures.Player;
import dev.java2dgame.main.Handler;

public class SprintBarUI extends UIObject {
	
	private static final int SPRINT_BAR_WIDTH = 50, SPRINT_BAR_HEIGHT_DEFAULT = 300, GAP_FROM_SIDE_SCREEN = 10,
			GAP_FROM_BOTTOM_SCREEN = 15, LINE_THICKNESS = 10;
	
	private Handler handler;
	private int barHeight = SPRINT_BAR_HEIGHT_DEFAULT, greenBarY;

	public SprintBarUI(Handler handler) {
		super(GAP_FROM_SIDE_SCREEN, handler.getHeight() - SPRINT_BAR_HEIGHT_DEFAULT - GAP_FROM_BOTTOM_SCREEN, SPRINT_BAR_WIDTH, SPRINT_BAR_HEIGHT_DEFAULT);
		
		greenBarY = (int) y;
		
		this.handler = handler;
	}

	@Override
	public void tick() {

		if (handler.getWorld().getEntityManager().getPlayer().getSprinting()) {		
			// If the bar isnt empty minus from the barHeigh int.
			if (!isStaminaEmpty()) {
				barHeight--;
				greenBarY++;
				handler.getWorld().getEntityManager().getPlayer().setStaminaBarEmpty(false);
				
			// If it is empty then make the player stop running.
			} else {
				handler.getWorld().getEntityManager().getPlayer().setStaminaBarEmpty(true);
				handler.getWorld().getEntityManager().getPlayer().setTired(true);
			}
			
		} else {
			if (barHeight < SPRINT_BAR_HEIGHT_DEFAULT) {
				
				if (SPRINT_BAR_HEIGHT_DEFAULT - 5 < barHeight)
					handler.getWorld().getEntityManager().getPlayer().setTired(false);
				
				handler.getWorld().getEntityManager().getPlayer().setStaminaBarEmpty(false);
				barHeight++;
				greenBarY--;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
		// Changes the bar color depending if the player is tired
		if (handler.getWorld().getEntityManager().getPlayer().isTired())
			g.setColor(Color.red);
		else
			g.setColor(Color.green);
		
		g.fillRect((int) x, greenBarY, SPRINT_BAR_WIDTH, barHeight);
		
		g.setColor(Color.black);
		for (int offset = 0; offset < LINE_THICKNESS; offset++) {
			g.drawRect((int) (x + offset), (int) (y + offset), SPRINT_BAR_WIDTH, SPRINT_BAR_HEIGHT_DEFAULT);
		}
	}

	@Override
	public void onClick() {
		
	}
	
	public boolean isStaminaEmpty() {
		if (barHeight <= 0)
			return true;
		else
			return false;
	}

}
