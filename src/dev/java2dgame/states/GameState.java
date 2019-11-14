package dev.java2dgame.states;

import java.awt.Graphics;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.gfx.Dialogue;
import dev.java2dgame.main.Handler;
import dev.java2dgame.tiles.Tile;
import dev.java2dgame.worlds.World;

public class GameState extends State{
	
	public GameState(Handler handler) {
		super(handler);
		handler.setWorld(new World(handler, "1"));
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
