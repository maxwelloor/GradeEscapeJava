package dev.java2dgame.collectibles;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;

import dev.java2dgame.main.Handler;

public class CollectibleManager {
	
	private Handler handler;
	private ArrayList<Collectible> collectibles;
	
	public CollectibleManager(Handler handler) {
		this.handler = handler;
		this.collectibles = new ArrayList<Collectible>();
	}
	
	public void tick() {
		Iterator<Collectible> iter = collectibles.iterator();
		
		while (iter.hasNext()) {
			Collectible c = iter.next();
			
			c.tick(iter);
		}
	}
	
	public void render(Graphics g) {
		for (Collectible c: collectibles) {
			c.render(g);
		}
	}
	
	public void addCollectible(Collectible col, int x, int y) {
		col.setPos(x, y);
		col.setHandler(handler);
		collectibles.add(col);
	}
	
	public void removeCollectible(Collectible col) {
		collectibles.remove(col);
	}
	
	// GETTERS AND SETTERS
	
	public Handler getHandler() {
		return handler;
	}
	
	public ArrayList<Collectible> getCollectibles() {
		return collectibles;
	}
	
}
