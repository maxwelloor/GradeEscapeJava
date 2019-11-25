package dev.java2dgame.entities.statics;

import java.awt.Graphics;

import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.TextAboveHeadUI;
import dev.java2dgame.ui.UIObject;
import dev.java2dgame.worlds.World;

public class AreaSwitchExact extends StaticEntity {
	
	private int sendTo, playerSpawnX, playerSpawnY;
	
	public AreaSwitchExact(Handler handler, float x, float y, int sendTo, int playerX, int playerY) {
		super(handler, x, y, true);
		
		hitbox.width = 64;
		hitbox.height = 64;
		
		this.sendTo = sendTo;
		this.playerSpawnX = playerX;
		this.playerSpawnY = playerY;
	}
	
	public void changeWorld() {
		handler.setWorld(new World(handler, Integer.toString(sendTo), playerSpawnX, playerSpawnY));
	}

	@Override
	public void tick() {
		
		boolean doorLocked = false;
		
		// If the player interacts with this
		if (this.checkForPlayerInteract(handler.getWorld().getEntityManager().getPlayer())) {
			for (int num: AreaSwitch.getLockedAreas()) {
				if (num == sendTo) {
					doorLocked = true;
					for (int key: handler.getWorld().getEntityManager().getPlayer().getKeysFound()) {
						if (key == sendTo) {
							changeWorld();
							return;
						}
					}
				}
			}
			if (!doorLocked)
				changeWorld();
			else {
				
				for (UIObject obj: handler.getMouseManager().getUiManager().getObjects()) {
					if (obj.getClass().getSimpleName().equals("TextAboveHeadUI")) {
						break;
					}
				}
				handler.getMouseManager().getUiManager().addObject(new TextAboveHeadUI(handler, "Door Locked."));	
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
	}
}
