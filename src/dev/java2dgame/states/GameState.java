package dev.java2dgame.states;

import java.awt.Graphics;

import dev.java2dgame.entities.creatures.Player;
import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.SprintBarUI;
import dev.java2dgame.ui.UIManager;
import dev.java2dgame.worlds.World;

public class GameState extends State{
	
	private UIManager uiManager;
	
	public GameState(Handler handler) {
		super(handler);
		handler.setWorld(new World(handler, "1"));
		
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
		handler.getMouseManager().getUiManager().addObject(new SprintBarUI(handler));
	}

	@Override
	public void tick() {
		handler.getWorld().tick();
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		handler.getWorld().render(g);
		uiManager.render(g);
	}
}
