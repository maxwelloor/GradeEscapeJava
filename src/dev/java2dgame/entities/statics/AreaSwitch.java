package dev.java2dgame.entities.statics;

import java.awt.Graphics;

import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.TextAboveHeadUI;
import dev.java2dgame.ui.UIObject;
import dev.java2dgame.worlds.World;

public class AreaSwitch extends StaticEntity {
	
	private static int[] lockedAreas = {2, 3};
	
	private int sendTo;

	public AreaSwitch(Handler handler, float x, float y, int sendTo) {
		super(handler, x, y, true);
		// sendTo is the number of the map the switch will send the player to.
		this.sendTo = sendTo;
		
		hitbox.width = 64;
		hitbox.height = 64;
	}
	
	public void changeWorld() {
		handler.setWorld(new World(handler, Integer.toString(sendTo)));
	}

	@Override
	public void tick() {
		
		boolean doorLocked = false, keyHad = false;
		
		// If the player interacts with this
		if (this.checkForPlayerInteract(handler.getWorld().getEntityManager().getPlayer())) {
			for (int num: lockedAreas) {
				if (num == sendTo) {
					doorLocked = true;
					for (int key: handler.getWorld().getEntityManager().getPlayer().getKeysFound()) {
						if (key == sendTo)
							changeWorld();
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
