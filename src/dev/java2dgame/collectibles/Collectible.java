package dev.java2dgame.collectibles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import dev.java2dgame.main.Handler;

public class Collectible {
	
	// COLLECTIBLES
	
	public static Collectible[] items = new Collectible[256];
	public static Collectible mooresHair = new Collectible(null, "Moore's Hair");
	public static Collectible pencil = new Collectible(null, "Pencil");
	
	// DYNAMIC STUFF
	
	private static final int COLLECTIBLE_W = 32, COLLECTIBLE_H = 32;
	
	private Handler handler;
	private float x, y;
	private String name;
	private boolean collected;
	private BufferedImage image;
	
	public Collectible(BufferedImage img, String iName) {
		this.name = iName;
		this.image = img;
	}
	
	public void tick(Iterator<Collectible> it) {
		if (collected)
			return;
		
		// If the player is touching the collectible
		if (getHitbox().intersects(handler.getWorld().getEntityManager().getPlayer().getHitbox(0f, 0f))) {
			it.remove();
			collected = true;
		}	
	}

	public void render(Graphics g) {
		if (handler == null || collected)
			return;
		
		g.setColor(Color.red);
		if (image == null)
			g.fillRect((int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), COLLECTIBLE_W, COLLECTIBLE_H);
		
		g.drawImage(image, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
	}
	
	public void renderMenuImage(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}
	
	// GETTTERS AND SETTTERS.
	
	public void setPos(int x, int y) {
		this.x = (float) x;
		this.y = (float) y;
	}
	
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle((int) x, (int) y, COLLECTIBLE_W, COLLECTIBLE_H);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	
}
