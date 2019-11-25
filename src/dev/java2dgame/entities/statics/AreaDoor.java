package dev.java2dgame.entities.statics;

import java.awt.Color;
import java.awt.Graphics;

import dev.java2dgame.main.Handler;

public class AreaDoor extends StaticEntity {
	
	int doorId;
	boolean doorLocked = true;

	public AreaDoor(Handler handler, float x, float y, int doorID) {
		super(handler, x, y, false);
		
		hitbox.width = 64;
		hitbox.height = 64;
		
		this.doorId = doorID;
	}

	@Override
	public void tick() {
		if (doorLocked) {
			for (int keyId: handler.getWorld().getEntityManager().getPlayer().getKeysFound()) {
				if (keyId == doorId)
					doorLocked = false;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (doorLocked) {
			g.setColor(Color.red);
			g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 64, 64);
		}
	}
	
	public boolean isDoorLocked() {
		return doorLocked;
	}
}
