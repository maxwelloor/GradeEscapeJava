package dev.java2dgame.entities.statics;

import java.awt.Graphics;

import dev.java2dgame.main.Handler;
import dev.java2dgame.worlds.World;

public class AreaSwitch extends StaticEntity {
	
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
		// If the player interacts with this
		if (this.checkForPlayerInteract(handler.getWorld().getEntityManager().getPlayer())) {
			changeWorld();
		}
	}

	@Override
	public void render(Graphics g) {
		
	}

}
