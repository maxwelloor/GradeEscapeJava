package dev.java2dgame.quests;

import dev.java2dgame.collectibles.Collectible;

public class FindMooresHairQuest extends Quest {

	public FindMooresHairQuest() {
		super("Find Mr. Moores Hair", "You need to search the school to find moores lost headtop.");
	}

	@Override
	public boolean isQuestRequirementFilled() {
		if (!questCompleted) {
			if (Collectible.mooresHair.isCollected())
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public void questReward() {
		
	}

}
