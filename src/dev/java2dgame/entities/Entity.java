package dev.java2dgame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import dev.java2dgame.entities.creatures.Player;
import dev.java2dgame.main.Handler;

public abstract class Entity {
	
	private static int interactRange = 3;
	
	protected float x, y;
	protected int w, h;
	protected Handler handler;
	protected Rectangle hitbox;
	protected boolean interactible;
	
	public Entity(Handler handler, float x, float y) {
		this.handler = handler;
		this.interactible = false;
		this.x = x;
		this.y = y;
		
		this.hitbox = new Rectangle(0, 0, w, h);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public boolean checkEntityCollisions(float xOffset, float yOffset){
		for(Entity e : handler.getWorld().getEntityManager().getEntities()){
			if(e.equals(this))
				continue;
			// Exceptions for classes that are not solid objects.
			
			if(e.getClass().getSimpleName().equals("AreaSwitch"))
				continue;
			
			// Exceptions over.
			
			if(e.getHitbox(0f, 0f).intersects(getHitbox(xOffset, yOffset)))
				return true;
		}
		return false;
	}
	
	public boolean checkSingleEntityCollision(Entity e) {
		if(this.getHitbox(0, 0).intersects(e.getHitbox(0, 0)))
			return true;
		return false;
	}
	
	public boolean checkForPlayerInteract(Player player) {
		if (player.getHitbox(0f, 0f).intersects(getInteractBox(0f, 0f)) && handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {
			player.setCanInteract(true);
			return true;
		}
		
		return false;
	}
	
	public Rectangle getHitbox(float xOffset, float yOffset){
		return new Rectangle((int) (x + hitbox.x + xOffset), (int) (y + hitbox.y + yOffset), hitbox.width, hitbox.height);
	}
	
	public Rectangle getInteractBox(float xOffset, float yOffset) {
		return new Rectangle((int) (x + hitbox.x + xOffset - interactRange), (int) (y + hitbox.y + yOffset - interactRange), hitbox.width + interactRange * 2, hitbox.height + interactRange * 2);
	}
	
	public boolean touchingPlayer() {
		if (this.checkSingleEntityCollision(handler.getWorld().getEntityManager().getPlayer()))
			return true;
		return false;
	}
	
	public boolean isInteractible() {
		return interactible;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}
}
