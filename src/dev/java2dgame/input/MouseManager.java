package dev.java2dgame.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener {
	
	private Handler handler;
	private boolean leftPressed, rightPressed;
	private int mouseX, mouseY;
	private UIManager uiManager;
	
	public MouseManager(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if (uiManager != null)
			uiManager.onMouseMove(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		if (e.getButton() == MouseEvent.BUTTON2)
			rightPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		if (e.getButton() == MouseEvent.BUTTON2)
			rightPressed = false;
		
		if (uiManager != null)
			uiManager.onMouseRelease(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	// Getters
	public boolean isLeftPressed() {
		return leftPressed;
	}

	public boolean isRightPressed() {
		return rightPressed;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}
	
	public UIManager getUiManager() {
		return uiManager;
	}

	// Setters
	
	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	
	public void setLeftPressed(boolean leftPressed) {
		this.leftPressed = leftPressed;
	}

	public void setRightPressed(boolean rightPressed) {
		this.rightPressed = rightPressed;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}
	
	

}
