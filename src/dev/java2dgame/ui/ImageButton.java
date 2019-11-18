package dev.java2dgame.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ListIterator;

import dev.java2dgame.interfaces.ClickListener;

public class ImageButton extends UIObject {
	
	private BufferedImage[] images;
	private ClickListener click;

	public ImageButton(float x, float y, BufferedImage[] images, ClickListener click) {
		super(x, y, images[0].getWidth(), images[0].getHeight());
		
		this.images = images;
		this.width = images[0].getWidth();
		this.height = images[1].getHeight();
		this.click = click;
	}

	@Override
	public void tick(ListIterator<UIObject> it) {
		
	}

	@Override
	public void render(Graphics g) {
		if (hovering)
			g.drawImage(images[0], (int) x, (int) y, null);
		else
			g.drawImage(images[1], (int) x, (int) y, null);
	}

	@Override
	public void onClick() {
		click.onClick();
	}

}
