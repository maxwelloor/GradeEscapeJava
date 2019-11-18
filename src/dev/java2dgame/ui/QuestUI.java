package dev.java2dgame.ui;

import java.awt.Graphics;
import java.util.ListIterator;

public class QuestUI extends UIObject {
	
	private String qName, qDesc;

	public QuestUI(float x, float y, int width, int height, String qName, String qDesc) {
		super(x, y, width, height);
		
		this.qName = qName;
		this.qDesc = qDesc;
	}

	@Override
	public void tick(ListIterator<UIObject> it) {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void onClick() {
		
	}

}
