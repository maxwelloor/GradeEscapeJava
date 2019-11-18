package dev.java2dgame.ui;

import java.awt.Graphics;
import java.util.ListIterator;

public class CollectiblePickupUI extends UIObject {
	
	private static final int FRAMES_TO_DISSAPEAR = 60;
	
	private int framesAlive;

	public CollectiblePickupUI(float x, float y) {
		super(x, y, 0, 0);
	}

	@Override
	public void tick(ListIterator<UIObject> it) {
		framesAlive++;
		y--;
		
		if (framesAlive >= FRAMES_TO_DISSAPEAR) {
			it.remove();
		}
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void onClick() {
		
	}

}
