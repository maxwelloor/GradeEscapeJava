package dev.java2dgame.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.ListIterator;

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
	
	public abstract void tick(ListIterator<UIObject> it);
	
	public abstract void render(Graphics g);
	
	public abstract void onClick();
	
	public void onMouseMove(MouseEvent e) {
		if (hitbox.contains(e.getX(), e.getY()))
			hovering = true;
		else
			hovering = false;
	}
	
	public void onMouseRelease(MouseEvent e) {
		if (hovering)
			onClick();
	}

}
