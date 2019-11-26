package dev.java2dgame.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.java2dgame.gfx.Assets;
import dev.java2dgame.interfaces.ClickListener;
import dev.java2dgame.main.Handler;
import dev.java2dgame.ui.ImageButton;
import dev.java2dgame.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	private static final int BUTTON_W = Assets.start_button_f1.getWidth();
	
	public MenuState(Handler handler) {
		super(handler);
		
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
		
		handler.getMouseManager().getUiManager().addObject(new ImageButton(handler.getWidth()/2 - BUTTON_W/2, 500, Assets.start_button, new ClickListener() {

			@Override
			public void onClick() {
				State.setState(handler.getGame().getGameState());
			}}));
	}

	@Override
	public void tick() {
		uiManager.tick(uiManager.getObjects().listIterator());
	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		
		g.drawImage(Assets.title_image, handler.getWidth()/2 - Assets.title_image.getWidth()/2, handler.getHeight()/8, null);	
		uiManager.render(g);
	}

}
