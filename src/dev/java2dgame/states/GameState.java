package dev.java2dgame.states;

import java.awt.Graphics;

import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.UIManager;
import dev.java2dgame.worlds.World;

public class GameState extends State{
	
	private UIManager uiManager;
	
	public GameState(Handler handler) {
		super(handler);
		handler.setWorld(new World(handler, "1"));
		
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
	}

	@Override
	public void tick() {
		handler.getWorld().tick();
	}

	@Override
	public void render(Graphics g) {
		handler.getWorld().render(g);
	}
}
