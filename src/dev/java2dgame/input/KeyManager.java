package dev.java2dgame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys, justPressed, cantPress;
	
	// P keys is for the perron fight.
	public boolean up, down, left, right, space, shift, pKeysReleased;
	
	public KeyManager() {
		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];
		pKeysReleased = true;
	}
	
	public void tick() {
		for (int i = 0; i < keys.length; i++) {
			if (cantPress[i] && !keys[i]) {
				cantPress[i] = false;
			} else if(justPressed[i]) {
				cantPress[i] = true;
				justPressed[i] = false;
			} 
			
			if (!cantPress[i] && keys[i])
				justPressed[i] = true;
		}
		
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_A];
		space = keys[KeyEvent.VK_SPACE];
		shift = keys[KeyEvent.VK_SHIFT];
	}
	
	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		
		return justPressed[keyCode];
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		
		keys[e.getKeyCode()] = true;
		pKeysReleased = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		
		keys[e.getKeyCode()] = false;
		if (!keys[KeyEvent.VK_A] && !keys[KeyEvent.VK_D]) {
			pKeysReleased = true;
		}
	}

}
