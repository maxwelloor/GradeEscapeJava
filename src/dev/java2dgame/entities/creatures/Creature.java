package dev.java2dgame.entities.creatures;

import dev.java2dgame.entities.Entity;
import dev.java2dgame.main.Handler;
import dev.java2dgame.tiles.Tile;

public abstract class Creature extends Entity {
	
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,
							  DEFAULT_CREATURE_HEIGHT = 64;
	
	protected float speed, xMove, yMove;

	public Creature(Handler handler, float x, float y) {
		// super means the parent classes constructor.
		super(handler, x, y);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;

	}
	
	public void move() {
		if (!checkEntityCollisions(xMove, 0f))
			moveX();
		if (!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	public void moveX() {
		if (xMove > 0) {//Moving right
			int tx = (int) (x + xMove + hitbox.x + hitbox.width) / Tile.TILE_WIDTH;
			
			if (!collisionWithTile(tx, (int) (y + hitbox.y) / Tile.TILE_HEIGHT) && 
					!collisionWithTile(tx, (int) (y + hitbox.y + hitbox.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILE_WIDTH - hitbox.x - hitbox.width - 1;
			}
		} else if (xMove < 0) {// Moving left
			int tx = (int) (x + xMove + hitbox.x) / Tile.TILE_WIDTH;
			
			if (!collisionWithTile(tx, (int) (y + hitbox.y) / Tile.TILE_HEIGHT) && 
					!collisionWithTile(tx, (int) (y + hitbox.y + hitbox.height) / Tile.TILE_HEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - hitbox.x;
			}
		}
	}
	
	public void moveY() {
		if (yMove < 0) {// Moving up
			int ty = (int) (y + yMove + hitbox.y) / Tile.TILE_HEIGHT;
			
			if (!collisionWithTile((int) (x + hitbox.x) / Tile.TILE_HEIGHT, ty) &&
					(!collisionWithTile((int) (x + hitbox.x + hitbox.width) / Tile.TILE_HEIGHT, ty))) {
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - hitbox.y;
			}
		} else if (yMove > 0) {// Moving down
			int ty = (int) (y + yMove + hitbox.y + hitbox.height) / Tile.TILE_HEIGHT;
			
			if (!collisionWithTile((int) (x + hitbox.x) / Tile.TILE_HEIGHT, ty) &&
					(!collisionWithTile((int) (x + hitbox.x + hitbox.width) / Tile.TILE_HEIGHT, ty))) {
				y += yMove;
			} else {
				y = ty * Tile.TILE_HEIGHT - hitbox.y - hitbox.height - 1;
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	// Getters and setters.
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
