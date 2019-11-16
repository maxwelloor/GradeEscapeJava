package dev.java2dgame.entities.statics;

import java.awt.Graphics;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.main.Handler;

public class ComputerMonitor extends StaticEntity {

	public ComputerMonitor(Handler handler, float x, float y) {
		super(handler, x, y, false);
		
		hitbox.width = Assets.computer_monitor.getWidth();
		hitbox.height = Assets.computer_monitor.getHeight();
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.computer_monitor, (int) (x - handler.getGameCamera().getxOffset()), (int) (x - handler.getGameCamera().getyOffset()), null);
	}

}
