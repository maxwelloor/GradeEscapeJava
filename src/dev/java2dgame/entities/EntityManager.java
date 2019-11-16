package dev.java2dgame.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;

import dev.java2dgame.entities.creatures.Player;
import dev.java2dgame.main.Handler;

public class EntityManager {
	
	private static boolean drawEntityHitboxes = false;
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b) {
			if (a.getY() + a.getHitbox(0f, 0f).y + a.getHitbox(0f, 0f).height < b.getY() + b.getHitbox(0f, 0f).y + b.getHitbox(0f, 0f).height)
				return -1;
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		this.entities = new ArrayList<Entity>();
		addEntity(player);
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		entities.sort(renderSorter);
		
		// Turns on hitbox drawing if the h key is pressed
		if (handler.getKeyManager().keyJustPressed((KeyEvent.VK_H))) {
			if (EntityManager.drawEntityHitboxes) {
				EntityManager.drawEntityHitboxes = false;
			} else {
				EntityManager.drawEntityHitboxes = true;
			}
		}
	}
	
	public void render(Graphics g) {
		for (Entity e : entities) {
			e.render(g);
			if (EntityManager.drawEntityHitboxes) {
				g.setColor(Color.red);
				g.drawRect((int) (e.x - handler.getGameCamera().getxOffset() + e.hitbox.x), (int) (e.y - handler.getGameCamera().getyOffset() + e.hitbox.y), e.hitbox.width, e.hitbox.height);
			}
		}
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	// Getters & Setters.
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
}
