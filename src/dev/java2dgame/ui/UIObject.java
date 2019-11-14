package dev.java2dgame.ui;

import java.awt.Rectangle;

public abstract class UIObject {
	
	protected float x, y;
	protected int width, height;
	protected Rectangle hitbox;
	protected boolean hovering = false;
	
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		this.hitbox = new Rectangle((int) x, (int) y, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render();
	
	public abstract void onClick();
	
	public void onMouseMove() {
		
	}
	
	public void onMouseRelease() {
		
	}

}
