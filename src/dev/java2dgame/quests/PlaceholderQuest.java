package dev.java2dgame.quests;

import dev.java2dgame.main.Handler;

public class PlaceholderQuest extends Quest {

	public PlaceholderQuest(Handler handler, String qName, String qDesc) {
		super(handler, qName, qDesc);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public boolean isQuestDone() {
		return false;
	}

	@Override
	public void finished() {
		
	}

}
