package dev.java2dgame.states;

import java.awt.Graphics;

import dev.java2dgame.main.Handler;

public abstract class State {
	
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	//  FOR OTHER STATES
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
	}	
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
