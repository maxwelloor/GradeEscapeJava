package dev.java2dgame.entities.statics;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.KeyPickupUI;

public class Key extends StaticEntity {
	
	private int areaUnlock;
	private String name;

	public Key(Handler handler, float x, float y, int areaToUnlock) {
		super(handler, x, y, false);
		
		this.areaUnlock = areaToUnlock;
		this.w = 32;
		this.h = 32;
		
		// Set key names here.
		if (areaUnlock == 2)
			name = "Test Key";
	}

	@Override
	public void tick() {
		// Doesnt tick or render if the key is had by the player.
		for (int keyNum: handler.getWorld().getEntityManager().getPlayer().getKeysFound()) {
			if (keyNum == areaUnlock)
				return;
		}
		
		// Checks for the player colliding with the key.
		Rectangle hitbox = new Rectangle((int) x, (int) y, w, h);
		
		if (hitbox.intersects(handler.getWorld().getEntityManager().getPlayer().getHitbox(0f, 0f))) {
			handler.getWorld().getEntityManager().getPlayer().getKeysFound().add(areaUnlock);
			handler.getMouseManager().getUiManager().addObject(new KeyPickupUI(handler, this.name));
		}
	}

	@Override
	public void render(Graphics g) {
		// Doesnt tick or render if the key is had by the player.
		for (int keyNum: handler.getWorld().getEntityManager().getPlayer().getKeysFound()) {
			if (keyNum == areaUnlock)
				return;
		}
		
		//g.drawImage(Assets.key, x, y, observer);
		g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), w, h);
	}

}
