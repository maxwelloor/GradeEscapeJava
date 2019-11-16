package dev.java2dgame.entities.statics;

import java.awt.Graphics;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.main.Handler;

public class WoodenTable extends StaticEntity {

	public WoodenTable(Handler handler, float x, float y) {
		super(handler, x, y, false);
		
		hitbox.x = 0;
		hitbox.y = 15;
		hitbox.width = Assets.wooden_table.getWidth();
		hitbox.height = Assets.wooden_table.getHeight() - 32;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.wooden_table, (int) (x - handler.getGameCamera().getxOffset()), (int) (x - handler.getGameCamera().getyOffset()), null);
	}
}
