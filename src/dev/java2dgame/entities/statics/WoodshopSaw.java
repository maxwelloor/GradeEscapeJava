package dev.java2dgame.entities.statics;

import java.awt.Graphics;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.main.Handler;
import dev.java2dgame.tiles.Tile;

public class WoodshopSaw extends StaticEntity {
	
	public WoodshopSaw(Handler handler, float x, float y) {
		super(handler, x, y, false);
		
		hitbox.x = 0;
		hitbox.y = 50;
		hitbox.width = Assets.saw.getWidth();
		hitbox.height = 42;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.saw, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
	}
}
