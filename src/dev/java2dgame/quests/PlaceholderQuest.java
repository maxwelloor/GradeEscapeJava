package dev.java2dgame.quests;

import dev.java2dgame.main.Handler;

public class PlaceholderQuest extends Quest {

	public PlaceholderQuest(Handler handler, String qName, String qDesc) {
		super(handler, qName, qDesc);
	}

	@Override
	public void tick() {
		if (isQuestRequirementFilled()) {
			finished();
		}
	}

	@Override
	public boolean isQuestRequirementFilled() {
		if (!questCompleted) {
			if (this.handler.getWorld().getEntityManager().getPlayer().isTired())
				return true;
			else
				return false;
		} else {
			return false;
		}
		
	}

	@Override
	public void finished() {
		setQuestCompleted(true);
		System.out.println("Jobs done!");
	}

}
